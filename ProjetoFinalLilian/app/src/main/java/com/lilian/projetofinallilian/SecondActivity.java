package com.lilian.projetofinallilian;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private DBHelper dh;
    EditText etNome, etCpf, etIdade, etTelefone, etEmail;
    Button btVoltar, btInserir, btListar; //criar os botões para os comandos voltar, inserir e listar.

    @Override //Criação dos métodos.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.dh = new DBHelper(this);
        etNome = (EditText) findViewById(R.id.etNome);
        etCpf= (EditText) findViewById(R.id.etCpf);
        etIdade = (EditText) findViewById(R.id.etIdade);
        etTelefone = (EditText) findViewById(R.id.etTelefone);
        etEmail = (EditText) findViewById(R.id.etEmail);

        btInserir = (Button) findViewById(R.id.btInserir);
        btListar = (Button) findViewById(R.id.btListar);

        //Implementação do método para adicionar no BD os valores inseridos.
        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNome.getText().length()>0 && etCpf.getText().length()>0 && etIdade.getText().length()>0 && etTelefone.getText().length()>0 && etEmail.getText().length()>0) {
                    dh.insert(etNome.getText().toString(), etCpf.getText().toString(), etIdade.getText().toString(), etTelefone.getText().toString(), etEmail.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this); //Mensagem de confirmação.
                    adb.setTitle("Sucesso");
                    adb.setMessage("Cadastro realizado!");
                    adb.show();

                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
                else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this); //Mensagem de erro.
                    adb.setTitle("Erro");
                    adb.setMessage("Todos os campos devem ser preenchidos!");
                    adb.show();

                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
            }
        });

        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Lista os dados existentes
                List<Cadastro> cadastros = dh.queryGetAll();
                if (cadastros == null) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this); //Mensagem de cadastro vazio.
                    adb.setTitle("Mensagem");
                    adb.setMessage("Não há pessoas cadastradas!");
                    adb.show();
                    return;
                }
                for (int i=0; i<cadastros.size(); i++) { //Mostra os dados armazenados no BD.
                    Cadastro cadastro = (Cadastro) cadastros.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(SecondActivity.this);
                    adb.setTitle("Registro " + i);
                    adb.setMessage("Nome: "+ cadastro.getNome()+"\nCPF: "+ cadastro.getCpf()+"\nIdade: "+ cadastro.getIdade()+"\nTelefone: "+ cadastro.getTelefone()+"\nE-mail: "+ cadastro.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    adb.show();
                }
            }
        });
        //Instanciando botão para voltar para a primeira tela.
        btVoltar = (Button) findViewById(R.id.btVoltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voltarPrimeiraTela();
            }
        });
    }
    //Método para voltar para a primeira tela
    void voltarPrimeiraTela() {
        Intent Intent = new Intent();
        Intent.setClass(SecondActivity.this, MainActivity.class);
        startActivity(Intent);
        finish();
    }
}
