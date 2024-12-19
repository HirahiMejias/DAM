/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface InterfazGenerica<T,ID> {
    boolean insert(T t);
    boolean update(T t);
    boolean delete(ID id);
    T selectById(ID id);
    List<T> selectAll();
}