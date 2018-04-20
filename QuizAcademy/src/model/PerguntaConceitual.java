
package model;

public class PerguntaConceitual extends Pergunta{

    public PerguntaConceitual(Integer id, String desc, String disc, String assu) {
        super(id, desc, disc, assu);
    }

    
    
    @Override
    public Alternativa getRespostaCerta() {
        Alternativa altCerta = null;
        for(Alternativa a : super.getAlternativas()){
            if(a.isRespostaCerta()){
                altCerta = a;
            }
        }
        return altCerta;
    }

    @Override
    public String getExplicacaoAlternativa(Integer _id) {
        for(Alternativa a : super.getAlternativas()){
            if(a.getId().equals(_id)){
                return a.getExplicacao();
            }
        }
        return null;
    }
    
}
