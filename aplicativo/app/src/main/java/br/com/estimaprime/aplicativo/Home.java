package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import dao.NoteDAO;

public class Home extends Activity {

    private Button btn_select_enterprise,btn_back,btnAddNote;
    public static final int Id_Enteprise=0;
    public int Enterprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_select_enterprise = (Button) findViewById(R.id.btnSelectEnterprise);
        btnAddNote = (Button) findViewById(R.id.btnHomeAddNote);

        verificaEmpresaSelecionada();
        preencherNotas();

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Deslogar();
            }
        });

        btn_select_enterprise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Voltar();
            }
        });
    }

    private void Voltar(){
        Intent intent = new Intent(Home.this, Enterprises.class);
        startActivity(intent);
        finish();
    }

    protected void Deslogar(){
        Intent intent = new Intent(Home.this, Mainactivity.class);
        startActivity(intent);
        finish();
    }

    private void verificaEmpresaSelecionada(){
        TextView textView = (TextView) findViewById(R.id.txtEmpresaSelecionada);

        SharedPreferences sharedPreferences = getSharedPreferences("estimaprime", Context.MODE_PRIVATE);
        Enterprise = sharedPreferences.getInt("GLO_ENTERPRISE",Id_Enteprise);

        if(Enterprise<=0){
            Toast toast = Toast.makeText(this, "Empresa não encontrada! " +Enterprise, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
        }else{
            textView.setText("Código da Empresa: " +Enterprise);
        }
    }

    private void preencherNotas(){
        NoteDAO n1 = new NoteDAO(getApplicationContext());
        Double Entradas = n1.getEntradas(Enterprise);
        NoteDAO n2 = new NoteDAO(getApplicationContext());
        Double Saidas = n2.getSaidas(Enterprise);
        Double Total = Entradas-Saidas;

        String[] types = {"Entrada - R$ "+Entradas,"Saída - R$ -"+Saidas,"Total -   R$"+Total};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,types){
            @Override
            public View getView (int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                // Como o simple_list_item_1 retorna um TextView, esse cast pode ser feito sem problemas
                if (position == 0)
                    ((TextView) view).setTextColor(Color.GREEN);
                else if (position == 1)
                    ((TextView) view).setTextColor(Color.RED);
                else if (position == 2)
                    ((TextView) view).setTextColor(Color.BLUE);

                return view;
            }
        };
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    protected void addNote(){
        Intent intent = new Intent(Home.this, AddNote.class);
        startActivity(intent);
    }
}
