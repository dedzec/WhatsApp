package com.lucas.whatsappandroid.whatsapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lucas.whatsappandroid.whatsapp.R;

public class MainActivity extends AppCompatActivity {

    //private DatabaseReference referenciaFirebase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referenciaFirebase.child("pontos").setValue(100);
    }
}