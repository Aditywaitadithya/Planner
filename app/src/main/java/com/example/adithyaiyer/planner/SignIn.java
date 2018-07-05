package com.example.adithyaiyer.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity {


    private List<customer> fetchedList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);
        Call<List<customer>> call=api.getMyJSONCustomer();

        call.enqueue(new Callback<List<customer>>() {
            @Override
            public void onResponse(Call<List<customer>> call, Response<List<customer>> response) {
                fetchedList= response.body();

            }

            @Override
            public void onFailure(Call<List<customer>> call, Throwable t) {
                Toast.makeText(SignIn.this,
                        "ask adi to start server", Toast.LENGTH_LONG).show();
            }
        });





    }


    public void goToSignUp(View view){

        Intent i=new Intent(this, Sign_up.class);
        startActivity(i);



    }



    public void SignInSignal(View view){
        EditText usernameField =(EditText)findViewById(R.id.Sign_in_username);
        EditText passwordField = (EditText)findViewById(R.id.sign_in_password);
        String username=usernameField.getText().toString();
        String password = passwordField.getText().toString();




        int i=0;Boolean b= true;
        for(i=0; i<fetchedList.size();i++){
            if(fetchedList.get(i).getUsername().equals(username) && fetchedList.get(i).getPassword().equals(password)){
                Toast.makeText(SignIn.this,
                        "You are a member", Toast.LENGTH_LONG).show();


                b=false;
            break;
            }


            }
            if(b==false){
            Intent myintent=new Intent(this, Main_task.class);
            int tata=fetchedList.get(i).getId();
            myintent.putExtra("pkvalue",tata);
                Intent myintent2=new Intent(this, AddTask.class);
                myintent2.putExtra("pkvalue",tata);
                Toast.makeText(SignIn.this,
                        "You are a member", Toast.LENGTH_SHORT).show();
            startActivity(myintent);
            }
            if(b){
                Toast.makeText(SignIn.this,
                        "You are not a member", Toast.LENGTH_LONG).show();
            }


    }
}
