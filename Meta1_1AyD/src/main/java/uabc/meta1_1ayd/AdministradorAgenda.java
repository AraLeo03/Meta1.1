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
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // SOLO insertar nombre, ya que no hay columna direccion
            String sql = "INSERT INTO Personas (nombre) VALUES (?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            // pstmt.setString(2, direccion); // ← Eliminar esta línea

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                return 1;
            } else {
                return 0;
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
    public static int bajaPersona(int personaId){
        Connection conn = null;
        PreparedStatement pstmtTelefonos = null;
        PreparedStatement pstmtPersona = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");

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
            
            Class.forName("org.mariadb.jdbc.Driver");

            
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

           
            String sql = "UPDATE Personas SET nombre = ?, direccion = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevaDireccion);
            pstmt.setInt(3, Integer.parseInt(idPersona));

            
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                return 1;
            } else {
                return 0;
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
            Class.forName("org.mariadb.jdbc.Driver");

            
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            
            String sql = "UPDATE Telefonos SET telefono = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nuevoNumero);
            pstmt.setInt(2, Integer.parseInt(idTelefono));

            
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                return 1;
            } else {
                return 0; 
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

                Persona p = new Persona(id, nombre);

                // Cargar teléfonos
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

                // Cargar direcciones (usando la tabla intermedia)
                Statement stmtDir = conn.createStatement();
                ResultSet rsDir = stmtDir.executeQuery(
                    "SELECT d.id, d.calle FROM Direcciones d " +
                    "INNER JOIN persona_direccion pd ON d.id = pd.direccionId " +
                    "WHERE pd.personaId = " + id);

                while (rsDir.next()) {
                    String idDir = rsDir.getString("id");
                    String calle = rsDir.getString("calle");
                    p.getDirecciones().add(new Direccion(idDir, calle));
                }
                rsDir.close();
                stmtDir.close();

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
    public static int agregarDireccion(String personaId, String calle) {
        Connection conn = null;
        PreparedStatement pstmtDir = null;
        PreparedStatement pstmtRel = null;
        ResultSet rs = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sqlDir = "INSERT INTO Direcciones (calle) VALUES (?)";
            pstmtDir = conn.prepareStatement(sqlDir, Statement.RETURN_GENERATED_KEYS);
            pstmtDir.setString(1, calle);
            pstmtDir.executeUpdate();

            rs = pstmtDir.getGeneratedKeys();
            int direccionId = 0;
            if (rs.next()) {
                direccionId = rs.getInt(1);
            }

            String sqlRel = "INSERT INTO persona_direccion (personaId, direccionId) VALUES (?, ?)";
            pstmtRel = conn.prepareStatement(sqlRel);
            pstmtRel.setInt(1, Integer.parseInt(personaId));
            pstmtRel.setInt(2, direccionId);

            int filas = pstmtRel.executeUpdate();
            return filas > 0 ? 1 : 0;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmtDir != null) pstmtDir.close();
                if (pstmtRel != null) pstmtRel.close();
                if (conn != null) conn.close();
            } catch (SQLException se) { se.printStackTrace(); }
        }
    }

    public static int bajaDireccion(String idDireccion) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sqlRel = "DELETE FROM persona_direccion WHERE direccionId = ?";
            pstmt = conn.prepareStatement(sqlRel);
            pstmt.setString(1, idDireccion);
            pstmt.executeUpdate();

            String sqlDir = "DELETE FROM Direcciones WHERE id = ?";
            pstmt = conn.prepareStatement(sqlDir);
            pstmt.setString(1, idDireccion);

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
}
