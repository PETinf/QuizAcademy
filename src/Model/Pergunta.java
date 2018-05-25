package Model;

public class Pergunta {
    
    public static String PATHENUNCIADO = System.getProperty("user.dir") + "/src/ImagemEnunciado/";
    public static String PATHRESPOSTA = System.getProperty("user.dir") + "/src/ImagemResposta/";
    
    private int id;
    private String descricao;
    private String enunciado;
    private String disciplina;
    private String assunto;
    private String imagemEnunciado;
    private String imagemResposta;
    private String resposta;
    
    
    public int getId() {
        return id;
    }
    
    public void setId(int id){
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagemEnunciado() {
        return imagemEnunciado;
    }

    public void setImagemEnunciado(String imagemEnunciado) {
        this.imagemEnunciado = imagemEnunciado;
    }

    public String getImagemResposta() {
        return imagemResposta;
    }

    public void setImagemResposta(String imagemResposta) {
        this.imagemResposta = imagemResposta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    @Override
    public String toString() {
        return "Pergunta{" + "id=" + id + ", descricao=" + descricao + ", enunciado=" + enunciado + ", disciplina=" + disciplina + ", assunto=" + assunto + ", imagemEnunciado=" + imagemEnunciado + ", imagemResposta=" + imagemResposta + ", resposta=" + resposta + '}';
    }

    

    
    
    
    
    
}
