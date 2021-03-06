package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PerguntaDAO {

    private static String tabela = "perguntas";

    public static void setTabela(String tabela) {
        PerguntaDAO.tabela = tabela;
    }

    public void insert(Pergunta p) throws SQLException {

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        stmt = conexao.prepareStatement("INSERT INTO " + tabela + "(DISCIPLINA,ASSUNTO,DESCRICAO,ENUNCIADO,IMAGEMENUNCIADO,IMAGEMRESPOSTA,resposta) VALUES(?,?,?,?,?,?,?)");
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

    public List<Pergunta> read() throws SQLException {

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pergunta> perguntas = new LinkedList<>();

        stmt = conexao.prepareStatement("SELECT * FROM " + tabela + ";");
        rs = stmt.executeQuery();

        while (rs.next()) {
            perguntas.add(getPergunta(rs));
        }

        ConnectionFactory.closeConnection(conexao, stmt, rs);

        return perguntas;
    }

    public void remove(Pergunta p) throws SQLException {

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        stmt = conexao.prepareStatement("DELETE FROM " + tabela + " WHERE id=?");
        stmt.setInt(1, p.getId());

        stmt.executeUpdate();

        ConnectionFactory.closeConnection(conexao, stmt);
    }

    public void update(Pergunta p) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        stmt = conexao.prepareStatement("UPDATE " + tabela + " SET DISCIPLINA = ?,"
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

    public List<Pergunta> pesquisarDisciplina(String disciplina) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = conexao.prepareStatement("SELECT * FROM " + tabela + " WHERE disciplina = ?");
        stmt.setString(1, disciplina);

        rs = stmt.executeQuery();
        List<Pergunta> perguntas = new LinkedList<>();

        while (rs.next()) {
            perguntas.add(getPergunta(rs));
        }
        ConnectionFactory.closeConnection(conexao, stmt, rs);
        return perguntas;
    }

    public List<Pergunta> pesquisarAssunto(String assunto) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = conexao.prepareStatement("SELECT * FROM " + tabela + " WHERE assunto = ?");
        stmt.setString(1, assunto);

        rs = stmt.executeQuery();
        List<Pergunta> perguntas = new LinkedList<>();

        while (rs.next()) {
            perguntas.add(getPergunta(rs));
        }

        System.out.println(perguntas.size());

        ConnectionFactory.closeConnection(conexao, stmt, rs);

        return perguntas;
    }

    public List<Pergunta> pesquisarDisciplinaAssunto(String disciplina, String assunto) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = conexao.prepareStatement("SELECT * FROM " + tabela + " WHERE disciplina = ? and assunto = ?");
        stmt.setString(1, disciplina);
        stmt.setString(2, assunto);

        rs = stmt.executeQuery();
        List<Pergunta> perguntas = new LinkedList<>();

        while (rs.next()) {

            perguntas.add(getPergunta(rs));
        }

        ConnectionFactory.closeConnection(conexao, stmt, rs);

        return perguntas;
    }

    public Pergunta pesquisarPorId(int id) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = conexao.prepareStatement("SELECT * FROM " + tabela + " WHERE id = ?");
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
        if (rs.getString("resposta") != null) {
            p.setSimulavel(true);
        }

        ConnectionFactory.closeConnection(conexao, stmt, rs);

        return p;
    }

    public Pergunta pesquisarPorDescricao(String descricao) throws SQLException {
        Connection conecao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pergunta p = new Pergunta();

        stmt = conecao.prepareStatement("SELECT * FROM " + tabela + " WHERE DESCRICAO = ?");
        stmt.setString(1, descricao);
        rs = stmt.executeQuery();

        p.setId(rs.getInt("id"));
        p.setDisciplina(rs.getString("disciplina"));
        p.setAssunto(rs.getString("assunto"));
        p.setDescricao(rs.getString("descricao"));
        p.setEnunciado(rs.getString("enunciado"));
        p.setImagemEnunciado(rs.getString("imagemenunciado"));
        p.setImagemResposta(rs.getString("imagemresposta"));
        p.setResposta(rs.getString("resposta"));
        if (rs.getString("resposta") != null) {
            p.setSimulavel(true);
        }

        ConnectionFactory.closeConnection(conecao, stmt, rs);

        return p;

    }
    
    private Pergunta getPergunta(ResultSet rs) throws SQLException{
        Pergunta p = new Pergunta();
        
        p.setId(rs.getInt("id"));
        p.setDisciplina(rs.getString("disciplina"));
        p.setAssunto(rs.getString("assunto"));
        p.setDescricao(rs.getString("descricao"));
        p.setEnunciado(rs.getString("enunciado"));
        p.setImagemEnunciado(rs.getString("imagemenunciado"));
        p.setImagemResposta(rs.getString("imagemresposta"));
        p.setResposta(rs.getString("resposta"));
        if (rs.getString("resposta") != null) {
            p.setSimulavel(true);
        }
        
        return p;
    }

}
