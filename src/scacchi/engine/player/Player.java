package scacchi.engine.player;

import scacchi.engine.service.ScacchieraService;
import scacchi.data.pedine.*;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Giocatore, Serializable {

    public ArrayList<Pezzo> listaPezzi = new ArrayList<>();

    private boolean colore;

    public Player(boolean colore) {
        this.colore = colore;
    }


    /**
     * @param scacchiera
     */
    @Override
    public void aggPezzo(ScacchieraService scacchiera) {

        if (!colore) {
            listaPezzi.add(new Torre(scacchiera, 0, 0, colore));
            listaPezzi.add(new Torre(scacchiera, 7, 0, colore));
            listaPezzi.add(new Alfiere(scacchiera, 2, 0, colore));
            listaPezzi.add(new Alfiere(scacchiera, 5, 0, colore));
            listaPezzi.add(new Cavallo(scacchiera, 1, 0, colore));
            listaPezzi.add(new Cavallo(scacchiera, 6, 0, colore));
            listaPezzi.add(new Regina(scacchiera, 3, 0, colore));
            listaPezzi.add(new Re(scacchiera, 4, 0, colore));
            listaPezzi.add(new Pedone(scacchiera, 0, 1, colore));
            listaPezzi.add(new Pedone(scacchiera, 1, 1, colore));
            listaPezzi.add(new Pedone(scacchiera, 2, 1, colore));
            listaPezzi.add(new Pedone(scacchiera, 3, 1, colore));
            listaPezzi.add(new Pedone(scacchiera, 4, 1, colore));
            listaPezzi.add(new Pedone(scacchiera, 5, 1, colore));
            listaPezzi.add(new Pedone(scacchiera, 6, 1, colore));
            listaPezzi.add(new Pedone(scacchiera, 7, 1, colore));

        } else {

            listaPezzi.add(new Torre(scacchiera, 0, 7, colore));
            listaPezzi.add(new Torre(scacchiera, 7, 7, colore));
            listaPezzi.add(new Alfiere(scacchiera, 2, 7, colore));
            listaPezzi.add(new Alfiere(scacchiera, 5, 7, colore));
            listaPezzi.add(new Cavallo(scacchiera, 1, 7, colore));
            listaPezzi.add(new Cavallo(scacchiera, 6, 7, colore));
            listaPezzi.add(new Regina(scacchiera, 3, 7, colore));
            listaPezzi.add(new Re(scacchiera, 4, 7, colore));
            listaPezzi.add(new Pedone(scacchiera, 0, 6, colore));
            listaPezzi.add(new Pedone(scacchiera, 1, 6, colore));
            listaPezzi.add(new Pedone(scacchiera, 2, 6, colore));
            listaPezzi.add(new Pedone(scacchiera, 3, 6, colore));
            listaPezzi.add(new Pedone(scacchiera, 4, 6, colore));
            listaPezzi.add(new Pedone(scacchiera, 5, 6, colore));
            listaPezzi.add(new Pedone(scacchiera, 6, 6, colore));
            listaPezzi.add(new Pedone(scacchiera, 7, 6, colore));
        }

    }


    /**
     * Metodo che verifica se dentro uno degli array list si trova ancora il re, in caso positivo si può continuare a gicoare
     * in caso negativo finisce la partita.
     *
     * @return
     */
    public boolean verificaReB() {
        for (Pezzo pezzo : listaPezzi) {
            if (pezzo.getClass().equals(Re.class) && pezzo.isWhite) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaReN() {
        for (Pezzo pezzo : listaPezzi) {
            if (pezzo.getClass().equals(Re.class) && !pezzo.isWhite) {
                return true;
            }
        }
        return false;
    }

}



