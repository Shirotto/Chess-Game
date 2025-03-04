package scacchi.engine.exception;

public class ResaGiocatoreBianco extends RuntimeException{

    /**
     * Viene usato per concludere la partita e definire il comportamento della resa del giocatore bianco
     *
     */
public ResaGiocatoreBianco() {
    super ("Il giocatore bianco si è arreso. Premi il tasto Ok per uscire dal gioco.");
}
}
