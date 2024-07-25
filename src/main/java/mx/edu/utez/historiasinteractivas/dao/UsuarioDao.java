package mx.edu.utez.historiasinteractivas.dao;

import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "SELECT * FROM USERS WHERE email = ? OR user = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            // Establecer los parámetros de entrada
            statement.setString(1, u.getEmail());
            statement.setString(2, u.getEmail());

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

    public boolean saveChangePasswordToken(User u, String token) {
        String sql = "UPDATE users SET change_password_token = ? WHERE email = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros de entrada
            stmt.setString(1, token);
            stmt.setString(2, u.getEmail());

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

    public String getPasswordToken(User user) {
        String sql = "Select change_password_token from users where email = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            // Establecer los parámetros de entrada
            statement.setString(1, user.getEmail());

            // Ejecutar la consulta
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("change_password_token");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean updatePassword(User u, String password) {
        String sql = "UPDATE users SET password = sha2(?,256) WHERE email = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros de entrada
            stmt.setString(1, password);
            stmt.setString(2, u.getEmail());

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


    public List<User> getAllUsers() {
        List<User> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USERS";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                User usuario = new User();
                usuario.setEmail(rs.getString("email"));
                usuario.setUser(rs.getString("user"));
                usuario.setName(rs.getString("name"));
                usuario.setPaternalSurname(rs.getString("paternal_surname"));
                usuario.setMaternalSurname(rs.getString("maternal_surname"));
                usuario.setStatus(rs.getBoolean("status"));
                usuario.setUser_type(rs.getBoolean("admin") ? 1 : 0);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public User findUserByEmail(String email) {
        User usuario = null;
        String sql = "SELECT * FROM USERS WHERE email = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email);

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

    public boolean disableUserByEmail(String email) {
        boolean resultado = false;
        String sql = "UPDATE USERS SET status = false WHERE email = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);

            int affectedRows = stmt.executeUpdate();
            resultado = affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public boolean updateUser(User u) {
        String sql = "UPDATE users SET user = ?, name = ?, paternal_surname = ?, maternal_surname = ? WHERE email = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            // Establecer los parámetros de entrada
            stmt.setString(1, u.getUser());
            stmt.setString(2, u.getName());
            stmt.setString(3, u.getPaternalSurname());
            stmt.setString(4, u.getMaternalSurname());
            stmt.setString(5, u.getEmail());

            // Ejecutar la actualización
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
