/*
 * Copyright EstimaPrime(c) 2016 - By Victor Sodré 528803
 */

package Modelo;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Victor on 29/05/2016.
 */
public class NoteM{

    // TODO: 04/06/2016 = ATRIBUTES
    private int id_note,id_enterprise,tipo;
    private String data;
    private double valor;

    public NoteM() { this.data = DateFormat.getDateInstance().format(new Date()); }

    //TODO: 04/06/2016 - GETTERS
    public int getId_note() { return id_note; }
    public int getId_enterprise() { return id_enterprise; }
    public int getTipo() { return tipo; }
    public String getData() { return data; }
    public double getValor() { return valor; }

    //TODO: 04/06/2016 - SETTERS
    public void setId_note(int id_note) { this.id_note = id_note; }
    public void setId_enterprise(int id_enterprise) { this.id_enterprise = id_enterprise; }
    public void setTipo(int tipo) { this.tipo = tipo; }
    public void setData(String data) { this.data = data; }
    public void setValor(double valor) { this.valor = valor; }
}
