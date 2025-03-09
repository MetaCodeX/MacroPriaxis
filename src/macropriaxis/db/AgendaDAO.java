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
} 