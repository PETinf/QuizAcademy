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
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vinicius
 */
public class PerguntaDAO {
    private static String tabela = "original";

    public static void setTabela(String tabela) {
        PerguntaDAO.tabela = tabela;
    }
    
    public void insert(Pergunta p){
        
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conexao.prepareStatement("INSERT INTO "+tabela+"(DISCIPLINA,ASSUNTO,DESCRICAO,ENUNCIADO,IMAGEMENUNCIADO,IMAGEMRESPOSTA) VALUES(?,?,?,?,?,?)");
            stmt.setString(1, p.getDisciplina());
            stmt.setString(2, p.getAssunto());
            stmt.setString(3, p.getDescricao());
            stmt.setString(4, p.getEnunciado());
            stmt.setString(5, p.getImagemEnunciado());
            stmt.setString(6, p.getImagemResposta());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ConnectionFactory.closeConnection(conexao, stmt);
                
                
    }
    
    public List<Pergunta> read(){
        
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pergunta> perguntas = new LinkedList<>();
        
        try {
            stmt = conexao.prepareStatement("SELECT * FROM "+tabela+";");
            
            rs = stmt.executeQuery();
            Pergunta p = new Pergunta();
            
            while(rs.next()){
                p.setId(rs.getInt("id"));
                p.setDisciplina(rs.getString("disciplina"));
                p.setAssunto(rs.getString("assunto"));
                p.setDescricao(rs.getString("descricao"));
                p.setEnunciado(rs.getString("enunciado"));
                p.setImagemEnunciado(rs.getString("imagemenunciado"));
                p.setImagemResposta(rs.getString("imagemresposta"));
                
                perguntas.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ConnectionFactory.closeConnection(conexao, stmt, rs);
        
        return perguntas;
    }
    
    public void remove(){
        
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pergunta> perguntas = new LinkedList<>();
        
        try {
            stmt = conexao.prepareStatement("SELECT * FROM "+tabela+";");
            
            rs = stmt.executeQuery();
            Pergunta p = new Pergunta();
            
            while(rs.next()){
                p.setDisciplina(rs.getString("disciplina"));
                p.setAssunto(rs.getString("assunto"));
                p.setDescricao(rs.getString("descricao"));
                p.setEnunciado(rs.getString("enunciado"));
                p.setImagemEnunciado(rs.getString("imagemenunciado"));
                p.setImagemResposta(rs.getString("imagemresposta"));
                
                perguntas.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ConnectionFactory.closeConnection(conexao, stmt, rs);
    }
    
}
