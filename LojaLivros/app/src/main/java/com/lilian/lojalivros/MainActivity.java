package com.lilian.lojalivros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    Button btOk;
    CheckBox cbcsharp, cbandroid, cbjava;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btOk = (Button) findViewById(R.id.btcomprar);
        cbcsharp = (CheckBox) findViewById(R.id.cbcsharp);
        cbandroid = (CheckBox) findViewById(R.id.cbandroid);
        cbjava = (CheckBox) findViewById(R.id.cbjava);

        btOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mensagem;

                if (!cbcsharp.isChecked() && !cbandroid.isChecked() && !cbjava.isChecked()) {
                    mensagem = "Você não selecionou livros!\n\n";

                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setMessage(mensagem);
                    alertDialog.show();
                    return;
                }

                mensagem = "Você comprou os seguintes livros:\n\n";

                if (cbcsharp.isChecked()) {
                    mensagem = mensagem + "CSharp\n";
                }
                if (cbandroid.isChecked()) {
                    mensagem = mensagem + "Android\n";
                }
                if (cbjava.isChecked()) {
                    mensagem = mensagem + "Java\n";
                }

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setMessage(mensagem);
                alertDialog.show();
            }
        });



    }
}
