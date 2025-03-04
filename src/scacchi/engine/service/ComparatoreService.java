package scacchi.engine.service;


import scacchi.data.pedine.Pezzo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ComparatoreService {


    private ArrayList<Pezzo> p1, p2;

    /**
     * Metodo che prende in input un file di salvataggio e calcola il valore totale delle pedine
     *
     * @param file
     * @return
     */
    public int valPedine(File file) {

        int valueComparator = 0;

        try (FileInputStream fis = new FileInputStream(file)) {
            ObjectInputStream oo = new ObjectInputStream(fis);
            ObjectInputStream ooo = new ObjectInputStream(fis);
            p1 = (ArrayList<Pezzo>) oo.readObject();
            p2 = (ArrayList<Pezzo>) ooo.readObject();

        } catch (ClassNotFoundException | IOException e) {

        }

        for (Pezzo pezzo : p1) {
            valueComparator += pezzo.value;
        }

        for (Pezzo pezzo : p2) {
            valueComparator += pezzo.value;
        }

        return valueComparator;
    }

    /**
     * Metodo che prende in input un salvataggio della partita e calcola il numero totale delle pedine in campo
     *
     * @param file
     * @return
     */
    public int numPedine(File file) {
        int numPedineDaComparare = 0;

        try (FileInputStream fis = new FileInputStream(file)) {
            ObjectInputStream oo = new ObjectInputStream(fis);
            ObjectInputStream ooo = new ObjectInputStream(fis);
            p1 = (ArrayList<Pezzo>) oo.readObject();
            p2 = (ArrayList<Pezzo>) ooo.readObject();

        } catch (ClassNotFoundException | IOException e) {

        }

        for (Pezzo pezzo : p1) {
            numPedineDaComparare += 1;
        }

        for (Pezzo pezzo : p2) {
            numPedineDaComparare += 1;
        }
        return numPedineDaComparare;
    }
}