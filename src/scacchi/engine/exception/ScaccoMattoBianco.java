package scacchi.engine.exception;

public class ScaccoMattoBianco extends RuntimeException{
    /**
     * Viene usato per concludere la partita e definire il comportamento dello scacco matto
     *
     */
    public ScaccoMattoBianco() {
        super("Scacco Matto!!  Ha Vinto Il Giocatore Bianco");
    }

}

