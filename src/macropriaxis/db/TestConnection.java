/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package macropriaxis.db;

import java.sql.SQLException;

/**
 *
 * @author juanm
 */
public class TestConnection {
    
    public static void main(String[] args) {
        System.out.println("Iniciando prueba de conexión...");
        
        try {
            // Intenta establecer la conexión
            DatabaseConnection.testConnection();
        } catch (SQLException e) {
            // Manejo de errores detallado
            System.err.println("\n--- Error crítico en la conexión ---");
            System.err.println("Mensaje SQL: " + e.getMessage());
            System.err.println("Código de error: " + e.getErrorCode());
            System.err.println("Estado SQL: " + e.getSQLState());
            e.printStackTrace();
        }
    }
}