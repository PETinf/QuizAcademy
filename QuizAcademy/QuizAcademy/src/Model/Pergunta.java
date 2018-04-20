package Model;
import java.util.ArrayList;

public abstract class Pergunta {
    
    private Integer _id;
    
    private String descricao;
    private String disciplina;
    private String assunto;
    private ArrayList<Alternativa> alternativas;
    
    public Pergunta(Integer id, String desc, String disc, String assu){
        _id = id;
        
        descricao = desc;
        disciplina = disc;
        assunto = assu;
        alternativas = new ArrayList<>();
    }
    
    public Integer getId() {
        return _id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public ArrayList<Alternativa> getAlternativas() {
        return alternativas;
    }
    
    public void addAlternativa(Alternativa alt){
        alternativas.add(alt);
    }
    
    
    public abstract Alternativa getRespostaCerta();
    
    public abstract String getExplicacaoAlternativa(Integer _id);
    
}
