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

/**
 *
 * @author Vinicius
 */
public class SimuladoDAO {

    private static String tabela = "simulados";

    public static void setTabela(String tabela) {
        SimuladoDAO.tabela = tabela;
    }

    public void insert(Simulado s) throws SQLException {

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        stmt = conexao.prepareStatement("INSERT INTO " + tabela + "(DESCRICAO,DISCIPLINA,ASSUNTO,NOTA,ID_PERGUNTAS) VALUES(?,?,?,?,?)");
        stmt.setString(1, s.getDescricao());
        stmt.setString(2, s.getDisciplina());
        stmt.setString(3, s.getAssunto());
        stmt.setDouble(4, s.getNota());
        stmt.setString(5, s.getIdPerguntas());

        stmt.executeUpdate();

        ConnectionFactory.closeConnection(conexao, stmt);
    }

    public List<Simulado> read() throws SQLException {

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Simulado> simulados = new LinkedList<>();

        stmt = conexao.prepareStatement("SELECT * FROM " + tabela + ";");
        rs = stmt.executeQuery();

        while (rs.next()) {

            Simulado s = new Simulado();

            s.setId(rs.getInt("id"));
            s.setDisciplina(rs.getString("disciplina"));
            s.setAssunto(rs.getString("assunto"));
            s.setDescricao(rs.getString("descricao"));
            s.setNota(rs.getDouble("nota"));
            s.setIdPerguntas(rs.getString("id_perguntas"));

            simulados.add(s);
        }

        ConnectionFactory.closeConnection(conexao, stmt, rs);

        return simulados;
    }

    public void remove(Simulado s) throws SQLException {

        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        stmt = conexao.prepareStatement("DELETE FROM " + tabela + " WHERE id=?");
        stmt.setInt(1, s.getId());

        stmt.executeUpdate();

        ConnectionFactory.closeConnection(conexao, stmt);
    }

    public void update(Simulado s) throws SQLException {
        Connection conexao = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        stmt = conexao.prepareStatement("UPDATE " + tabela + " SET DESCRICAO = ?,"
                + "DISCIPLINA = ?,"
                + "ASSUNTO = ?,"
                + "NOTA = ?,"
                + "ID_PERGUNTAS = ? WHERE id = ?");

        stmt.setString(1, s.getDescricao());
        stmt.setString(2, s.getDisciplina());
        stmt.setString(3, s.getAssunto());
        stmt.setDouble(4, s.getNota());
        stmt.setString(5, s.getIdPerguntas());
        stmt.setInt(7, s.getId());

        stmt.executeUpdate();

        ConnectionFactory.closeConnection(conexao, stmt);
    }
}
