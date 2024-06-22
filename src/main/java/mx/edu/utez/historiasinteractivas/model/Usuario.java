package mx.edu.utez.historiasinteractivas.model;

public class Usuario {
    private String user;
    private String name;
    private String password;
    private String email;


    public Usuario() {
    }

    public Usuario(String user, String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.user = user;
        this.email = email;
    }

    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
