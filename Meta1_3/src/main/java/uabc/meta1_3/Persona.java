/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uabc.meta1_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class Persona implements InterfaceCRUD<Persona>{
    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "superpassword";
    
    private String id;
    private String nombre;
    private ArrayList<Telefono> telefonos = new ArrayList<>();
    private ArrayList<Direccion> direcciones = new ArrayList<>();

    public Persona(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    @Override
    public int alta(Persona p) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Personas (nombre) VALUES (?)")) {

            pstmt.setString(1, p.getNombre());
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0 ? 1 : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int baja(Persona p) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Personas WHERE id = ?")) {

            pstmt.setInt(1, Integer.parseInt(p.getId()));
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0 ? 1 : 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int modificar(Persona p) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE Personas SET nombre = ? WHERE id = ?")) {

            pstmt.setString(1, p.getNombre());
            pstmt.setInt(2, Integer.parseInt(p.getId()));

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(ArrayList<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public ArrayList<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(ArrayList<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}
