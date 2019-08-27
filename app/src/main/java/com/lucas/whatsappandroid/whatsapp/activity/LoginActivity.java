package com.lucas.whatsappandroid.whatsapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.lucas.whatsappandroid.whatsapp.R;
import com.lucas.whatsappandroid.whatsapp.helper.Preferencias;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText nome;
    private EditText telefone;
    private EditText codPais;
    private EditText codArea;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nome        = (EditText) findViewById(R.id.edit_nome);
        telefone    = (EditText) findViewById(R.id.edit_telefone);
        codPais     = (EditText) findViewById(R.id.edit_cod_pais);
        codArea     = (EditText) findViewById(R.id.edit_cod_area);
        cadastrar   = (Button) findViewById(R.id.bt_cadastrar);

        /*Definir Mascaras*/
        SimpleMaskFormatter simpleMaskCodPais = new SimpleMaskFormatter("+NN");
        SimpleMaskFormatter simpleMaskCodArea = new SimpleMaskFormatter("NN");
        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");

        MaskTextWatcher maskCodPais = new MaskTextWatcher(codPais, simpleMaskCodPais);
        MaskTextWatcher maskCodArea = new MaskTextWatcher(codArea, simpleMaskCodArea);
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);

        codPais.addTextChangedListener(maskCodPais);
        codArea.addTextChangedListener(maskCodArea);
        telefone.addTextChangedListener(maskTelefone);

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomeUsuario = nome.getText().toString();
                String telefoneCompleto =
                        codPais.getText().toString() +
                        codArea.getText().toString() +
                        telefone.getText().toString();

                String telefoneSemFormatacao = telefoneCompleto.replace("+", "");
                telefoneSemFormatacao = telefoneSemFormatacao.replace("-", "");

                //Gerar tokem
                Random randomico = new Random();
                int numeroRandomico = randomico.nextInt( 9999 - 1000) + 1000;
                String token = String.valueOf(numeroRandomico);
                String mensagemEnvio = "WhatsApp Código de Confirmação: " + token;

                //Salvar os Dados para Validação
                Preferencias preferencias = new Preferencias( LoginActivity.this );
                preferencias.salvarUsuarioPreferencias(nomeUsuario, telefoneSemFormatacao, token);

                //Envio de SMS
                boolean enviadoSMS = enviaSMS("+" + telefoneSemFormatacao, mensagemEnvio);

                //HashMap<String, String> usuario = preferencias.getDadosUsuario();

            }
        });

    }

    //Envio do SMS
    private boolean enviaSMS(String telefone, String mensagem) {

        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(telefone, null, mensagem, null, null);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
