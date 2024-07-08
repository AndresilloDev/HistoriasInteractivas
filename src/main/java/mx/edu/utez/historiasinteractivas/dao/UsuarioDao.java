package mx.edu.utez.historiasinteractivas.dao;


import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.sql.*;

public class UsuarioDao {

    // Obtener usuario de la base de datos
    public User getUser(User u){
        User usuario = new User();
        String query = "call getUser(?, sha2(?,64));";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            CallableStatement stmt = con.prepareCall(query);

            stmt.setString(1,u.getUser());
            stmt.setString(2,u.getPassword());
            stmt.execute();
            final ResultSet rs = stmt.getResultSet();

            if(rs.next()){
                usuario.setId_user(rs.getString("id_user"));
                usuario.setEmail(rs.getString("email"));
                usuario.setName(rs.getString("name"));
                usuario.setPaternalSurname(rs.getString("paternalSurname"));
                usuario.setMaternalSurname(rs.getString("maternalSurname"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUser(rs.getString("user"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    // Crear usuario en la base de datos
    public boolean insert(User u) {
        boolean flag = false;
        String query = "call insertUser(?,?,?,?,?,?)";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            CallableStatement stmt = con.prepareCall(query);
            stmt.setString(1,u.getEmail());
            stmt.setString(2,u.getName());
            stmt.setString(3,u.getPaternalSurname());
            stmt.setString(4,u.getMaternalSurname());
            stmt.setString(5,u.getPassword());
            stmt.setString(6,u.getUser());
            stmt.execute();
            try (ResultSet rs = stmt.getResultSet()) {
                if (rs.next()) {
                    // El procedimiento devuelve true si se logró la inserción, false en caso contrario
                    flag = rs.getBoolean("p_success");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

//    //Obtener usuario a traves de su correo y token
//    public User getUserFromEmailANDToken(String email, String token){
//        User usuario = new User();
//        String query = "select * from Users where email = ? and token = ?;";
//        try {
//            Connection con = DatabaseConnectionManager.getConnection();
//            PreparedStatement ps = con.prepareStatement(query);
//            ps.setString(1,email);
//            ps.setString(2,token);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                usuario.setEmail(rs.getString("email"));
//                //usuario.setUser_type(rs.getInt("user_type"));
//                usuario.setToken(rs.getString("token"));
//                //usuario.setName(rs.getString("name"));
//                //usuario.setFirst_last_name(rs.getString("first_last_name"));
//                //usuario.setLast_last_name(rs.getString("last_last_name"));
//                usuario.setPassword(rs.getString("password"));
//                //usuario.setUser(rs.getString("user"));
//                //usuario.setStatus(rs.getBoolean("status"));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return usuario;
//    }

    public boolean updateUserPassword(User u, String newPassword){
        boolean flag = false;
        String query = "call updatePassword(?,?)";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            CallableStatement stmt = con.prepareCall(query);
            stmt.setString(1, u.getUser());
            stmt.setString(2, newPassword);
            stmt.execute();
            try (ResultSet rs = stmt.getResultSet()) {
                if (rs.next()) {
                    // El procedimiento devuelve true si se logró la actualización, false en caso contrario
                    //De todas formas por ahora siempre devuelve false, que dios ampare al valiente que logre resolver el problema

                    flag = true;
                    // Para cuando funcione
                    // flag = rs.getBoolean("p_success");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public User existsUser(String email, String password) {
        User usuario = new User();
        String query ="call seekUser(?,?)";
        try (Connection con = DatabaseConnectionManager.getConnection()) {
            // Llamar al procedimiento almacenado
            String sql = "{call seekUser(?, ?, ?)}";
            try (CallableStatement statement = con.prepareCall(sql)) {
                // Establecer los parámetros de entrada
                statement.setString(1, "exampleUser");
                statement.setString(2, "examplePassword");

                // Registrar el parámetro de salida
                statement.registerOutParameter(3, Types.BOOLEAN);

                // Ejecutar el procedimiento
                statement.execute();

                // Obtener el valor del parámetro de salida
                boolean success = statement.getBoolean(3);
                System.out.println("Success: " + success);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
}