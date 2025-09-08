/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uabc.meta1_3;

import java.sql.*;

/**
 *
 * @author Dell
 */
public class Direccion implements InterfaceCRUD<Direccion>{
    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "superpassword";

    private String id;
    private String personaId;
    private String calle;

    public Direccion(String id, String personaId, String calle) {
        this.id = id;
        this.personaId = personaId;
        this.calle = calle;
    }

    @Override
    public int alta(Direccion d) {
        String sqlDir = "INSERT INTO Direcciones (calle) VALUES (?)";
        String sqlRel = "INSERT INTO persona_direccion (personaId, direccionId) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmtDir = conn.prepareStatement(sqlDir, Statement.RETURN_GENERATED_KEYS)) {

            // Insertar dirección
            pstmtDir.setString(1, d.getCalle());
            pstmtDir.executeUpdate();

            // Obtener ID generado
            try (ResultSet rs = pstmtDir.getGeneratedKeys()) {
                if (rs.next()) {
                    d.setId(String.valueOf(rs.getInt(1)));
                }
            }

            // Insertar relación con persona
            try (PreparedStatement pstmtRel = conn.prepareStatement(sqlRel)) {
                pstmtRel.setInt(1, Integer.parseInt(d.getPersonaId()));
                pstmtRel.setInt(2, Integer.parseInt(d.getId()));
                int filas = pstmtRel.executeUpdate();
                return filas > 0 ? 1 : 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 🔹 Baja (DELETE en relación y dirección)
    @Override
    public int baja(Direccion d) {
        String sqlRel = "DELETE FROM persona_direccion WHERE direccionId = ?";
        String sqlDir = "DELETE FROM Direcciones WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Borrar relación
            try (PreparedStatement pstmtRel = conn.prepareStatement(sqlRel)) {
                pstmtRel.setString(1, d.getId());
                pstmtRel.executeUpdate();
            }

            // Borrar dirección
            try (PreparedStatement pstmtDir = conn.prepareStatement(sqlDir)) {
                pstmtDir.setString(1, d.getId());
                int filasAfectadas = pstmtDir.executeUpdate();
                return filasAfectadas > 0 ? 1 : 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // 🔹 Modificar (UPDATE dirección)
    @Override
    public int modificar(Direccion d) {
        String sql = "UPDATE Direcciones SET calle = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, d.getCalle());
            pstmt.setInt(2, Integer.parseInt(d.getId()));

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0 ? 1 : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    
}
