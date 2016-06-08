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

import Controle.Enterprise;

public class Enterprises extends Activity {

    //DATA SECTION
    private Button btn_enterprises_exit;
    public static final int Id_Usuario=0;
    Controle.Enterprise Enterprise = new Enterprise(this);
    ArrayList<String> enterpriseList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprises);

        btn_enterprises_exit = (Button) findViewById(R.id.btn_enterprises_exit);

        //Retornar as empresas do banco
            //enterpriseList = enterprise.getAllEnterprises();
            SharedPreferences sharedPreferences = getSharedPreferences("estimaprime", Context.MODE_PRIVATE);
            int UsuarioLogado = sharedPreferences.getInt("GLO_USUARIO",Id_Usuario);
            if (UsuarioLogado <= 0){
                Toast toast = Toast.makeText(Enterprises.this, "Usuário não encontrado! "+UsuarioLogado, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                toast.show();
            } else {
//                  enterpriseList = enterprise.getAllEnterprises();
                //enterpriseList = Enterprise.getUserEnterprises(UsuarioLogado);

                if (enterpriseList==null){
                    Toast toast = Toast.makeText(Enterprises.this, "Nenhuma empresa encontrada!", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    // Adicionar as empresas no list
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,enterpriseList);
                    final ListView listView = (ListView) findViewById(R.id.listView_enterprises);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(
                            new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String empresa = (String)listView.getItemAtPosition(position);
                                    //Salva a empresa selecionada
                                    SharedPreferences sharedPreferences=getSharedPreferences("estimaprime", Context.MODE_PRIVATE); //salvar em modo privado!
                                    SharedPreferences.Editor editor = sharedPreferences.edit(); //declarar o editor

                                    //editor.putInt("GLO_ENTERPRISE", Enterprise.getIdEnterprise(Integer.parseInt(empresa.substring(0,1)))); //salvar a enterprise selecionada

                                    editor.commit(); //salvar dados no arquivo
                                    //Chama a tela da empresa
                                    Intent intent = new Intent(Enterprises.this, Home.class);
                                    startActivity(intent);
                                }
                            }
                    );
                }
            }
        btn_enterprises_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Enterprises.this, Mainactivity.class);
                startActivity(intent);
            }
        });
    }

    private void enterpriseSelection(){
   }
}