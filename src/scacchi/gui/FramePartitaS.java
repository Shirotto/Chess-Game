package scacchi.gui;

import scacchi.engine.exception.ResaGiocatoreBianco;
import scacchi.engine.exception.ResaGiocatoreNero;
import scacchi.engine.service.GameData;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static scacchi.engine.input.InputA.scacchieraS;


public class FramePartitaS {

    public static GameData game = new GameData();

    /**
     * Questo metodo ci permette di richiamare il metodo "Salva" che si trova nella classe GameData , e associarlo al Bottone che ci permette di completare il
     * salvataggio.
     *
     * @param e
     */
    public void salvaButton(ActionEvent e) {

        game = new GameData();

        try {
            game.Salva();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Questo metodo rileva il press del ResaButton e
     * verifica il colore dell'ultimo pezzo spostato, se è bianco allora la resa sarà del giocatore nero mentre se l'ultimo
     * pezzo spostato è nero allora la resa sarà del giocatore bianco.
     *
     * @param e
     */
    public void resaButton(ActionEvent e) {
        try {
            if (scacchieraS.partitaService.lastClickedPezzo != null && scacchieraS.partitaService.lastClickedPezzo.isWhite()) {

                throw new ResaGiocatoreNero();
            } else {
                throw new ResaGiocatoreBianco();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Il gioco verrà chiuso", JOptionPane.INFORMATION_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Metodo che rileva il press del mouse e permette di implementare il comportamento dell'undo e quindi di annullare l'ultima mossa.
     *
     * @param e
     */
    public void undoButton(ActionEvent e) {
        try {
            Input.m.undo();
        } catch (Exception ex) {
        }
    }

}
