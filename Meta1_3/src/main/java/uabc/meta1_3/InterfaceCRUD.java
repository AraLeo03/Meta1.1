/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uabc.meta1_3;

/**
 *
 * @author Dell
 */
public interface InterfaceCRUD<T> {
    int alta(T obj);
    int baja(T obj);
    int modificar(T obj);
}
