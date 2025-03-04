package macropriaxis.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellidos, fecha_nacimiento, ciudad, telefono, direccion, email, matricula, carrera) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, usuario.getApellidos());
            pstmt.setString(3, usuario.getFechaNacimiento());
            pstmt.setString(4, usuario.getCiudad());
            pstmt.setString(5, usuario.getTelefono());
            pstmt.setString(6, usuario.getDireccion());
            pstmt.setString(7, usuario.getEmail());
            pstmt.setString(8, usuario.getMatricula());
            pstmt.setString(9, usuario.getCarrera());
            
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public List<Usuario> buscarPorNombre(String nombre) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nombre LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + nombre + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("ciudad"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getString("carrera")
                    );
                    usuario.setId(rs.getInt("id"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por nombre: " + e.getMessage());
        }
        return usuarios;
    }

    public List<Usuario> buscarPorId(int id) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("ciudad"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getString("carrera")
                    );
                    usuario.setId(rs.getInt("id"));
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar por ID: " + e.getMessage());
        }
        return usuarios;
    }
} 