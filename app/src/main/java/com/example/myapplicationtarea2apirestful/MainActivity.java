package com.example.myapplicationtarea2apirestful;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://jsonplaceholder.typicode.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtinformacion =findViewById(R.id.txtinformacion);

        String Informacion="";

        JSONArray JSONInformacion =  new JSONArray(result);
        for(int i = 0; i< JSONInformacion.length(); i++) {
            JSONObject Us =  JSONInformacion.getJSONObject(i);
            String ident = Us.getString("id");
            String Nombre = Us.getString("name");
            String Email = Us.getString("email");


            Informacion = Informacion + "\n" + "Num:" + ident + "\n" +
                    " Nom:" + Nombre + "\n" + " E-mail:" + Email.toLowerCase();
        }

        txtinformacion.setText(Informacion);
    }
}