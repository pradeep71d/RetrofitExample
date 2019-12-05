package com.example.retrofitp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SignUpResponse signUpResponse;
    EditText emailId, password, name;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        name = findViewById(R.id.username);
        signup = findViewById(R.id.signUp);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateData();  //for calling method body validdata;
            }
        });
    }

    private  void ValidateData() {
        if(name.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please enter Name", Toast.LENGTH_SHORT).show();
        }
        else if(emailId.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please enter emailId", Toast.LENGTH_SHORT).show();
        }
        else if(!isEmailValid(emailId))
        {
            Toast.makeText(this, "please enter the valid email", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please enter the password", Toast.LENGTH_SHORT).show();
        }
        else
        {
            signup();
            //Toast.makeText(this, "Signup method will be invoked", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailValid(EditText emailId) {

        return android.util.Patterns.EMAIL_ADDRESS.matcher(
                emailId.getText().toString()).matches();
    }

    private void signup(){


        Api.getclient().registration(name.getText().toString().trim(),emailId.getText().toString().trim(),password.getText().toString().trim(),"email").enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                signUpResponse=response.body();
                Toast.makeText(getApplicationContext(),"connection Succesful" +response.code(), Toast.LENGTH_SHORT).show();
                 Intent intent =new Intent(MainActivity.this, HomePage.class);
                 startActivity(intent);

            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                Log.d("response",t.getStackTrace().toString());

                Toast.makeText(getApplicationContext(), "Response failed"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
