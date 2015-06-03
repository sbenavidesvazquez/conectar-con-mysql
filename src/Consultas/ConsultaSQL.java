/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sbenavidesvazquez
 */
public class ConsultaSQL {

    private Connection conexion;
    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private String consulta;

    private String[][] datosDevueltos;
    private String[] nombresColumnas;
    private String error;

    public ConsultaSQL(Connection coneRec, String consRec) {
        this.conexion = coneRec;
        this.consulta = consRec;

        try {
            //para mandar sentencias al servidor
            Statement sentencia = conexion.createStatement();
            //ejecutar consulta y devolver el resultset
            rs = sentencia.executeQuery(consulta);
            //obtener metadatos del resultset
            rsmd = rs.getMetaData();
            error = null;

        } catch (SQLException ex) {
            error = ex.getMessage();
        }
    }

    public String[][] getDatosDevueltos() {
        if (error == null) {
            try {
                int columnas = rsmd.getColumnCount();
                rs.last();
                int filas = rs.getRow();
                datosDevueltos = new String[filas][columnas];
                rs.beforeFirst();
                for (int i = 0; i < filas; i++) {
                    for (int j = 0; j < columnas; j++) {
                        datosDevueltos[i][j] = rs.getString(j + 1);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(ConsultaSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return datosDevueltos;
    }

    public String[] getNombresColumnas() {

        if (error == null) {
            try {
                int columnas = rsmd.getColumnCount();
                nombresColumnas = new String[columnas];

                for (int i = 0; i < columnas; i++) {
                    nombresColumnas[i] = rsmd.getColumnLabel(i + 1);
                }

            } catch (SQLException ex) {

            }

        }
        return nombresColumnas;
    }

    public String getMensajeError() {
        return error;
    }

}
