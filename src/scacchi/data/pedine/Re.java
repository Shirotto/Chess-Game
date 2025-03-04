package scacchi.data.pedine;

import scacchi.engine.service.ScacchieraService;

import java.awt.image.BufferedImage;
import java.io.Serializable;


public class Re extends Pezzo implements Serializable {
    /**
     * Classe re, nel costruttore della classe troviamo anche l'immagine grafica della pedina
     *
     * @param scacchiera
     * @param colonne
     * @param righe
     * @param isWhite
     */
    public Re(ScacchieraService scacchiera, int colonne, int righe, boolean isWhite) {
        super(scacchiera);
        this.colonne = colonne;
        this.righe = righe;
        this.xPos = colonne * scacchiera.gCaselle;
        this.yPos = righe * scacchiera.gCaselle;
        this.isWhite = isWhite;
        this.nome = "re";
        this.value = 10;
        this.imgSecondaria = imgPrincipale.getSubimage(0 * imgScale, isWhite ? 0 : imgScale, 200, 200).getScaledInstance(78, 78, BufferedImage.SCALE_SMOOTH);
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


