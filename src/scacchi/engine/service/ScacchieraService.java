package scacchi.engine.service;

import scacchi.data.pedine.Pezzo;


public class ScacchieraService{

    public static final int gCaselle = 75;
    public static final int colonne = 8;
    public static final int righe = 8;
    public Pezzo selectedPiece;
    public PartitaService partitaService = new PartitaService();

    /**
     *Il metodo makemove aggiorna la posizione del pezzo utlizzando in input una Move vengono aggiornate le colonne
     * ,le righe e la posizione del pezzo con le nuove coordinate.
     *
     * @param move
     */
    public void makeMove(Move move) {
        move.pezzo.colonne = move.nuoveColonne;
        move.pezzo.righe = move.nuoveRighe;
        move.pezzo.xPos = move.nuoveColonne * gCaselle;
        move.pezzo.yPos = move.nuoveRighe * gCaselle;
        move.pezzo.isFristMove = false;

        partitaService.capture(move);
    }

    /**
     *Il metodo GetPezzo prende in input colonne e righe e restituisce la peidna che si trova in quella determinata
     * posizione, verifica la listaPezzi di entrambi i colori.
     *
     * @param colonne
     * @param righe
     */
    public Pezzo getPezzo(int colonne, int righe) {

        for (Pezzo pezzo : partitaService.giocatoreB.listaPezzi) {
            if (pezzo.colonne == colonne && pezzo.righe == righe) {
                return pezzo;
            }
        }
        for (Pezzo pezzo : partitaService.giocatoreN.listaPezzi) {
            if (pezzo.colonne == colonne && pezzo.righe == righe) {
                return pezzo;
            }
        }

        return null;
    }


}
