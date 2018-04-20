
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER = "root";
    private final String URL = "jdbc:mysql://localhost:3306/dbquizacademy";
    private final String PASS = "";
    
    Connection conexao;
    
    public Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(USER,URL,PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ",ex);
        }
        
    }
    
    public static void closeConnection(Connection conexao){
        try{
            if(conexao != null){
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement statement){
        closeConnection(conexao);
        
        try{
            if(statement != null){
                statement.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conexao, PreparedStatement statement, ResultSet rs){
        closeConnection(conexao, statement);
        
        try{
            if(rs != null){
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
