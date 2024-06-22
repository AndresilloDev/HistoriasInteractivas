package mx.edu.utez.historiasinteractivas.dao;

import mx.edu.utez.historiasinteractivas.model.Usuario;
import mx.edu.utez.historiasinteractivas.utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    //CRUD para usuario
    //Read para un usuario
    public Usuario getOne(String nombre, String contra) {
        Usuario usuario = new Usuario();
        String query = "select * from Usuario where nombre = ? and contra = ?;";
        try {
            Connection con = DatabaseConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, contra);
            ps.getResultSet();
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setNombre(rs.getString("nombre"));
                usuario.setContra(rs.getString("contra"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public String insert(Usuario user){

        return "";
    }
}
