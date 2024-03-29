package com.example.sample.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Config;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

    private EditText email,password;
    private Button button;
    private RequestQueue requestQueue;
    private static final String URL="https://stncrpcom.000webhostapp.com/demo1/demo1/user_control.php";
    private StringRequest request;
    String test;
   static String uname,passwd;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.button);

        requestQueue = Volley.newRequestQueue(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                uname = email.toString();
                                passwd = password.toString();

                                 test = jsonObject.getString("success");
                                Toast.makeText(getApplicationContext(),"SUCCESS" +test,Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(getApplicationContext(),Navigation.class);
                                i.putExtra("email",test);
                                 startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"ERROR" +jsonObject.getString("error"),Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("email",email.getText().toString());
                        hashMap.put("password",password.getText().toString());
                        return hashMap;
                    }
                };
                requestQueue.add(request);


            }
        });

    }
}
// https://www.youtube.com/watch?annotation_id=annotation_1157100269&feature=iv&src_vid=7CjBlxQRf7s&v=tguOfRD8vYo