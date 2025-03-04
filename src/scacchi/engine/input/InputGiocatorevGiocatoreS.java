package scacchi.engine.input;

import scacchi.engine.exception.ScaccoMattoBianco;
import scacchi.engine.exception.ScaccoMattoNero;
import scacchi.engine.exception.TurnoBianco;
import scacchi.engine.exception.TurnoNero;
import scacchi.engine.service.Move;
import scacchi.engine.service.PartitaService;
import scacchi.engine.service.ScacchieraService;
import scacchi.data.pedine.Pezzo;

import javax.swing.*;

public class InputGiocatorevGiocatoreS extends InputA {
    public int[] xA, yA;
    public Pezzo[] pA;

    public int i = 0;

    private int patta = 0;

    public InputGiocatorevGiocatoreS() {

        xA = new int[7];
        yA = new int[7];
        pA = new Pezzo[7];

    }

    /**
     * Nel metodo mousePressedGiocatore ci sono diverse implementazioni tra cui: quella dell'undo, ma anche il "turno"
     * del giocatore gestito con delle eccezioni.Qui troviamo il log dei file che indica la posizione inizale del pezzo.
     *
     * @param col
     * @param rows
     */
    public void ComportamentoClickGiocatorevGiocatore(int col, int rows) {

        scacchieraS.partitaService.pezzoXY = scacchieraS.getPezzo(col, rows);

        x = col;
        y = rows;
        undo = scacchieraS.partitaService.pezzoXY;

        if (i == 6) {

            i = 5;

            for (int j = 0; j < 5; j++) {

                xA[j] = xA[j + 1];
                yA[j] = yA[j + 1];
                pA[j] = pA[j + 1];

            }

        }

        xA[i] = x;
        yA[i] = y;
        pA[i] = undo;


        if (scacchieraS.partitaService.pezzoXY != null) {
            if (scacchieraS.partitaService.pezzoXY.isWhite()) {
                if (PartitaService.lastClickedPezzo != null && PartitaService.lastClickedPezzo.isWhite()) {

                    try {
                        throw new TurnoBianco();
                    } catch (TurnoBianco ex) {
                        return;
                    }
                }
            }
            if (!scacchieraS.partitaService.pezzoXY.isWhite()) {

                if (PartitaService.lastClickedPezzo != null && !PartitaService.lastClickedPezzo.isWhite()) {

                    try {
                        throw new TurnoNero();
                    } catch (TurnoNero ex) {
                        return;
                    }
                }
            }
        }

        scacchieraS.selectedPiece = scacchieraS.partitaService.pezzoXY;

        if (scacchieraS.partitaService.pezzoXY != null) {

            //implementazione log delle mosse sia su file che su schermo
            logger.info(" {} {} è stata spostato da col: {}  rows: {}", scacchieraS.partitaService.pezzoXY.nome, scacchieraS.partitaService.pezzoXY.isWhite ? "bianco" : "nero", col, rows);
            logMessageGiocatore = String.format("%s %s è stata spostata da col: %d  rows: %d", scacchieraS.partitaService.pezzoXY.nome, scacchieraS.partitaService.pezzoXY.isWhite ? "bianco" : "nero", col, rows);

        }

    }


    /**
     * Crea una nuova mossa e se è valida la esegue. Qui troviamo il log dei file che
     * indica la posizione di arrivo del pezzo. Gli ultimi due if ci permettono di controllare se nella lista di pezzi
     * ci sono i due re, in caso contrario è scacco matto.
     *
     * @param col
     * @param rows
     */
    public void ComportamentoRilascioGiocatorevGiocatore(int col, int rows) {

        if (scacchieraS.selectedPiece != null) {


            Move move = new Move(scacchieraS, scacchieraS.selectedPiece, col, rows);


            if (scacchieraS.partitaService.isvalidMove(move)) {
                if (patta == 50) {
                    JOptionPane.showMessageDialog(null, "La partita è patta\n sono state eseguite più di 50 mosse", "Patta", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(1);
                }
                patta++;
                PartitaService.lastClickedPezzo = scacchieraS.partitaService.pezzoXY;
                scacchieraS.makeMove(move);

            } else {

                scacchieraS.selectedPiece.xPos = scacchieraS.selectedPiece.colonne * ScacchieraService.gCaselle;
                scacchieraS.selectedPiece.yPos = scacchieraS.selectedPiece.righe * ScacchieraService.gCaselle;
            }

            if (scacchieraS.selectedPiece != null) {

                //implementazione log delle mosse sia su file che su schermo
                logger.info(" a col: {}  rows: {} \n", scacchieraS.selectedPiece.colonne, scacchieraS.selectedPiece.righe);
                logMessageGiocatore = String.format("a col: %d  rows: %d \n", scacchieraS.selectedPiece.colonne, scacchieraS.selectedPiece.righe);

            }
            scacchieraS.selectedPiece = null;
        }

        //Controlla se c'è ancora il re dentro l'arraylist, in caso contrario è scacco matto
        if (!PartitaService.giocatoreB.verificaReB()) {
            throw new ScaccoMattoNero();
        }

        if (!PartitaService.giocatoreN.verificaReN()) {
            throw new ScaccoMattoBianco();
        }

        i++;

    }

}
