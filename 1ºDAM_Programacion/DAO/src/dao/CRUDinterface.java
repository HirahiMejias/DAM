/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author 1dama
 */
public interface CRUDinterface {

    public boolean insertContacto(Contactos contacto);

    public boolean updateContacto(Contactos contacto);

    public boolean deleteContacto(int id);

    public Contactos selectContacto(int id);

    public ArrayList<Contactos> selectAllContactos();

}
