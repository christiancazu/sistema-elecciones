package modelos;

import entidades.Partido;
import entidades.Voto;

public class PartidoVoto {
    private Partido partido;
    private int votos;

    public PartidoVoto() {
    }

    public PartidoVoto(Partido partido, int votos) {
        this.partido = partido;
        this.votos = votos;
    }
    
    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public int getVotos() {
        return votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }
 
}
