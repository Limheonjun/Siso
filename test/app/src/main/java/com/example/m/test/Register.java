package com.example.m.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText idText, passwordText, nameText, emergencyText;
        Button registerBtn;

        final User user = new User();


        // 분류항목 spinner로 구현
        final Spinner spinner = (Spinner)findViewById(R.id.category);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(spinner.getSelectedItemPosition()>0){
//                    category[0] =(String)spinner.getAdapter().getItem(spinner.getSelectedItemPosition());
//                }
                user.setCategory(parent.getItemAtPosition(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);
        nameText = findViewById(R.id.nameText);
        emergencyText = findViewById(R.id.emergencyText);
        registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setId(idText.getText().toString());
                user.setPassword(passwordText.getText().toString());
                user.setName(nameText.getText().toString());
                user.setEmergency(emergencyText.getText().toString());

                Gson gson = new GsonBuilder().setLenient()
                        .create();

                GsonConverterFactory factory = GsonConverterFactory.create(gson);

                Retrofit retrofit = new Retrofit.Builder()

                        .addConverterFactory(factory)

                        .baseUrl("http://14.49.38.11:80/") // url과 포트

                        .addConverterFactory(GsonConverterFactory.create())

                        .build();

                final RemoteService remote = retrofit.create(RemoteService.class);

                Call<User> call3 = remote.register(user.getId(), user.getPassword(), user.getName(), user.getEmergency(), user.getCategory());
                call3.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if(response.isSuccessful()){
                            if(user.verification){
                                Log.i("MyTag", "베리피케이션 확인1 : " + user.verification);
                                Toast.makeText(getBaseContext(), "회원가입 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            } else {
                                Log.i("MyTag", "베리피케이션 확인2 : " + user.verification);
                                Log.i("MyTag", "응답코드 : " + response.code());
                                Toast.makeText(getBaseContext(), "이미 존재하는 아이디입니다", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.i("MyTag", "응답코드 : " + response.code());
                            Toast.makeText(getBaseContext(), "이미 존재하는 아이디입니다", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("MyTag", "서버 onFailure 에러내용 : " + t.getMessage());


                    }

                });
            }
        });




    }
}
