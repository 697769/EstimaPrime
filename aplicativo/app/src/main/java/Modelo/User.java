/*
 * Copyright EstimaPrime(c) 2016 - By Victor Sodré 528803
 */

package modelo;

public class User {

    // TODO: 02/06/2016 = ATRIBUTES
    private long id_user;
    private String email,password;

    // TODO: 02/06/2016 = GETTERS
    public long getIdUser() { return id_user; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // TODO: 02/06/2016 = SETTERS
    public void setIdUser(long id_user) { this.id_user = id_user; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
}
