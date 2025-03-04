package scacchi.data.pedine;

import scacchi.engine.service.ScacchieraService;

import java.awt.image.BufferedImage;
import java.io.Serializable;


public class Alfiere extends Pezzo implements Serializable {

    /**
     * Classe alfiere, nel costruttore della classe troviamo anche l'immagine grafica della pedina
     *
     * @param scacchiera
     * @param colonne
     * @param righe
     * @param isWhite
     */
    public Alfiere(ScacchieraService scacchiera, int colonne, int righe, boolean isWhite) {
        super(scacchiera);
        this.colonne = colonne;
        this.righe = righe;
        this.xPos = colonne * scacchiera.gCaselle;
        this.yPos = righe * scacchiera.gCaselle;
        this.isWhite = isWhite;
        this.nome = "alfiere";
        this.value= 6;
        this.imgSecondaria = imgPrincipale.getSubimage(2 * imgScale, isWhite ? 0 : imgScale, 200, 200).getScaledInstance(78, 78, BufferedImage.SCALE_SMOOTH);
    }

    @Override
    public boolean isWhite() {
        return super.isWhite();
    }

    @Override
    public int getColonne() {
        return super.getColonne();
    }

    @Override
    public int getRighe() {
        return super.getRighe();
    }
}






















