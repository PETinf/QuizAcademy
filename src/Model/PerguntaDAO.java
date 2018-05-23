/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Vinicius
 */
public class PerguntaDAO {
    private static String tabela = "perguntas";

    public static void setTabela(String tabela) {
        PerguntaDAO.tabela = tabela;
    }
    
    public void insert(Pergunta p) throws SQLException{
        
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        stmt = conexao.prepareStatement("INSERT INTO "+tabela+"(DISCIPLINA,ASSUNTO,DESCRICAO,ENUNCIADO,IMAGEMENUNCIADO,IMAGEMRESPOSTA,resposta) VALUES(?,?,?,?,?,?,?)");
        stmt.setString(1, p.getDisciplina());
        stmt.setString(2, p.getAssunto());
        stmt.setString(3, p.getDescricao());
        stmt.setString(4, p.getEnunciado());
        stmt.setString(5, p.getImagemEnunciado());
        stmt.setString(6, p.getImagemResposta());
        stmt.setString(7, p.getResposta());
            
        stmt.executeUpdate();
        
        
        ConnectionFactory.closeConnection(conexao, stmt);      
    }
    
    public List<Pergunta> read() throws SQLException{
        
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pergunta> perguntas = new LinkedList<>();
        
        stmt = conexao.prepareStatement("SELECT * FROM "+tabela+";");
        rs = stmt.executeQuery();
            
        while(rs.next()){
                
            Pergunta p = new Pergunta();
                
            p.setId(rs.getInt("id"));
            p.setDisciplina(rs.getString("disciplina"));
            p.setAssunto(rs.getString("assunto"));
            p.setDescricao(rs.getString("descricao"));
            p.setEnunciado(rs.getString("enunciado"));
            p.setImagemEnunciado(rs.getString("imagemenunciado"));
            p.setImagemResposta(rs.getString("imagemresposta"));
            p.setResposta(rs.getString("resposta"));
                
            perguntas.add(p);
        }
        
        ConnectionFactory.closeConnection(conexao, stmt, rs);
        
        return perguntas;
    }
    
    public void remove(Pergunta p) throws SQLException{
        
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        
        stmt = conexao.prepareStatement("DELETE FROM "+tabela+" WHERE id=?");
        stmt.setInt(1,p.getId());
            
        stmt.executeUpdate();
        
        ConnectionFactory.closeConnection(conexao, stmt);
    }
    
    public void update(Pergunta p) throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        stmt = conexao.prepareStatement("UPDATE "+tabela+" SET DISCIPLINA = ?,"
                                                + "ASSUNTO = ?,"
                                                + "DESCRICAO = ?,"
                                                + "ENUNCIADO = ?,"
                                                + "IMAGEMENUNCIADO = ?,"
                                                + "IMAGEMRESPOSTA = ?,"
                                                + "resposta = ? WHERE id = ?");
            
        stmt.setString(1, p.getDisciplina());
        stmt.setString(2, p.getAssunto());
        stmt.setString(3, p.getDescricao());
        stmt.setString(4, p.getEnunciado());
        stmt.setString(5, p.getImagemEnunciado());
        stmt.setString(6, p.getImagemResposta());
        stmt.setString(7, p.getResposta());
        stmt.setInt(8, p.getId());
           
        stmt.executeUpdate();
           
        
        ConnectionFactory.closeConnection(conexao, stmt);
    }
    
    public Pergunta search(int id) throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = conexao.prepareStatement("SELECT * FROM "+tabela+" WHERE id = ?");
        stmt.setInt(1, id);
            
        rs = stmt.executeQuery();
            
        Pergunta p = new Pergunta();
             
        p.setId(rs.getInt("id"));
        p.setDisciplina(rs.getString("disciplina"));
        p.setAssunto(rs.getString("assunto"));
        p.setDescricao(rs.getString("descricao"));
        p.setEnunciado(rs.getString("enunciado"));
        p.setImagemEnunciado(rs.getString("imagemenunciado"));
        p.setImagemResposta(rs.getString("imagemresposta"));
        p.setResposta(rs.getString("resposta"));
            
        ConnectionFactory.closeConnection(conexao, stmt, rs);
        
        return p;
    }
    
    
    public List<Pergunta> pesquisarDisciplina(String disciplina) throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = conexao.prepareStatement("SELECT * FROM "+tabela+" WHERE disciplina = ?");
        stmt.setString(1,disciplina);
            
        rs = stmt.executeQuery();
        List<Pergunta> perguntas = new LinkedList<>();
          
        while(rs.next()){
               
            Pergunta p = new Pergunta();
                
            p.setId(rs.getInt("id"));
            p.setDisciplina(rs.getString("disciplina"));
            p.setAssunto(rs.getString("assunto"));
            p.setDescricao(rs.getString("descricao"));
            p.setEnunciado(rs.getString("enunciado"));
            p.setImagemEnunciado(rs.getString("imagemenunciado"));
            p.setImagemResposta(rs.getString("imagemresposta"));
            p.setResposta(rs.getString("resposta"));
               
            perguntas.add(p);
        }
            
        ConnectionFactory.closeConnection(conexao, stmt, rs);
            
        return perguntas;
    }
    
    public List<Pergunta> pesquisarDisciplinaAssunto(String disciplina, String assunto) throws SQLException{
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = conexao.prepareStatement("SELECT * FROM "+tabela+" WHERE disciplina = ? and assunto = ?");
        stmt.setString(1,disciplina);
        stmt.setString(2, assunto);
            
        rs = stmt.executeQuery();
        List<Pergunta> perguntas = new LinkedList<>();
            
        while(rs.next()){
                
            Pergunta p = new Pergunta();
                
            p.setId(rs.getInt("id"));
            p.setDisciplina(rs.getString("disciplina"));
            p.setAssunto(rs.getString("assunto"));
            p.setDescricao(rs.getString("descricao"));
            p.setEnunciado(rs.getString("enunciado"));
            p.setImagemEnunciado(rs.getString("imagemenunciado"));
            p.setImagemResposta(rs.getString("imagemresposta"));
            p.setResposta(rs.getString("resposta"));
             
            perguntas.add(p);
        }
            
        ConnectionFactory.closeConnection(conexao, stmt, rs);
            
        return perguntas;
    }
    
}
