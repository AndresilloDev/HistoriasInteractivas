package mx.edu.utez.historiasinteractivas.dao;


import mx.edu.utez.historiasinteractivas.model.Users;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.sql.*;

public class UsuarioDao {

    // Crear usuario en la base de datos
    public boolean insert(Users u) {
        boolean flag = false;
        String query = "insert into Users(email, name, first_last_name, last_last_name, password, user) values (?, ?, ?, ?, sha2(?, 64), ?);";  // Cambio resaltado
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getEmail());
            ps.setString(2, u.getName());
            ps.setString(3, u.getFirst_last_name());
            ps.setString(4, u.getLast_last_name());
            ps.setString(5, u.getPassword());
            ps.setString(6, u.getUser());
            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // Seleccionar usuario de la base de datos
    public Users getOne(String email, String password){
        Users usuario = new Users();
        String query = "select * from Users where email = ? and password = sha2(?,64);";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Users existsUser(String email, String password) {
        Users usuario = new Users();
        String query ="call seekUser(?,?)";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            CallableStatement stmt = con.prepareCall(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.execute();
            final ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                usuario.setStatus(rs.getBoolean("status"));
                usuario.setUser_type(rs.getInt("user_type"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return usuario;
    }
}