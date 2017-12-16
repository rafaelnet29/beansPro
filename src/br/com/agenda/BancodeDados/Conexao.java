package br.com.agenda.BancodeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    static Connection conn;
    /**
     * Conexão como MySQL
    */
    private final String url = "jdbc:mysql://localhost:3306/cad?autoReconnect=true&useSSL=false";
    private final String user = "root";
    private final String pass = "2907";
    private static final String Driverclass = "com.mysql.jdbc.Driver";

    /** Conexão com o postgres
     * private final String url2 = "jdbc:postgresql://localhost:5432/postgres?autoReconnect=true&useSSL=false";
     * private final String user2 = "postgres"; 
     * private final String pass2 = "postgres";
     * private static final String Driverclass2 = "org.postgresql.Driver";
     */
    
    public Conexao(){
        
    }

    public Connection getConectMySQl() {

        try {
            Class.forName(Driverclass);
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
   /* public Connection getConectPostgres(){
        try {
            Class.forName(Driverclass2);
            conn = DriverManager.getConnection(url2, user2, pass2);
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
        return conn;
    }*/
}
