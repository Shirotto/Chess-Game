package scacchi.engine.exception;

public class ScaccoMattoNero extends RuntimeException{
    /**
     * Viene usato per concludere la partita e definire il comportamento dello scacco matto
     *
     */
        public ScaccoMattoNero() {
            super("Scacco Matto!!  Ha Vinto Il Giocatore Nero");
        }

    }
