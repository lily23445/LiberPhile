package com.example.liberphile;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewSignUp;
    private String BASE_URL = "http://192.168.0.104:3000";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Liberphile");
        }

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        // Button OnclickListener event code below
        buttonLogin.setOnClickListener(v ->{
            try {
                loadLogin();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });


        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handlesignupDialog();
            }

            private void handlesignupDialog() {
                View view = getLayoutInflater().inflate(R.layout.signup_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setView(view).show();

                Button signupBtn = view.findViewById(R.id.signup);
                EditText nameEdit = view.findViewById(R.id.nameEdit);
                EditText emaiEdit = view.findViewById(R.id.emailEdit);
                EditText passwordEdit = view.findViewById(R.id.passwordEdit);

                signupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ProgressDialog loading = new ProgressDialog(LoginActivity.this);
                        loading.setMessage("Please Wait...");
                        loading.show();
                        loading.setCanceledOnTouchOutside(false);

                        String username = nameEdit.getText().toString();
                        String email = emaiEdit.getText().toString();
                        String password = passwordEdit.getText().toString();


                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                        String url = "http://192.168.0.104:3000/signup/"+username+"/"+email+"/"+password;


                        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject object = new JSONObject(response);
                                    Toast.makeText(LoginActivity.this, " " + object, Toast.LENGTH_SHORT).show();

                                    loading.dismiss();
                                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();

                                    // Navigate to HomepageActivity
                                    Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                                    startActivity(intent);
                                    finish(); // Optional: Call finish() if you don't want to return to this activity

                                }catch (Exception e){
                                    loading.dismiss();
                                    e.printStackTrace();
                                }

                            }
                        }, new com.android.volley.Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                loading.dismiss();
                                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();

                                Log.d("LoginActivity", "Username: " + username);
                                params.put("name", username);
                                params.put("email", password);
                                return params;
                            }
                        };
//                        queue.add(request);

                }
                });
            }
        });
// Check if views are initialized

        // Check if views are initialized
        if (editTextUsername == null || editTextPassword == null || buttonLogin == null || textViewSignUp == null) {
            Log.e(TAG, "One or more views are not initialized.");
            return;
        }




    }

// SIGIN FUNCTION








// LOGIN FUNCTION

    private void loadLogin() throws JSONException {
        final ProgressDialog loading = new ProgressDialog(LoginActivity.this);
        loading.setMessage("Please Wait...");
        loading.show();
        loading.setCanceledOnTouchOutside(false);

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        JSONObject data = new JSONObject();
        data.put("Username", username);
        data.put("Password", password);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String url = "http://192.168.0.104:3000/login/"+username+"/"+password;


        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject object = new JSONObject(response);
                    Toast.makeText(LoginActivity.this, " " + object, Toast.LENGTH_SHORT).show();

                    loading.dismiss();
                    Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    // Navigate to HomepageActivity
                    Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Call finish() if you don't want to return to this activity

                }catch (Exception e){
                    loading.dismiss();
                    e.printStackTrace();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                Log.d("LoginActivity", "Username: " + username);
                params.put("name", username);
                params.put("email", password);
                return params;
            }
        };
//        queue.add(request);
    }
}

