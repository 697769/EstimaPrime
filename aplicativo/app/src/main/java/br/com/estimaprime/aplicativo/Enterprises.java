package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import dao.EnterpriseDAO;

public class Enterprises extends Activity {

    private Button btnEnterprisesExit, btnAddEnterprise;
    private int IdUsuario=0;
    private ArrayList<String> enterpriseList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprises);

        btnAddEnterprise = (Button) findViewById(R.id.btnEnterprisesAddEnterprise);
        btnEnterprisesExit = (Button) findViewById(R.id.btn_enterprises_exit);

        carregarEmpresasDoUsuario();

        btnAddEnterprise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Enterprises.this, AddEnterprise.class);
                startActivity(intent);
            }
        });
        btnEnterprisesExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Enterprises.this, Mainactivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    protected void carregarEmpresasDoUsuario(){
        SharedPreferences sharedPreferences = getSharedPreferences("estimaprime", Context.MODE_PRIVATE);
        int UsuarioLogado = sharedPreferences.getInt("GLO_USUARIO",IdUsuario);

        if (UsuarioLogado <= 0){
            Toast toast = Toast.makeText(Enterprises.this, "Usuário não encontrado! "+UsuarioLogado, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
            toast.show();
        } else {

            EnterpriseDAO empDAO = new EnterpriseDAO(getApplicationContext());
            enterpriseList = empDAO.getUserEnterprises(UsuarioLogado);

            if (enterpriseList==null){
                Toast toast = Toast.makeText(Enterprises.this, "Nenhuma empresa encontrada!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,enterpriseList);
                    final ListView listView = (ListView) findViewById(R.id.listView_enterprises);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String empresa = (String)listView.getItemAtPosition(position);

                                    SalvarEmpresaSelecionada(empresa);

                                    StartHome();
                                }
                            }
                    );
            }
        }
    }

    protected void SalvarEmpresaSelecionada(String emp){
        //Salva a empresa selecionada
        SharedPreferences sharedPreferences=getSharedPreferences("estimaprime", Context.MODE_PRIVATE); //salvar em modo privado!
        SharedPreferences.Editor editor = sharedPreferences.edit(); //declarar o editor
        EnterpriseDAO empDAO2 = new EnterpriseDAO(getApplicationContext());
        editor.putInt("GLO_ENTERPRISE", empDAO2.getIdEnterprise(Integer.parseInt(emp.substring(0,1)))); //salvar a enterprise selecionada//
        editor.commit(); //salvar dados no arquivo
    }

    protected void StartHome(){
        Intent intent = new Intent(Enterprises.this, Home.class);
        startActivity(intent);
    }
}