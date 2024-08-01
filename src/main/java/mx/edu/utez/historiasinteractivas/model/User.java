package mx.edu.utez.historiasinteractivas.model;

public class User {
    private String email;
    private String user;
    private String token;
    private String change_password_token;
    private String name;
    private String paternalSurname;
    private String maternalSurname;
    private String profilePicture;
    private String password;
    private boolean status;
    private boolean admin;

    public User() {
    }

    public User(String email, String name, String paternalSurname, String maternalSurname, String password, String user) {
        this.email = email;
        this.name = name;
        this.paternalSurname = paternalSurname;
        this.maternalSurname = maternalSurname;
        this.password = password;
        this.user = user;
        this.status = true;
    }
    //Constructor para registro de usuario
    //Constructor para el login
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Constructor para la recuperación de contraseña
    public User(String email) {
        this.email = email;
        this.user = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChange_password_token() {
        return change_password_token;
    }

    public void setChange_password_token(String change_password_token) {
        this.change_password_token = change_password_token;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
