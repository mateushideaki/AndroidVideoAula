package br.com.mateushideaki.videoaula;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB
        SQLiteDatabase db = openOrCreateDatabase("devmedia.db", Context.MODE_PRIVATE, null);
        StringBuilder strb = new StringBuilder();
        strb.append("CREATE TABLE IF NOT EXISTS [clientes](");
        strb.append("[id] INTEGER PRIMARY KEY AUTOINCREMENT,");
        strb.append("[nome] VARCHAR(30), ");
        strb.append("[email] VARCHAR(40), ");
        strb.append("[endereco] VARCHAR(50),");
        strb.append("[numero] VARCHAR(10));");

        db.execSQL(strb.toString());

        Button btnCadastrar = (Button) findViewById(R.id.btn_cadastrar_actMain);
        Button btnListar = (Button) findViewById(R.id.btn_listar_actMain);
        Button btnApagar = (Button) findViewById(R.id.btn_apagar_actMain);
        Button btnSair = (Button) findViewById(R.id.btn_sair_actMain);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CadastroActivity.class));
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
