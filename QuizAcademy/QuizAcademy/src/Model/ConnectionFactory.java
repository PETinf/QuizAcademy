
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String URL = "jdbc:sqlite:db/db_quiz_academy.db";
    
    public static Connection getConnection(){
        
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        
    }
    
    public static void closeConnection(Connection conexao){
        
        try {
            if(conexao != null){
                conexao.close();
            }  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement stmt){
        closeConnection(conexao);
        
        try {
            if(stmt != null){
                stmt.close();
            }  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement stmt, ResultSet rs){
        
        closeConnection(conexao, stmt);
        
        try {
            if(rs != null){
                rs.close();
            }  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
}
