package scacchi.engine.service;

import scacchi.data.pedine.*;
import scacchi.engine.input.InputGiocatorevGiocatoreS;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class GameData implements Serializable {

    public static ArrayList<Pezzo> p1 = new ArrayList<>();

    public static ArrayList<Pezzo> p2 = new ArrayList<>();


    /**
     * Metodo che prende il path dove si vuole salvare il file,
     * nel caso non esistesse crea la cartella Salvataggi e permette di salvare la lista dei pezzi delle pedine
     * della partita che si desidera salvare, viene salvato il nome del pezzo, la posizione e il colore.
     * .In caso di avvenuto salvataggio uscira a riga di comando un messaggio con scritto salvato
     * invece in caso contrario uscirà un messaggio con scritto errore.
     */
    public void Salva() {
        String pathCartella = System.getProperty("user.dir") + File.separator + "Salvataggi" + File.separator;
        File cartella = new File(pathCartella);

        if (!cartella.exists()) {
            cartella.mkdir();
        }

        LocalDateTime orario = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm");
        String timestamp = orario.format(formatter);
        String nomeFile = pathCartella + "salvataggio_" + timestamp + ".chess";

        try (FileOutputStream fos = new FileOutputStream(nomeFile)) {
            ObjectOutputStream oo = new ObjectOutputStream(fos);
            ObjectOutputStream ooo = new ObjectOutputStream(fos);

            oo.writeObject(PartitaService.giocatoreB.listaPezzi);
            ooo.writeObject(PartitaService.giocatoreN.listaPezzi);
            System.out.println("salvato");

        } catch (Exception e) {
            System.out.println("errore");
        }
    }


    /**
     * Metodo che prende in input il file salvato , deserializza le informazioni salvate
     * e aggiunge i pezzi in due nuovi array list che verrannp usati per generare di nuovo le pedine salvate.
     *
     * @param file
     */
    public void Carica(File file) {

        try (FileInputStream fis = new FileInputStream(file)) {
            ObjectInputStream oo = new ObjectInputStream(fis);
            ObjectInputStream ooo = new ObjectInputStream(fis);
            p1 = (ArrayList<Pezzo>) oo.readObject();
            p2 = (ArrayList<Pezzo>) ooo.readObject();

        } catch (ClassNotFoundException | IOException e) {
          System.out.println("errore");
        }
    }


    /**
     * Metodo che prende gli array list deserializzati e permette di riaggiungerli alla scacchiera ridisegnandoli da capo,
     * utilizzando le posizioni e il colore delle pedine salvate negli array list presi dal metodo Carica().
     *
     * @param scacchiera
     */
    public static void aggPezzoB(ScacchieraService scacchiera) {

        for (Pezzo pezzo : p1) {

            switch (pezzo.nome) {

                case ("alfiere"):
                    PartitaService.giocatoreB.listaPezzi.add(new Alfiere(scacchiera, pezzo.getColonne(), pezzo.getRighe(), true));
                    break;
                case ("cavallo"):
                    PartitaService.giocatoreB.listaPezzi.add(new Cavallo(scacchiera, pezzo.getColonne(), pezzo.getRighe(), true));
                    break;
                case ("pedone"):
                    PartitaService.giocatoreB.listaPezzi.add(new Pedone(scacchiera, pezzo.getColonne(), pezzo.getRighe(), true));
                    break;
                case ("re"):
                    PartitaService.giocatoreB.listaPezzi.add(new Re(scacchiera, pezzo.getColonne(), pezzo.getRighe(), true));
                    break;
                case ("regina"):
                    PartitaService.giocatoreB.listaPezzi.add(new Regina(scacchiera, pezzo.getColonne(), pezzo.getRighe(), true));
                    break;
                case ("torre"):
                    PartitaService.giocatoreB.listaPezzi.add(new Torre(scacchiera, pezzo.getColonne(), pezzo.getRighe(), true));
                    break;
            }
        }
    }


    /**
     * Metodo che prende gli array list deserializzati e permette di riaggiungerli alla scacchiera ridisegnandoli da capo,
     * utilizzando le posizioni e il colore delle pedine salvate negli array list presi dal metodo Carica().
     *
     * @param scacchiera
     */
    public static void aggPezzoN(ScacchieraService scacchiera) {

        for (Pezzo pezzo : p2) {

            switch (pezzo.nome) {

                case ("alfiere"):
                    PartitaService.giocatoreN.listaPezzi.add(new Alfiere(scacchiera, pezzo.getColonne(), pezzo.getRighe(), false));
                    break;
                case ("cavallo"):
                    PartitaService.giocatoreN.listaPezzi.add(new Cavallo(scacchiera, pezzo.getColonne(), pezzo.getRighe(), false));
                    break;
                case ("pedone"):
                    PartitaService.giocatoreN.listaPezzi.add(new Pedone(scacchiera, pezzo.getColonne(), pezzo.getRighe(), false));
                    break;
                case ("re"):
                    PartitaService.giocatoreN.listaPezzi.add(new Re(scacchiera, pezzo.getColonne(), pezzo.getRighe(), false));
                    break;
                case ("regina"):
                    PartitaService.giocatoreN.listaPezzi.add(new Regina(scacchiera, pezzo.getColonne(), pezzo.getRighe(), false));
                    break;
                case ("torre"):
                    PartitaService.giocatoreN.listaPezzi.add(new Torre(scacchiera, pezzo.getColonne(), pezzo.getRighe(), false));
                    break;
            }
        }
    }
}




