package macropriaxis.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {
    
    public static class Agenda {
        private int id;
        private String asunto;
        private Date fecha;
        private Time hora;
        private String anotaciones;
        
        public Agenda(int id, String asunto, Date fecha, Time hora, String anotaciones) {
            this.id = id;
            this.asunto = asunto;
            this.fecha = fecha;
            this.hora = hora;
            this.anotaciones = anotaciones;
        }
        
        // Getters
        public int getId() { return id; }
        public String getAsunto() { return asunto; }
        public Date getFecha() { return fecha; }
        public Time getHora() { return hora; }
        public String getAnotaciones() { return anotaciones; }
    }
    
    public static void insertarAgenda(String asunto, Date fecha, Time hora, String anotaciones) throws SQLException {
        String sql = "INSERT INTO Agenda (Asunto, Fecha, Hora, Anotaciones) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, asunto);
            pstmt.setDate(2, fecha);
            pstmt.setTime(3, hora);
            pstmt.setString(4, anotaciones);
            pstmt.executeUpdate();
        }
    }
    
    public static List<Agenda> obtenerTodasLasAgendas() throws SQLException {
        List<Agenda> agendas = new ArrayList<>();
        String sql = "SELECT * FROM Agenda ORDER BY ID ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                agendas.add(new Agenda(
                    rs.getInt("ID"),
                    rs.getString("Asunto"),
                    rs.getDate("Fecha"),
                    rs.getTime("Hora"),
                    rs.getString("Anotaciones")
                ));
            }
        }
        return agendas;
    }

    // Nuevo método para insertar agenda con usuario_id
    public static void insertarAgenda(int usuarioId, String asunto, Date fecha, Time hora, String anotaciones) throws SQLException {
        String sql = "INSERT INTO Agenda (usuario_id, Asunto, Fecha, Hora, Anotaciones) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            pstmt.setString(2, asunto);
            pstmt.setDate(3, fecha);
            pstmt.setTime(4, hora);
            pstmt.setString(5, anotaciones);
            pstmt.executeUpdate();
        }
    }

    // Nuevo método para obtener agendas por usuario
    public static List<Agenda> obtenerAgendasPorUsuario(int usuarioId) throws SQLException {
        List<Agenda> agendas = new ArrayList<>();
        String sql = "SELECT * FROM Agenda WHERE usuario_id = ? ORDER BY ID ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, usuarioId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    agendas.add(new Agenda(
                        rs.getInt("ID"),
                        rs.getString("Asunto"),
                        rs.getDate("Fecha"),
                        rs.getTime("Hora"),
                        rs.getString("Anotaciones")
                    ));
                }
            }
        }
        return agendas;
    }

    // Método para modificar una agenda
    public static void modificarAgenda(int id, int usuarioId, String asunto, Date fecha, Time hora, String anotaciones) throws SQLException {
        String sql = "UPDATE Agenda SET Asunto=?, Fecha=?, Hora=?, Anotaciones=? WHERE ID=? AND usuario_id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, asunto);
            pstmt.setDate(2, fecha);
            pstmt.setTime(3, hora);
            pstmt.setString(4, anotaciones);
            pstmt.setInt(5, id);
            pstmt.setInt(6, usuarioId);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar una agenda
    public static void eliminarAgenda(int id) throws SQLException {
        String sql = "DELETE FROM Agenda WHERE ID=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
} 