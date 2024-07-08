package mx.edu.utez.historiasinteractivas.model;

public class User {

    private String id_user;
    private String user;
    private String email;
    private int user_type;
    private String token;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String password;
    private boolean status;
    public User() {
    }
    //


    public User(String email, int user_type, String token, String name, String paternalSurname, String maternalSurname, String password, String user, boolean status) {
        this.email = email;
        this.user_type = user_type;
        this.token = token;
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.password = password;
        this.user = user;
        this.status = status;
    }

    public User(String email, String user, String password, String maternalSurname, String paternalSurname, String name, String token) {
        this.email = email;
        this.user = user;
        this.password = password;
        this.maternalSurname = maternalSurname;
        this.paternalSurname = paternalSurname;
        this.name = name;
        this.token = token;
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaternalSurname() {
        return paternalSurname;
    }

    public void setPaternalSurname(String paternalSurname) {
        this.paternalSurname = paternalSurname;
    }

    public String getMaternalSurname() {
        return maternalSurname;
    }

    public void setMaternalSurname(String maternalSurname) {
        this.maternalSurname = maternalSurname;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
