package scacchi.gui;

import scacchi.engine.input.InputA;
import scacchi.engine.input.InputGiocatorevComputerS;
import scacchi.engine.input.InputGiocatorevGiocatoreS;
import scacchi.engine.service.Move;
import scacchi.engine.service.ScacchieraService;

import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;


public class Input extends MouseAdapter implements Serializable {

    public static DisplayScacchiera scacchiera;
    public static InputGiocatorevGiocatoreS inputGiocatorevsGiocatore = new InputGiocatorevGiocatoreS();
    public InputGiocatorevComputerS inputComputervsGiocatore = new InputGiocatorevComputerS();
    public static UndoManager m = new UndoManager();

    public Input(DisplayScacchiera scacchieraS) {
        scacchiera = scacchieraS;
    }


    /**
     * Il medodo mousePressed prende in input un mouseEvent che verrà usato per ricavare le coordinate x e y del pezzo nella posizione selezionata , nel momento
     * in cui si clicca il mouse.
     * Verifica se il titolo del frame corrente è "GiocatorevGiocatore" oppure "ComputervGiocatore" e in base al titolo
     * va ad applicare uno dei due mouse pressed.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

        int col = e.getX() / ScacchieraService.gCaselle;
        int rows = e.getY() / ScacchieraService.gCaselle;

        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());

        if (currentFrame.getTitle().equals("Giocatore Vs Giocatore")) {

            try {
                inputGiocatorevsGiocatore.ComportamentoClickGiocatorevGiocatore(col, rows);
                JTextAreaPanel.appendToLog(inputGiocatorevsGiocatore.logMessageGiocatore);
            } catch (Exception ex) {

                if (Input.inputGiocatorevsGiocatore.i > 1) {
                    Input.inputGiocatorevsGiocatore.i--;
                }
            }
        } else {

            try {
                inputComputervsGiocatore.ComportamentoClickComputervGiocatore(col, rows);
                JTextAreaPanel.appendToLog(inputComputervsGiocatore.logMessageComputer);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Il metodo mouseDragged ci permette (nel momento che noi trasciniamo il mouse) di verificare se le caselle
     * sono "libere", in base alle coordinate date da getX e getY , vengono aggiornate le xPos e yPos del pezzo selezionato.
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {

        if (InputA.scacchieraS.selectedPiece != null) {
            InputA.scacchieraS.selectedPiece.xPos = e.getX() - ScacchieraService.gCaselle / 2;
            InputA.scacchieraS.selectedPiece.yPos = e.getY() - ScacchieraService.gCaselle / 2;
            scacchiera.repaint();

        }
    }


    /**
     * Questo metodo gestisce i mouseRealeased.
     * Controlla quali metodi chiamare in base al nome del frame, aggiunge la mossa eseguita all'undo.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        int col = e.getX() / ScacchieraService.gCaselle;
        int rows = e.getY() / ScacchieraService.gCaselle;

        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor((Component) e.getSource());

        if (currentFrame.getTitle().equals("Giocatore Vs Giocatore")) {

            try {
                inputGiocatorevsGiocatore.ComportamentoRilascioGiocatorevGiocatore(col, rows);
                scacchiera.repaint();
                JTextAreaPanel.appendToLog(inputGiocatorevsGiocatore.logMessageGiocatore);

                UndoableEdit undoableEdit = new AbstractUndoableEdit() {
                    @Override
                    public void undo() throws CannotUndoException {

                        Move moveU = new Move(InputA.scacchieraS, inputGiocatorevsGiocatore.pA[inputGiocatorevsGiocatore.i], inputGiocatorevsGiocatore.xA[inputGiocatorevsGiocatore.i], inputGiocatorevsGiocatore.yA[inputGiocatorevsGiocatore.i]);
                        InputA.scacchieraS.partitaService.lastClickedPezzo = inputGiocatorevsGiocatore.pA[inputGiocatorevsGiocatore.i];
                        InputA.scacchieraS.makeMove(moveU);
                        scacchiera.repaint();
                    }
                };
                m.addEdit(undoableEdit);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Scacco Matto", JOptionPane.INFORMATION_MESSAGE);
                System.exit(1);
            }

        } else {

            try {

                inputComputervsGiocatore.ComportamentoRilascioComputervGiocatore(col, rows);
                scacchiera.repaint();
                //  appendToLog(inputComputervsGiocatore.logMessageComputer);

                UndoableEdit undoableEdit = new AbstractUndoableEdit() {
                    @Override
                    public void undo() throws CannotUndoException {

                        Move moveU = new Move(InputA.scacchieraS, inputComputervsGiocatore.undo, inputComputervsGiocatore.x, inputComputervsGiocatore.y);
                        InputA.scacchieraS.partitaService.lastClickedPezzo = inputComputervsGiocatore.undo;
                        InputA.scacchieraS.makeMove(moveU);
                        scacchiera.repaint();
                    }
                };
                m.addEdit(undoableEdit);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Scacco Matto", JOptionPane.INFORMATION_MESSAGE);
                System.exit(1);
            }

            inputComputervsGiocatore.CicloBot();
        }
    }
}
