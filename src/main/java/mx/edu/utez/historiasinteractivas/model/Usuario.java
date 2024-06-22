package mx.edu.utez.historiasinteractivas.model;

public class Usuario {

    private String email;
    private String name;
    private String first_last_name;
    private String last_last_name;
    private String password;
    private String user;
    public Usuario() {
    }
    //

    public Usuario(String email, String name, String first_last_name, String last_last_name, String password, String user) {
        this.email = email;
        this.name = name;
        this.first_last_name = first_last_name;
        this.last_last_name = last_last_name;
        this.password = password;
        this.user = user;
    }

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_last_name() {
        return first_last_name;
    }

    public void setFirst_last_name(String first_last_name) {
        this.first_last_name = first_last_name;
    }

    public String getLast_last_name() {
        return last_last_name;
    }

    public void setLast_last_name(String last_last_name) {
        this.last_last_name = last_last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
