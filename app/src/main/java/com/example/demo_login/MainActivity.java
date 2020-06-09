package com.example.demo_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name,number;
    Button button;
    String number_,name_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_ = name.getText().toString().trim();
                number_ = number.getText().toString().trim();
                doLogin();
            }
        });

    }

    public void doLogin() {
        String servername = "http://192.168.1.11/demo/";
        String doLoginurl = servername+"login.php";

        Log.e("url",doLoginurl);

        if (name_.trim().equals("") || number_.trim().equals(""))
            Toast.makeText(this, "Please Fill Up All Fields", Toast.LENGTH_SHORT).show();
        else {


            StringRequest request = new StringRequest(Request.Method.POST, doLoginurl, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {

                    Log.e("Response", response);
                    JSONObject json = null;
                    try {
                        json = new JSONObject(response);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                    Log.e("Erro: ", error.getMessage());
                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("u_name", name_);
                    params.put("phoneno", number_);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);

        }
    }
}
