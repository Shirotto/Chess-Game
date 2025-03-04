package scacchi.data.pedine;

import scacchi.engine.service.ScacchieraService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;


public abstract class Pezzo implements Serializable {

    public int colonne, righe;

    public boolean isWhite; //Uso isWhite per ritagliare l'immagine in maniera corretta per ricavare la pedina nera o bianca

    public String nome;
    public int xPos, yPos;
    public Pezzo pezzo;
    public int value;

    transient BufferedImage imgPrincipale;
    public boolean isFristMove = true;
    transient Image imgSecondaria;

    transient public ScacchieraService scacchiera;

    public Pezzo(ScacchieraService scacchiera) {
        this.scacchiera = scacchiera;
    }

    public boolean isWhite() {
        return isWhite;
    }

    //Metodo che viene utilizzato per l'image dei pezzi
    {
        try {
            imgPrincipale = ImageIO.read(ClassLoader.getSystemResourceAsStream("chess.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getColonne() {
        return colonne;
    }

    public int getRighe() {
        return righe;
    }

    public int imgScale = imgPrincipale.getWidth() / 6;

    public void paint(Graphics2D g2d) {
        g2d.drawImage(imgSecondaria, xPos, yPos, null);
    }


}