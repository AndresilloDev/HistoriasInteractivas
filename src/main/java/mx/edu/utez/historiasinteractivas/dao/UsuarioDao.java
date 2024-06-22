package mx.edu.utez.historiasinteractivas.dao;

import mx.edu.utez.historiasinteractivas.model.Usuario;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    public boolean insert(Usuario u) {
        boolean flag = false;
        String query = "insert into Usuario(email, name, first_last_name, last_last_name, password, user) values (?, ?, ?, ?, sha2(?, 64), ?);";  // Cambio resaltado
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

    public Usuario getOne(String email, String name, String first_last_name, String last_last_name, String password, String user) {
        Usuario usuario = new Usuario();
        String query = "select * from Usuario where email = ? and name = ? and first_last_name = ? and last_last_name = ? and password = ? and user = ?;";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, name);
            ps.setString(3, first_last_name);
            ps.setString(4, last_last_name);
            ps.setString(5, password);
            ps.setString(6, user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setName(rs.getString("nombre"));
                usuario.setPassword(rs.getString("contra"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public boolean existsUser(Usuario user) {
        return true;
    }
}
