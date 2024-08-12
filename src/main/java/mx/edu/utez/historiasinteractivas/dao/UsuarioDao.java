package mx.edu.utez.historiasinteractivas.dao;

import mx.edu.utez.historiasinteractivas.model.User;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    // Crear usuario en la base de datos
    public boolean insert(User u) {
        String query = "INSERT INTO historiasInteractivas.Users(email, password) VALUES(?, SHA2(?, 256))";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros de entrada
            stmt.setString(1, u.getEmail());
            stmt.setString(2, u.getPassword());

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

    public User getUser(String email,String password) {
        User usuario = null;
        String sql = "call loginUser(?, ?)";

        try (Connection con = DatabaseConnectionManager.getConnection();
             CallableStatement statement = con.prepareCall(sql)) {

            // Establecer los parámetros de entrada
            statement.setString(1, email);
            statement.setString(2, password);
            statement.execute();

            // Ejecutar la consulta
            try (ResultSet rs = statement.getResultSet()) {
                if (rs.next()) {
                    usuario = new User();
                    usuario.setEmail(rs.getString("email"));
                    usuario.setUser(rs.getString("user"));
                    usuario.setName(rs.getString("name"));
                    usuario.setPaternalSurname(rs.getString("paternal_surname"));
                    usuario.setMaternalSurname(rs.getString("maternal_surname"));
                    usuario.setProfilePicture(rs.getString("profile_picture"));
                    usuario.setStatus(rs.getBoolean("status"));
                    usuario.setAdmin(rs.getBoolean("admin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean existsUser(String email, String password) {
        String sql = "select * from historiasInteractivas.Users where email = ? and password = ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            // Establecer los parámetros de entrada
            statement.setString(1, email);
            statement.setString(2, password);

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

    public boolean saveChangePasswordToken(String email, String token) {
        String sql = "UPDATE historiasInteractivas.Users SET change_password_token = ? WHERE email = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Establecer los parámetros de entrada
            stmt.setString(1, token);
            stmt.setString(2, email);

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
        String sql = "Select change_password_token from historiasInteractivas.Users where email = ?";
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
        String sql = "UPDATE historiasInteractivas.Users SET password = sha2(?,256) WHERE email = ?";
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


    public ArrayList<User> getAllUsers() {
        ArrayList<User> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM historiasInteractivas.Users";
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
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public User findUserByEmail(String email) {
        User usuario = null;
        String sql = "SELECT * FROM historiasInteractivas.Users WHERE email = ?";
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
                    usuario.setProfilePicture(rs.getString("profile_picture"));
                    usuario.setAdmin(rs.getBoolean("admin"));
                    usuario.setStatus(rs.getBoolean("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean disableUserByEmail(String email) {
        String sql = "UPDATE historiasInteractivas.Users SET status = false WHERE email = ?";
        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             stmt.setString(1, email);
             return stmt.executeUpdate() > 0;
        } catch (Exception e) {
             e.printStackTrace();
             return false;
        }
    }

    public boolean updateUser(User user) {
        String query = "UPDATE historiasInteractivas.Users SET user = ?, name = ?, paternal_surname = ?, maternal_surname = ?, profile_picture = ? WHERE email = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getUser());
            statement.setString(2, user.getName());
            statement.setString(3, user.getPaternalSurname());
            statement.setString(4, user.getMaternalSurname());
            statement.setString(5, user.getProfilePicture());
            statement.setString(6, user.getEmail());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean enableUserByEmail(String email) {
        try (Connection con = DatabaseConnectionManager.getConnection()) {
            String query = "UPDATE historiasInteractivas.Users SET status = true WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public ArrayList<User> findAllUsersByEmail(String email) {
        ArrayList<User> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM historiasInteractivas.Users WHERE email LIKE ?";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {

            statement.setString(1, email + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User usuario = new User();
                    usuario.setEmail(rs.getString("email"));
                    usuario.setUser(rs.getString("user"));
                    usuario.setName(rs.getString("name"));
                    usuario.setPaternalSurname(rs.getString("paternal_surname"));
                    usuario.setMaternalSurname(rs.getString("maternal_surname"));
                    usuario.setStatus(rs.getBoolean("status"));
                    usuario.setProfilePicture(rs.getString("profile_picture"));
                    usuarios.add(usuario);  // Agrega cada usuario a la lista
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
    public String getAdminEmail() {
        String email = "";
        String query = "SELECT email FROM historiasInteractivas.Users WHERE admin = true ORDER BY RAND() LIMIT 1";

        try (Connection con = DatabaseConnectionManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                email = rs.getString("email");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return email != null && !email.isEmpty() ? email : "admin@historiainteractivas.mx";
    }
}
