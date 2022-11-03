package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MyReceiverBroCast;

public class MainActivity extends AppCompatActivity {

    private MyReceiverBroCast objRe;
    private TextView txtResult;
    private Button btn;
    public static String mensaje = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResult = findViewById(R.id.textViewResult);
        btn = findViewById(R.id.buttonSendLocalBroCast);

    }

    @Override
    protected void onResume() {
        super.onResume();
        envia();
    }

    private void envia() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText msj = findViewById(R.id.editTextMen);
                mensaje = msj.getText().toString();
                final Intent i = new Intent();

                i.setAction("com.example.lab04");
                i.putExtra("data", "Some data");
                i.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                i.setComponent(new ComponentName("com.example.lab04","com.example.lab04.MyReceiverBroCast"));
                sendBroadcast(i);
                Log.d("Salida", "Mensaje Enviado desde MyAplication");
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(objRe);
    }
}