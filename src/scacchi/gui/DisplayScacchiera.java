package scacchi.gui;

import scacchi.engine.service.Move;
import scacchi.engine.service.PartitaService;
import scacchi.engine.service.ScacchieraService;
import scacchi.data.pedine.Pezzo;

import javax.swing.*;
import java.awt.*;

import static scacchi.engine.input.InputA.scacchieraS;


public class DisplayScacchiera extends JPanel {

    Input input = new Input(this);

    public DisplayScacchiera() {
        this.setPreferredSize(new Dimension(ScacchieraService.colonne * ScacchieraService.gCaselle, ScacchieraService.righe * ScacchieraService.gCaselle));
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }

    /**
     *Metodo che disegna la scacchiera, i blocchi verdi delle mosse valide e le pedine.
     *
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        //disegna la scacchiera
        for (int r = 0; r < ScacchieraService.righe; r++)
            for (int c = 0; c < ScacchieraService.colonne; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(227, 198, 181) : new Color(157, 105, 53));
                g2d.fillRect(c * ScacchieraService.gCaselle, r * ScacchieraService.gCaselle, ScacchieraService.gCaselle, ScacchieraService.gCaselle);
            }

        //disegna i blocchi verdi delle mosse valide
        if (scacchieraS.selectedPiece != null)
            for (int i = 0; i < ScacchieraService.righe; i++)
                for (int j = 0; j < ScacchieraService.colonne; j++) {

                    if (scacchieraS.partitaService.isvalidMove(new Move(scacchieraS, scacchieraS.selectedPiece, j, i))) {
                        g2d.setColor(new Color(68, 150, 57, 190));
                        g2d.fillRect(j * ScacchieraService.gCaselle, i * ScacchieraService.gCaselle, ScacchieraService.gCaselle, ScacchieraService.gCaselle);
                    }
                }

        // disegna le pedine

        for (Pezzo pezzo : PartitaService.giocatoreN.listaPezzi) {
            pezzo.paint(g2d);
        }


        for (Pezzo pezzo : PartitaService.giocatoreB.listaPezzi) {
            pezzo.paint(g2d);
        }



    }
}
