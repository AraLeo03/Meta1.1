/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uabc.meta1_1ayd;
import java.sql.*;
import java.util.ArrayList;

/**
 * Programa que implementa la funcionalidad de altas, bajas, modificaciones y 
 * cambios de los datos de las tablas Personas y Telefonos. 
 * Utilizar Java FX para las interfaces de usuario.
 * @author Dell
 */
public class AdministradorAgenda {
    
    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "superpassword";
    
//    public static void main(String[] args) {
//        mostrarTablas();
//        altaPersona("Leo Dan", "Av Lombardo");
//        //mostrarTablas();
//        //bajaPersona(5);
//        mostrarTablas();
//        //altaTelefono("2", "686-3895882");
//    }
    
    public static int altaPersona(String nombre, String direccion){
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Registrar el driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Conexión a la base de datos
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Sentencia SQL para insertar persona
            String sql = "INSERT INTO Personas (nombre, direccion) VALUES (?, ?)";

            // Preparar la consulta
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setString(2, direccion);

            // Ejecutar la inserción
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                
                //System.out.println("Persona agregada correctamente: " + nombre);
            } else {
                return 0;
                //System.out.println("No se pudo agregar la persona.");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return 1;
    }
    public static int bajaPersona(int personaId){
        Connection conn = null;
        PreparedStatement pstmtTelefonos = null;
        PreparedStatement pstmtPersona = null;

        try {
            // Registrar el driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            String sqlPersona = "DELETE FROM Personas WHERE id = ?";
            pstmtPersona = conn.prepareStatement(sqlPersona);
            pstmtPersona.setInt(1, personaId);
            int filasAfectadas = pstmtPersona.executeUpdate();

            if (filasAfectadas > 0) {
                
                //System.out.println("Persona con ID " + personaId + " eliminada correctamente.");
            } else {
                return 0;
                //System.out.println("No se encontró ninguna persona con ID " + personaId);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (pstmtTelefonos != null) pstmtTelefonos.close();
                if (pstmtPersona != null) pstmtPersona.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return 1;
    }
    public static int modificarPersona(String idPersona, String nuevoNombre, String nuevaDireccion) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Registrar el driver
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. Establecer la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. Preparar la sentencia SQL
            String sql = "UPDATE Personas SET nombre = ?, direccion = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevaDireccion);
            pstmt.setInt(3, Integer.parseInt(idPersona));

            // 4. Ejecutar actualización
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                return 1; // actualización exitosa
            } else {
                return 0; // no se encontró la persona
            }

        } catch (SQLException se) {
            se.printStackTrace();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static int modificarTelefono(String idTelefono, String nuevoNumero) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Registrar el driver
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. Establecer la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. Preparar la sentencia SQL
            String sql = "UPDATE Telefonos SET telefono = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoNumero);
            pstmt.setInt(2, Integer.parseInt(idTelefono));

            // 4. Ejecutar actualización
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                return 1; // actualización exitosa
            } else {
                return 0; // no se encontró el teléfono
            }

        } catch (SQLException se) {
            se.printStackTrace();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static int bajaTelefono(String idTelefono) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "DELETE FROM Telefonos WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, idTelefono);

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0 ? 1 : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) { se.printStackTrace(); }
        }
    }
    public static int agregarTelefono(String personaId, String numero) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO Telefonos (personaId, telefono) VALUES (?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, personaId);
            pstmt.setString(2, numero);

            int filas = pstmt.executeUpdate();
            return filas > 0 ? 1 : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) { se.printStackTrace(); }
        }
    }
    public static ArrayList<Persona> obtenerPersonas() {
        ArrayList<Persona> personas = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Personas");

            while (rs.next()) {
                String id = rs.getString("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                Persona p = new Persona(id, nombre, direccion);

                // cargar teléfonos
                Statement stmtTel = conn.createStatement();
                ResultSet rsTel = stmtTel.executeQuery(
                    "SELECT id, telefono FROM Telefonos WHERE personaId = " + id);

                while (rsTel.next()) {
                    String idTel = rsTel.getString("id");
                    String numero = rsTel.getString("telefono");
                    p.getTelefonos().add(new Telefono(idTel, numero));
                }
                rsTel.close();
                stmtTel.close();

                personas.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) { se.printStackTrace(); }
        }
        return personas;
    }
    
//    public static void mostrarTablas(){
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//
//        try {
//            // 1. Registrar el driver JDBC
//            Class.forName("org.mariadb.jdbc.Driver");
//
//            // 2. Establecer la conexión
//            System.out.println("Conectando a la base de datos...");
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//            // 3. Consultar la tabla Personas
//            System.out.println("\n=== LISTADO DE PERSONAS ===");
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM Personas");
//            
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String nombre = rs.getString("nombre");
//                String direccion = rs.getString("direccion");
//                
//                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion);
//                
//                // 4. Consultar los teléfonos de cada persona
//                System.out.println("  Teléfonos:");
//                Statement stmtTelefonos = conn.createStatement();
//                ResultSet rsTelefonos = stmtTelefonos.executeQuery(
//                    "SELECT telefono FROM Telefonos WHERE personaId = " + id);
//                
//                while (rsTelefonos.next()) {
//                    System.out.println("    - " + rsTelefonos.getString("telefono"));
//                }
//                rsTelefonos.close();
//                stmtTelefonos.close();
//            }
//
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 5. Cerrar recursos
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        System.out.println("\nConexión cerrada.");
//    }
//    
//    public static String mostrarTablasS() {
//        Connection conn = null;
//        Statement stmt = null;
//        ResultSet rs = null;
//        StringBuilder sb = new StringBuilder();
//
//        try {
//            // 1. Registrar el driver JDBC
//            Class.forName("org.mariadb.jdbc.Driver");
//
//            // 2. Establecer la conexión
//            sb.append("Conectando a la base de datos...\n");
//            conn = DriverManager.getConnection(URL, USER, PASSWORD);
//
//            // 3. Consultar la tabla Personas
//            sb.append("\n=== LISTADO DE PERSONAS ===\n");
//            stmt = conn.createStatement();
//            rs = stmt.executeQuery("SELECT * FROM Personas");
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String nombre = rs.getString("nombre");
//                String direccion = rs.getString("direccion");
//
//                sb.append("ID: ").append(id)
//                  .append(", Nombre: ").append(nombre)
//                  .append(", Dirección: ").append(direccion).append("\n");
//
//                // 4. Consultar los teléfonos de cada persona
//                sb.append("  Teléfonos:\n");
//                Statement stmtTelefonos = conn.createStatement();
//                ResultSet rsTelefonos = stmtTelefonos.executeQuery(
//                    "SELECT telefono FROM Telefonos WHERE personaId = " + id);
//
//                while (rsTelefonos.next()) {
//                    sb.append("    - ").append(rsTelefonos.getString("telefono")).append("\n");
//                }
//                rsTelefonos.close();
//                stmtTelefonos.close();
//            }
//
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            // 5. Cerrar recursos
//            try {
//                if (rs != null) rs.close();
//                if (stmt != null) stmt.close();
//                if (conn != null) conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        //sb.append("\nConexión cerrada.\n");
//        return sb.toString();
//    }  
    

}
