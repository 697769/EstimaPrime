/*
 * Copyright EstimaPrime(c) 2016 - By Victor Sodré 528803
 */

package modelo;

public class Enterprise {
    // TODO: 29/10/2016 = ATRIBUTES
    private long id,id_usuario;
    private String name;
    //TODO: 04/06/2016 - GETTERS
    public long getId() { return id; }
    public long getId_usuario() { return id_usuario; }
    public String getName() { return name; }
    //TODO: 04/06/2016 - SETTERS
    public void setId(long id) { this.id = id; }
    public void setId_usuario(long id_usuario) { this.id_usuario = id_usuario; }
    public void setName(String name) { this.name = name; }
}
