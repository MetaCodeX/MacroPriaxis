package macropriaxis.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
   
    private static final String DB_URL = "jdbc:mysql://bcpnowq7pabjlbbgb7wd-mysql.services.clever-cloud.com:3306/bcpnowq7pabjlbbgb7wd";
    private static final String USER = "uy0cgyxczquif8jz";
    private static final String PASSWORD = "7vC6uk6kVnV1ylHG0lrV"; 
    
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver no encontrado.", e);
        }
    }
    
    public static void testConnection() throws SQLException {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Conexión exitosa a la base de datos");
            } else {
                throw new SQLException("❌ Fallo en la conexión");
            }
        } catch (SQLException e) {
            System.err.println("🔥 Error detallado: " + e.getMessage());
            throw e;
        }
    }
}