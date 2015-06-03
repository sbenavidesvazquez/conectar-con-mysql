/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ultimoproyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sbenavidesvazquez
 */
public class Metodos {
    Connection conexion;
    String error;

    public Metodos(String host,String usuario,String pw,String base) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://"+host+"/"+base,usuario,pw);
        } catch (ClassNotFoundException ex) {
            error=ex.getMessage();
        } catch (SQLException ex) {
            error=ex.getMessage();
        }
    }
    
    public Connection conectar(){
        return conexion;
    }
    
    public void cierraConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String enviarError(){
        return error;
    }
    
}
