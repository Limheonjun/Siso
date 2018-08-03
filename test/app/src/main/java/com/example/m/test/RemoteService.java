package com.example.m.test;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RemoteService {
    /*
    @FormUrlEncoded
    @POST("/register") // 포스트 방식으로 보낼 url
    Call<product> post_food(@Body product product);
    */

    //@FormUrlEncoded
    //@POST("/register") // 포스트 방식으로 보낼 url
    //Call<User> register(@Body User user);

    @FormUrlEncoded
    @POST("register")
    Call<User> register(
            @Field("id") String id,
            @Field("password") String password,
            @Field("name") String name,
            @Field("emergency") String emergency,
            @Field("category") String category
    );

    @FormUrlEncoded
    @POST("login")
    Call<User> login(
            @Field("id") String id,
            @Field("password") String password
    );



//    @POST("register") // 포스트 방식으로 보낼 url
//    Call<user> test(@Body user user);

    @POST ("/register")
    Call<User> test(@Query("name") String name);


    @GET("/test/test")//해당 url로 갔을때 [{p_name:"", p_price:"", p_description:"", p_subclass:"", p_category:"", p_origin:"", p_picture:"", p_link:""}] 식의 데이터를 가져온다는걸 알 수 있음
    Call <List<product>> getAllProduct();//다른 조건사항이 없으니 응답받은 위와 같은 데이터를 아래 제네릭 표현의 Call객체로 저장시킴

    @GET("test/{p_name}") // @GET("URL/{서버로 전송할 값}) 시맨틱 url일때 사용
    Call <List<product>> getNameProduct( @Path("p_name") String p_name );
    // Call <List<product>> 함수명 (@Path("서버로 전송할 값") 자료형 변수명
    // 확실하진 않지만 String타입의 p_name이라는 변수를 만들어서 값을 받고 그 값을 p_name이라는 태그로 전달하는것 같음 p:name: ""
    // 자세한건 http://blog.naver.com/PostView.nhn?blogId=lmjing&logNo=220660414338 참조


    /*
    <> : 제네릭표현
    http://arabiannight.tistory.com/entry/%EC%9E%90%EB%B0%94Java-ArrayListT-%EC%A0%9C%EB%84%A4%EB%A6%AD%EC%8A%A4Generics%EB%9E%80 참조

    Call <Product> 이면 Product와 동일한 형태를 갖는(아마, Product.java와 동일한 자료형, 메소드를 갖는)
    [{p_name:"", p_price:"", p_description:"", p_subclass:"", p_category:"", p_origin:"", p_picture:"", p_link:""}]Call 객체를 생성

    Call <List<product>>이면 List<Product>와 동일한 형태를 갖는(현재 Product는 7개의 변수를 갖고 있고 그거의 리스트 형태이면 대략 이런식
    [{p_name:"", p_price:"", p_description:"", p_subclass:"", p_category:"", p_origin:"", p_picture:"", p_link:""},
    {p_name:"", p_price:"", p_description:"", p_subclass:"", p_category:"", p_origin:"", p_picture:"", p_link:""},
    {p_name:"", p_price:"", p_description:"", p_subclass:"", p_category:"", p_origin:"", p_picture:"", p_link:""},
    ...] 이런식의 product의 리스트 형태를 갖을 수 있는 Call 객체를 생성

     */




}