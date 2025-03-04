package scacchi.engine.exception;

public class ResaGiocatoreNero extends  RuntimeException{

    /**
     * Viene usato per concludere la partita e definire il comportamento della resa del giocatore nero
     *
     */
    public ResaGiocatoreNero(){
        super("Il giocatore nero si è arreso. Premi il tasto Ok per uscire dal gioco.");
    }
}
