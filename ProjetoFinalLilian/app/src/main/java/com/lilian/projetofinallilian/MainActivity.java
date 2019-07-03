//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina: M4DADM - Programação de Computadores e Dispositivos Móveis
//Aluna: Lilian Sampaio Souza Machado
//******************************************************
package com.lilian.projetofinallilian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //criar botão para inserir a pessoa.
    Button btInserirPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //instanciar o botão para inserir a pessoa
        btInserirPessoa = (Button) findViewById(R.id.btInserirPessoa);
        btInserirPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamaSegundaTela(); //Função para ir de uma Activit para outra.
            }
        });
    }
    //Método para ir para a segunda activit.
    void chamaSegundaTela() {
        Intent Intent = new Intent();
        Intent.setClass(MainActivity.this, SecondActivity.class);
        startActivity(Intent);
        finish();
    }
}
