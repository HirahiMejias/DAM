/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author 1dama
 */
public class SQLBuilder {
    public String InsertarContacto (){
    return "Insert into users (name,email,country)Values (?,?,?)";
    
    }
    public String DeleteContacto(){
    return "";
    }
    
}
