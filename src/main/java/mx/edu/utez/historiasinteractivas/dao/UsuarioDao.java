package mx.edu.utez.historiasinteractivas.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.sql.*;

public class UsuarioDao {

    // Crear usuario en la base de datos
    public boolean insert(User u) {
        String query = "INSERT INTO users(email, user, password) VALUES(?, ?, SHA2(?, 256))";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros de entrada
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getUser());
            stmt.setString(3, u.getPassword());

            // Ejecutar la inserción
            int affectedRows = stmt.executeUpdate();
            System.out.println("Affected rows: " + affectedRows);

            // Verificar si se insertó una fila
            if (affectedRows > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


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

    public User getUser(User u) {
        User usuario = null;
        String sql = "SELECT * FROM USERS WHERE (email = ? OR user = ?) AND password = SHA2(?, 256)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            // Establecer los parámetros de entrada
            statement.setString(1, u.getEmail());
            statement.setString(2, u.getEmail());
            statement.setString(3, u.getPassword());

            // Ejecutar la consulta
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    usuario = new User();
                    usuario.setEmail(rs.getString("email"));
                    usuario.setUser(rs.getString("user"));
                    usuario.setName(rs.getString("name"));
                    usuario.setPaternalSurname(rs.getString("paternal_surname"));
                    usuario.setMaternalSurname(rs.getString("maternal_surname"));
                    usuario.setStatus(rs.getBoolean("status"));
                    usuario.setUser_type(rs.getBoolean("admin") ? 1 : 0);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean existsUser(User u) {
        User usuario = null;
        String sql = "SELECT * FROM USERS WHERE (email = ? OR user = ?) AND password = SHA2(?, 256)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            // Establecer los parámetros de entrada
            statement.setString(1, u.getEmail());
            statement.setString(2, u.getEmail());
            statement.setString(3, u.getPassword());

            // Ejecutar la consulta
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}