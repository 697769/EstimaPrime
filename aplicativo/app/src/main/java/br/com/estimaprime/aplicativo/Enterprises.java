package br.com.estimaprime.aplicativo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import DAO.Enterprise;

public class Enterprises extends Activity {

    //DATA SECTION
    private Button btn_enteprises_select_enterprise,btn_enterprises_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //DEFAULT DECLARATIONS
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprises);
        //CHANGES
        btn_enteprises_select_enterprise = (Button) findViewById(R.id.btn_enteprises_select_enterprise);
        btn_enterprises_exit = (Button) findViewById(R.id.btn_enterprises_exit);
        //METHODS
        // Lista de Empresas
        //ArrayList<String> enterprise = new ArrayList<String>();
        ArrayList<String> enterpriseList = null;
        //DBManager dbManager = new DBManager(this);
        Enterprise enterprise = new Enterprise(this);
        //limpar as empresas teste do banco
        enterprise.deleteAllEnterprises();
        //adicionar empresas no banco
        for (int i = 0; i < 5; i++){
            enterprise.addEnterprise("Empresa " + i, 1);
        }
        //Retornar as empresas do banco
        enterpriseList = enterprise.getAllEnterprises();
        // Adicionar as empresas no list
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,enterpriseList);
        ListView listView = (ListView) findViewById(R.id.listView_enterprises);
        listView.setAdapter(adapter);

        //------------------------------------------------------

        btn_enteprises_select_enterprise.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Enterprises.this, Home.class);
                startActivity(intent);
            }
        });
        btn_enterprises_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Enterprises.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
