/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uabc.meta1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class Telefono implements InterfaceCRUD<Telefono>{
    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "superpassword";

    private String id;
    private String personaId;
    private String numero;
    
    public Telefono(String id, String personaId, String numero) {
        this.id = id;
        this.personaId = personaId;
        this.numero = numero;
    }
    
    @Override
    public int alta(Telefono t) {
        String sql = "INSERT INTO Telefonos (personaId, telefono) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getPersonaId());
            pstmt.setString(2, t.getNumero());

            int filas = pstmt.executeUpdate();
            return filas > 0 ? 1 : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    @Override
    public int baja(Telefono t) {
        String sql = "DELETE FROM Telefonos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getId());

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0 ? 1 : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Telefono t) {
        String sql = "UPDATE Telefonos SET telefono = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, t.getNumero());
            pstmt.setInt(2, Integer.parseInt(t.getId()));

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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    
    
}
