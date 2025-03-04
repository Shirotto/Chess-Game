package scacchi.engine.input;

import scacchi.engine.exception.ScaccoMattoBianco;
import scacchi.engine.exception.ScaccoMattoNero;
import scacchi.engine.exception.TurnoBianco;
import scacchi.engine.exception.TurnoNero;
import scacchi.engine.service.Move;
import scacchi.engine.service.PartitaService;
import scacchi.engine.service.ScacchieraService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

public class InputGiocatorevComputerS extends InputA {

    public int i = 355, j = 250, k = 400, f = 0;
    private int patta = 0;

    /**
     * Metodo che definisce il comportamento del mouse al click, viene usato per il giocatore vs computer.
     *
     * @param col  queste sono le colonneù
     * @param rows queste le righe
     */
    public void ComportamentoClickComputervGiocatore(int col, int rows) {

        scacchieraS.partitaService.pezzoXY = scacchieraS.getPezzo(col, rows);

        x = col;
        y = rows;
        undo = scacchieraS.partitaService.pezzoXY;

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

        if (scacchieraS.partitaService.pezzoXY != null) {

            //implementazione log delle mosse sia su file che su schermo
            logger.info(" {} {} è stata spostato da col: {}  rows: {}", scacchieraS.partitaService.pezzoXY.nome, scacchieraS.partitaService.pezzoXY.isWhite ? "bianco" : "nero", col, rows);
            logMessageComputer = String.format("%s %s è stata spostata da col: %d  rows: %d", scacchieraS.partitaService.pezzoXY.nome, scacchieraS.partitaService.pezzoXY.isWhite ? "bianco" : "nero", col, rows);

        }

        scacchieraS.selectedPiece = scacchieraS.partitaService.pezzoXY;

    }


    /**
     * Metodo che definisce il comportamento al rilascio del mouse, viene usato per il giocatore vs computer.Simile a Giocatore vs Giocatore
     * ma con l'aggiunta dell'implementazione del comportamento del Bot.
     *
     * @param col
     * @param rows
     */
    public void ComportamentoRilascioComputervGiocatore(int col, int rows) {

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

            try {
                //Bot che mi permette di poter fare delle azioni automatiche nel giocatore vs computer
                Robot robot = new Robot();

                int startX = i;
                int startY = j;

                int endX = i;
                int endY = k;


                robot.mouseMove(startX, startY);
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.mouseMove(endX, endY);
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);


            } catch (AWTException ex) {

            }
            if (scacchieraS.selectedPiece != null) {

                //implementazione log delle mosse sia su file che su schermo
                logger.info(" a col: {}  rows: {} \n", scacchieraS.selectedPiece.colonne, scacchieraS.selectedPiece.righe);
                logMessageComputer = String.format("a col: %d  rows: %d \n", scacchieraS.selectedPiece.colonne, scacchieraS.selectedPiece.righe);
            }

        }

        scacchieraS.selectedPiece = null;

        //Controlla se c'è ancora il re dentro l'arraylist in caso contrario è scacco matto
        if (!PartitaService.giocatoreB.verificaReB()) {
            throw new ScaccoMattoNero();
        }

        if (!PartitaService.giocatoreN.verificaReN()) {
            throw new ScaccoMattoBianco();
        }
    }


    /**
     * Metodo viene usato dal bot per ciclare la torre avanti e indietro.
     */
    public void CicloBot() {
        if (f % 2 == 0) {

            j = 155;
            k = 300;
            f++;

        } else {

            j = 300;
            k = 155;
            f++;

        }
    }
}

