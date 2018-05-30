
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:db/";
    public static String banco = "db_quiz_academy.db";
    
    public static Connection getConnection(){
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL+banco);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ConnectionFactory.getConnection: "+ex.getMessage());
            return null;
        }
        
    }
    
    public static void closeConnection(Connection conexao){
        
        try {
            if(conexao != null){
                conexao.close();
            }
            
        } catch (SQLException ex) {
            System.out.println("ConnectionFactory.closeConnection1: "+ex.getMessage());
        }
        
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement stmt){
        
        closeConnection(conexao);
        
        try {
            if(stmt != null){
                stmt.close();
            }
        } catch (SQLException ex) {
            System.out.println("ConnectionFactory.closeConnection2: "+ex.getMessage());
        }
        
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement stmt, ResultSet rs){
        
        closeConnection(conexao, stmt);
        
        try {
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
            System.out.println("ConnectionFactory.closeConnection3: "+ex.getMessage());
        }
        
    }

    public static String getBanco() {
        return banco;
    }

    public static void setBanco(String banco) {
        ConnectionFactory.banco = banco;
    }
}
