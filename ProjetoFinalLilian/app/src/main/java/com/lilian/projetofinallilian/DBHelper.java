package com.lilian.projetofinallilian;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    private static final String DATABASE_NAME = "bancodedados.db"; //Atributo para criação do banco de dados
    private static final int DATABASE_VERSION = 1; //Atributo para nomear a versão
    private static final String TABLE_NAME = "cadastro"; //Atributo para nomear a tabela.

    //Atributos para criar suporte ao BD.
    private Context context;
    private SQLiteDatabase db;

    //Atributo para inserção dos dados na tabela.
    private SQLiteStatement insertStnt;
    private static final String INSERT = "insert into " + TABLE_NAME + " (nome, cpf, idade, telefone, email) values (?,?,?,?,?)";

    //Construtor que passará os dados para o BD
    public DBHelper (Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStnt = this.db.compileStatement(INSERT);
    }

    //Metodo para automatizar a inserção de dados no BD.
    public long insert (String nome, String cpf, String idade, String telefone, String email){
        this.insertStnt.bindString(1, nome);
        this.insertStnt.bindString(2, cpf);
        this.insertStnt.bindString(3, idade);
        this.insertStnt.bindString(4, telefone);
        this.insertStnt.bindString(5, email);

        return this.insertStnt.executeInsert();
    }

    //Metodo que apaga todos os registros.
    public void delleteAll() {
        this.db.delete(TABLE_NAME, null, null);
    }

    //Metodo para listar todas as informações inseridas no BD.
    public List<Cadastro> queryGetAll(){
        List <Cadastro> list = new ArrayList<Cadastro>();
        //Mecanismo que trata as excessões de informação no BD.
        try {
            Cursor cursor = this.db.query (TABLE_NAME, new String[] {"nome", "cpf", "idade", "telefone", "email"},
                    null, null, null, null, null, null);
            int nregistros = cursor.getCount(); //variavel que guarda o número de registros no BD.
            if (nregistros !=0) {
                cursor.moveToFirst(); //Coloca o cursor na posição do primeiro campo.
                do { //Preenche as colunas com as informações do BD.
                    Cadastro cadastro = new Cadastro(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    list.add(cadastro);
                } while (cursor.moveToNext()); //Move o cursor para o próximo campo.

                if (cursor != null && ! cursor.isClosed())
                    cursor.close();
                return list; //Executa a lista.
            }else
                return null;
        }
        catch (Exception err) {
            return null;
        }
    }
    //Iner class, uma classe dentro da outra.
    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate (SQLiteDatabase db) { //Implementação dos métodos abstratos e criação dos parametros
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, " +
                    "cpf TEXT, idade TEXT, telefone TEXT, email TEXT);";
            db.execSQL(sql);
        }
        //Método para atualizar o DB.
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }


}
