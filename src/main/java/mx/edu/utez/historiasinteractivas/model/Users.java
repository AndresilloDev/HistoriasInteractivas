package mx.edu.utez.historiasinteractivas.model;

public class Users {

    private String email;
    private int user_type;
    private String token;
    private String name;
    private String first_last_name;
    private String last_last_name;
    private String password;
    private String user;
    private boolean status;
    public Users() {
    }
    //


    public Users(String email, int user_type, String token, String name, String first_last_name, String last_last_name, String password, String user, boolean status) {
        this.email = email;
        this.user_type = user_type;
        this.token = token;
        this.name = name;
        this.first_last_name = first_last_name;
        this.last_last_name = last_last_name;
        this.password = password;
        this.user = user;
        this.status = status;
    }

    public Users(String email, String user, String password, String last_last_name, String first_last_name, String name, String token) {
        this.email = email;
        this.user = user;
        this.password = password;
        this.last_last_name = last_last_name;
        this.first_last_name = first_last_name;
        this.name = name;
        this.token = token;
    }

    public Users(String password, String email) {
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
