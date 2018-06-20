package com.example.adithyaiyer.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);







    }

    public void onCreatingAccount(View view){
        EditText newmail=(EditText)findViewById(R.id.email_text);
        EditText newUsername= (EditText)findViewById(R.id.new_username_text);
        EditText newPassword= (EditText)findViewById(R.id.new_password_text);
        String mailtext= newmail.getText().toString();
        String usernametext= newUsername.getText().toString();
        String passText= newPassword.getText().toString();
        customer test= new customer(usernametext,passText,mailtext);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiServiceCustomer.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        ApiServiceCustomer api= retrofit.create(ApiServiceCustomer.class);

        api.saveCustomer(test).enqueue(new Callback<customer>() {
            @Override
            public void onResponse(Call<customer> call, Response<customer> response) {
                Toast.makeText(Sign_up.this,
                        "successfull sign up", Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),SignIn.class);
                startActivity(i);            }

            @Override
            public void onFailure(Call<customer> call, Throwable t) {
                Toast.makeText(Sign_up.this,
                        "internet hug raha hai", Toast.LENGTH_LONG).show();
            }
        });
    }
}
