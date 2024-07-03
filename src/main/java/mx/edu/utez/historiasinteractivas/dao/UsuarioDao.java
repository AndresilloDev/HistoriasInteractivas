package mx.edu.utez.historiasinteractivas.dao;


import mx.edu.utez.historiasinteractivas.model.Users;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.sql.*;

public class UsuarioDao {

    // Crear usuario en la base de datos
    public boolean insert(Users usuario) {
        boolean flag = false;
        String query = "call insertUser(?,?,?,?,?,?,?)";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            CallableStatement stmt = con.prepareCall(query);
            stmt.setString(1,usuario.getEmail());
            stmt.setString(2,usuario.getUser());
            stmt.setString(3,usuario.getToken());
            stmt.setString(4,usuario.getName());
            stmt.setString(5,usuario.getFirst_last_name());
            stmt.setString(6,usuario.getLast_last_name());
            stmt.setString(7,usuario.getPassword());
            stmt.execute();
            final ResultSet rs = stmt.getResultSet();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    // Seleccionar usuario de la base de datos
    public Users getUserFromEmailANDPassword(String email, String password){
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
                usuario.setUser_type(rs.getInt("user_type"));
                usuario.setToken(rs.getString("token"));
                usuario.setName(rs.getString("name"));
                usuario.setFirst_last_name(rs.getString("first_last_name"));
                usuario.setLast_last_name(rs.getString("last_last_name"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUser(rs.getString("user"));
                usuario.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    //Obtener usuario a traves de su correo y token
    public Users getUserFromEmailANDToken(String email, String token){
        Users usuario = new Users();
        String query = "select * from Users where email = ? and token = ?;";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,email);
            ps.setString(2,token);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                usuario.setEmail(rs.getString("email"));
                //usuario.setUser_type(rs.getInt("user_type"));
                usuario.setToken(rs.getString("token"));
                //usuario.setName(rs.getString("name"));
                //usuario.setFirst_last_name(rs.getString("first_last_name"));
                //usuario.setLast_last_name(rs.getString("last_last_name"));
                usuario.setPassword(rs.getString("password"));
                //usuario.setUser(rs.getString("user"));
                //usuario.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public boolean updateUserPassword(String email, String password){
        boolean flag = false;
        String query = "update users set password = ? where email = ?";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,password);
            ps.setString(2,email);

            if (ps.executeUpdate() > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return flag;
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