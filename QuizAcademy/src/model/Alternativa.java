package model;

public class Alternativa {
    
    private Integer _id;
    
    private String descricao;
    private String explicacao;
    private boolean respostaCerta;
    
    public Alternativa(Integer id, String desc, String exp, boolean respC){
        _id = id;
        descricao = desc;
        explicacao = exp;
        respostaCerta = respC;
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

    public String getExplicacao() {
        return explicacao;
    }

    public void setExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    public boolean isRespostaCerta() {
        return respostaCerta;
    }

    public void setRespostaCerta(boolean respostaCerta) {
        this.respostaCerta = respostaCerta;
    }
    
    public String toString(){
        return _id + " " + descricao + " " + explicacao + " " + respostaCerta;
    }
}
