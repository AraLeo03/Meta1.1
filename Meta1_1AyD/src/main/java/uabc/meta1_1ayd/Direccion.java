/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uabc.meta1_1ayd;

/**
 *
 * @author Dell
 */
public class Direccion {
    private String idCalle;
    private String calle;

    public Direccion(String idCalle, String calle) {
        this.idCalle = idCalle;
        this.calle = calle;
    }

    public String getIdCalle() {
        return idCalle;
    }

    public void setIdCalle(String idCalle) {
        this.idCalle = idCalle;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }
    
}
