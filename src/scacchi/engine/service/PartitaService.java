package scacchi.engine.service;

import scacchi.data.pedine.Pezzo;

import scacchi.engine.player.Player;


public class
PartitaService {

    public static Player giocatoreB = new Player(true);
    public static Player giocatoreN = new Player(false);
    public static Pezzo lastClickedPezzo = null;
    public Pezzo pezzoXY;


    /**
     *IsValidMove è il metodo che unisce i tre controlli effettuati in sameteam, isvalidmovement e infine movecollidewithPiece,
     * e definisce se una mossa è valida.
     *
     * @param move
     */
    public boolean isvalidMove(Move move) {

        if (sameTeam(move.pezzo, move.capture)) {
            return false;
        }

        if (!move.isValidMovement(move.pezzo, move.nuoveColonne, move.nuoveRighe)) {
            return false;
        }

        if (move.moveCollidewithPiece(move.pezzo, move.nuoveColonne, move.nuoveRighe)) {
            return false;
        }

        return true;
    }

    /**
     *SameTeam è un controllo che viene utilizzato comparando due pezzi per verificare se i pezzi appartengono
     * allo stesso team o sono di colore diverso.
     *
     * @param p1
     * @param p2
     */
    public boolean sameTeam(Pezzo p1, Pezzo p2) {

        boolean t = false;

        if (p1 == null || p2 == null) {
            return false;
        } else {
            if (p1.isWhite == p2.isWhite)
                t = true;
        }

        return t;

    }

    /**
     *Metodo che rimuove le pedine dall'arraylist quando vengono mangiate.
     *
     * @param move
     */
    public void capture(Move move) {

        giocatoreN.listaPezzi.remove(move.capture);
        giocatoreB.listaPezzi.remove(move.capture);

    }

}

