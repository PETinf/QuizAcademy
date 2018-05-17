/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;

/**
 *
 * @author Vinicius
 */
public class Simulado {
    private int id;
    private String disciplina;
    private String descricao;
    private String assunto;
    private double nota;
    private String idPerguntas;

    public Simulado(int id, String disciplina, String descricao, String assunto, double nota, String idPerguntas) {
        this.id = id;
        this.disciplina = disciplina;
        this.descricao = descricao;
        this.assunto = assunto;
        this.nota = nota;
        this.idPerguntas = idPerguntas;
    }

    public Simulado(){}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getIdPerguntas() {
        return idPerguntas;
    }

    public void setIdPerguntas(String idPerguntas) {
        this.idPerguntas = idPerguntas;
    }

    @Override
    public String toString() {
        return "Simulado{" + "id=" + id + ", disciplina=" + disciplina + ", descricao=" + descricao + ", assunto=" + assunto + ", nota=" + nota + ", idPerguntas=" + idPerguntas + '}';
    }
    
    
}
