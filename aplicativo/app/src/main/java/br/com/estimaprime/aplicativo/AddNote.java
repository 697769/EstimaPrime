package br.com.estimaprime.aplicativo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import dao.NoteDAO;
import modelo.Note;

public class AddNote extends AppCompatActivity {
    private Spinner NoteTypes;
    private Button btnAddNote;
    private EditText edtValorNote;
    private String TipoEscolhido = "1";
    private Double ValorNote;
    public static final int Id_Enteprise=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        btnAddNote = (Button) findViewById(R.id.btnAddEnterprise);
        edtValorNote = (EditText) findViewById(R.id.txtNoteValor);
        NoteTypes = (Spinner) findViewById(R.id.spinTypes);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.TiposNotes, android.R.layout.simple_spinner_item);
        NoteTypes.setAdapter(adapter);

        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!NoteTypes.getSelectedItem().toString().equals("")) {
                    TipoEscolhido = NoteTypes.getSelectedItem().toString();
                }
                if (edtValorNote.getText().toString().equals("") || TipoEscolhido.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Informe um Valor e Tipo Válidos.", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    ValorNote = Double.parseDouble(edtValorNote.getText().toString());

                    if (adicionarNote()){
                        Toast toast = Toast.makeText(getApplicationContext(), "Nota adicionada com sucesso!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                        toast.show();
                        Voltar();
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "Operação ou Empresa invalida!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });
    }
    protected boolean adicionarNote(){
        SharedPreferences sharedPreferences = getSharedPreferences("estimaprime", Context.MODE_PRIVATE);
        int Enterprise = sharedPreferences.getInt("GLO_ENTERPRISE",Id_Enteprise);

        if (Enterprise > 0) {
            Note n = new Note();
            n.setId_enterprise(Enterprise);
            n.setValor(ValorNote);
            n.setTipo(Integer.parseInt(TipoEscolhido.substring(0, 1)));

            NoteDAO nDao = new NoteDAO(getApplicationContext());
            if (nDao.insertNote(n)) {
                return true;
            }
        }
        else {
                return false;
            }
        return false;
    }

    protected void Voltar(){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
    }
}
