package Model;

public class Pergunta {
    
    private Integer id;
    private String enunciado;
    private String disciplina;
    private String assunto;
    
    public Integer getId() {
        return id;
    }
    
    public void serId(int id){
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
    
    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
    
}
