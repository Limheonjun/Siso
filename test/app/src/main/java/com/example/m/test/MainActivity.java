package com.example.m.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    EditText idText, passwordText;
    TextView registerText;
    Button loginBtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);
        registerText = findViewById(R.id.registerText);
        loginBtn =  findViewById(R.id.loginBtn);





        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new GsonBuilder().setLenient()
                        .create();

                GsonConverterFactory factory = GsonConverterFactory.create(gson);



                Retrofit retrofit = new Retrofit.Builder()

                        .addConverterFactory(factory)

                        .baseUrl("http://14.49.38.11:80/") // url과 포트

                        .addConverterFactory(GsonConverterFactory.create())

                        .build();

                String id = idText.getText().toString();
                String password = passwordText.getText().toString();

                User user = new User();

                user.setId(id);
                user.setPassword(password);


                final RemoteService remote = retrofit.create(RemoteService.class);

                Call<User> call3 = remote.login(user.getId(), user.getPassword());
                call3.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if(response.isSuccessful()){
                            System.out.println("verification이 무엇인가" + user.verification);
                            if(user.verification){
                                Log.i("MyTag", "베리피케이션 확인1 : " + user.verification);
                                Toast.makeText(getBaseContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                                startActivity(intent);
                            } else {
                                Log.i("MyTag", "베리피케이션 확인2 : " + user.verification);
                                Log.i("MyTag", "응답코드 : " + response.code());
                                Toast.makeText(getBaseContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Log.i("MyTag", "응답코드 : " + response.code());
                        }





//


                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.i("MyTag", "서버 onFailure 에러내용 : " + t.getMessage());

                    }

                });
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });




    }
}
