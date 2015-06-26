/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Allan
 */
public class Connect {

    // String de Conexiòn
    String connect = "jdbc:sqlite:baseDatosHogarcito{NoBorrar}.db"; 
    
    
    private Connection connection;
    private Statement statement;

    public Connect() {
        try{
        connection = DriverManager.getConnection(connect);
        statement = connection.createStatement();
        statement.setQueryTimeout(30);
        }catch(Exception e){
            SendException("E100. * Can't Connect * ");
        }
    }
    
    
   // Todas las Expciones de Esta Clase
    private void SendException(String e) {
        throw new UnsupportedOperationException(e + "[Connect.Connect]"); //To change body of generated methods, choose Tools | Templates.
    }
  
    public boolean executeStatement(String state) {
        try {
            this.getStatement().executeUpdate(state);
            return true;
        } catch (Exception e) {
           SendException("E200. * Can't Execute * " + e);
           return false;
        }
    }

    // Getter & Setter
    
    public String getConnect() {
        return connect;
    }

    public void setConnect(String connect) {
        this.connect = connect;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
  

}
