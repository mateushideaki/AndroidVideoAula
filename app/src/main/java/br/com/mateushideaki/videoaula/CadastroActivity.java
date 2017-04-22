package br.com.mateushideaki.videoaula;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity implements Runnable {
    private ProgressDialog pd;
    private String valor;
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtEnder;
    private EditText txtNumero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button btnCadastro = (Button) findViewById(R.id.btn_cadastrar_actCadastro);
        this.txtNome = (EditText) findViewById(R.id.txt_nome_actCadastro);
        this.txtEmail = (EditText) findViewById(R.id.txt_email_actCadastro);
        this.txtEnder = (EditText) findViewById(R.id.txt_endereco_actCadastro);
        this.txtNumero = (EditText) findViewById(R.id.txt_numero_actCadastro);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtNome.getText().toString().equals("")){
                    txtNome.setError("Este campo é obrigatório.");
                } if(txtEmail.getText().toString().equals("")){
                    txtEmail.setError("Este campo é obrigatório.");
                } if(txtEnder.getText().toString().equals("")){
                    txtEnder.setError("Este campo é obrigatório.");
                } if(txtNumero.getText().toString().equals("")){
                    txtNumero.setError("Este campo é obrigatório.");
                } else {
                    pd = ProgressDialog.show(CadastroActivity.this, "Aguarde.", "Cadastrando Informações.", true);
                    Thread t = new Thread(CadastroActivity.this);
                    t.start();
                }
            }
        });
    }

    @Override
    public void run(){
        valor = txtNome.getText().toString();
        SQLiteDatabase db = openOrCreateDatabase("devmedia.db", Context.MODE_PRIVATE, null);
        ContentValues ctv = new ContentValues();
        ctv.put("nome", txtNome.getText().toString());
        ctv.put("email", txtEmail.getText().toString());
        ctv.put("endereco", txtEnder.getText().toString());
        ctv.put("numero", txtNumero.getText().toString());
        if(db.insert("clientes", null, ctv) > 0){
            h.sendEmptyMessage(0);
        }
    }


    //SERVE PARA ATUALIZAR A INTERFACE DE UMA ACTIVITY
    private Handler h = new Handler(){
        @Override
        public void handleMessage (Message msg){
            pd.dismiss();
            Toast t = Toast.makeText(getBaseContext(), "Cliente cadastrado com sucesso.", Toast.LENGTH_SHORT);
            t.show();
        }
    };
}
