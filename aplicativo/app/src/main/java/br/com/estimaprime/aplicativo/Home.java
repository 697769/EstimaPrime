package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
    private Button btn_select_enterprise,btn_back;
    public static final int Id_Enteprise=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_back = (Button) findViewById(R.id.btn_back);
        preencherNotas();
        verificaEmpresaSelecionada();
        btn_back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Voltar();
            }
        });
    }
    private void Voltar(){
        Intent intent = new Intent(Home.this, Mainactivity.class);
        startActivity(intent);
    }
    private void verificaEmpresaSelecionada(){
        TextView textView = (TextView) findViewById(R.id.textView);
        SharedPreferences sharedPreferences = getSharedPreferences("estimaprime", Context.MODE_PRIVATE);

        int Enterprise = sharedPreferences.getInt("GLO_ENTERPRISE",Id_Enteprise);

        if(Enterprise<=0){
            Toast toast = Toast.makeText(this, "Empresa não encontrada! " +Enterprise, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
        }else{
            textView.setText("Código da Empresa: " +Enterprise);
        }
    }
    private void preencherNotas(){
        String[] types = {"Entrada","Saída","Total"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,types);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }
}
