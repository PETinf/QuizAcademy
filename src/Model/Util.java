/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Util {
    public static List<String> disciplinas(){
        Connection con = ConnectionFactory.getConnection();
        List<String> disciplinas = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT disciplina FROM perguntas;");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                disciplinas.add(rs.getString("disciplina"));
            }
            
        }catch(SQLException ex){
            return new ArrayList<>();
        }
        
        return disciplinas;
    }
    
    public static List<String> assuntos(String disciplina){
        Connection con = ConnectionFactory.getConnection();
        List<String> assuntos = new ArrayList<>();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT assunto FROM perguntas WHERE disciplina=?;");
            stmt.setString(1, disciplina);
            ResultSet rs = stmt.executeQuery();
            
            assuntos.add("TODOS");
            
            while(rs.next()){
                assuntos.add(rs.getString("assunto"));
            }
            
        }catch(SQLException ex){
            return new ArrayList<>();
        }
        
        return assuntos;
    }
    
    public static int nroPerguntas(String disciplina, String assunto){
        Connection con = ConnectionFactory.getConnection();
        int nroPerguntas;
        PreparedStatement stmt;
        ResultSet rs;
        try{
            if(assunto.equals("TODOS")){
                stmt = con.prepareStatement("SELECT COUNT(id) FROM perguntas WHERE disciplina=?;");
                stmt.setString(1, disciplina);
            }else{
                stmt = con.prepareStatement("SELECT COUNT(id) FROM perguntas WHERE disciplina=? AND assunto=?;");
                stmt.setString(1, disciplina);
                stmt.setString(2, assunto);
            }
            rs = stmt.executeQuery();
            
            nroPerguntas = rs.getInt(1);
            
        }catch(SQLException | NullPointerException ex){
            return 0;
        }
        
        return nroPerguntas;
    }
    
    
//    public static void main(String args[]){
//        System.out.println(Util.nroPerguntas(Util.disciplinas().get(0), Util.assuntos(Util.disciplinas().get(1)).get(0)));
//    }
}
