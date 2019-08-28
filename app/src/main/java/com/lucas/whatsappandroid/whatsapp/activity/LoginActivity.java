package com.lucas.whatsappandroid.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.lucas.whatsappandroid.whatsapp.R;
import com.lucas.whatsappandroid.whatsapp.config.ConfiguracaoFirebase;

public class LoginActivity extends AppCompatActivity {

    private TextView novoUsuario;
    private DatabaseReference referenciaFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("pontos").setValue("800");

        novoUsuario = (TextView) findViewById(R.id.abrirCadastroUsuario);
        novoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, CadastroUsuarioActivity.class);
                startActivity(intent);

            }
        });

    }

}
