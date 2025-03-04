package scacchi.engine.service;

import scacchi.data.pedine.Pezzo;


public class Move {
    int vecchieColonne;
    int vecchieRighe;
    public int nuoveColonne;
    public int nuoveRighe;

    public Pezzo pezzo;
    public Pezzo capture;

    /**
     * Il costruttore della classe Move, prende in input una scacchiera, un pezzo, nuove righe e nuove colonne,
     * e lo usiamo per aggiornare la posizione del pezzo nel metodo MakeMove.
     *
     * @param nuoveColonne
     * @param nuoveRighe
     * @param pezzo
     * @param scacchiera
     */
    public Move(ScacchieraService scacchiera, Pezzo pezzo, int nuoveColonne, int nuoveRighe) {
        this.vecchieColonne = pezzo.colonne;
        this.vecchieRighe = pezzo.righe;
        this.nuoveColonne = nuoveColonne;
        this.nuoveRighe = nuoveRighe;
        this.pezzo = pezzo;
        this.capture = scacchiera.getPezzo(nuoveColonne, nuoveRighe);
    }


    /**
     * IsValidMovement viene utilizzato per definire se una mossa da eseguire è valida, è un controllo che ci permetterà di eseguire la mossa.
     *
     * @param <T>
     * @param colonne
     * @param righe
     */
    public <T extends Pezzo> boolean isValidMovement(T t, int colonne, int righe) {

        switch (t.nome) {

            case "alfiere":
                return Math.abs(t.colonne - colonne) == Math.abs(t.righe - righe);

            case "cavallo":
                return Math.abs(colonne - t.colonne) * Math.abs(righe - t.righe) == 2;

            case "pedone":
                int colorIndex = t.isWhite ? 1 : -1;

                //push pedone 1
                if (t.colonne == colonne && righe == t.righe - colorIndex && t.scacchiera.getPezzo(colonne, righe) == null)
                    return true;

                //push pedone 2
                if (t.righe == 6 | t.righe == 1 && t.isFristMove && t.colonne == colonne && righe == t.righe - colorIndex * 2 && t.scacchiera.getPezzo(colonne, righe) == null && t.scacchiera.getPezzo(colonne, righe + colorIndex) == null)
                    return true;

                //cattura a sinistra
                if (colonne == t.colonne - 1 && righe == t.righe - colorIndex && t.scacchiera.getPezzo(colonne, righe) != null)
                    return true;

                //cattura a destra
                if (colonne == t.colonne + 1 && righe == t.righe - colorIndex && t.scacchiera.getPezzo(colonne, righe) != null)
                    return true;

                return false;

            case "re":
                return Math.abs((colonne - t.colonne) * (righe - t.righe)) == 1 || Math.abs(colonne - t.colonne) + Math.abs(righe - t.righe) == 1;

            case "regina":
                return t.colonne == colonne || t.righe == righe || Math.abs(t.righe - righe) == Math.abs(t.colonne - colonne);

            case "torre":
                return t.colonne == colonne || t.righe == righe;

            default:
                return false;
        }
    }

    /**
     * moveCollidewithPiece è un metodo booleano che viene utilizzato anche esso al fine di eseguire la mossa,
     * controlla se il pezzo selezionato entra in contatto con un altro pezzo nella direzione selezionata.
     *
     * @param <T>
     * @param colonne
     * @param righe
     */
    public <T extends Pezzo> boolean moveCollidewithPiece(T t, int colonne, int righe) {

        switch (t.nome) {

            case "alfiere": {
                // sinistra sopra
                if (t.colonne > colonne && t.righe > righe)
                    for (int i = 1; i < Math.abs(t.colonne - colonne); i++)
                        if (t.scacchiera.getPezzo(t.colonne - i, t.righe - i) != null)
                            return true;

                // destra sopra
                if (t.colonne < colonne && t.righe > righe)
                    for (int i = 1; i < Math.abs(t.colonne - colonne); i++)
                        if (t.scacchiera.getPezzo(t.colonne + i, t.righe - i) != null)
                            return true;

                // sinistra sotto
                if (t.colonne > colonne && t.righe < righe)
                    for (int i = 1; i < Math.abs(t.colonne - colonne); i++)
                        if (t.scacchiera.getPezzo(t.colonne - i, t.righe + i) != null)
                            return true;

                // sinistra sotto
                if (t.colonne < colonne && t.righe < righe)
                    for (int i = 1; i < Math.abs(t.colonne - colonne); i++)
                        if (t.scacchiera.getPezzo(t.colonne + i, t.righe + i) != null)
                            return true;

                return false;
            }

            case "regina": {
                if (t.colonne == colonne || t.righe == righe) {

                    //sinistra
                    if (t.colonne > colonne)
                        for (int i = t.colonne - 1; i > colonne; i--)
                            if (t.scacchiera.getPezzo(i, t.righe) != null)
                                return true;

                    //destra
                    if (t.colonne < colonne)
                        for (int i = t.colonne + 1; i < colonne; i++)
                            if (t.scacchiera.getPezzo(i, t.righe) != null)
                                return true;

                    //sopra
                    if (t.righe > righe)
                        for (int i = t.righe - 1; i > righe; i--)
                            if (t.scacchiera.getPezzo(t.colonne, i) != null)
                                return true;

                    //sotto
                    if (t.righe < righe)
                        for (int i = t.righe + 1; i < righe; i++)
                            if (t.scacchiera.getPezzo(t.colonne, i) != null)
                                return true;


                } else {

                    // sinistra sopra
                    if (t.colonne > colonne && t.righe > righe)
                        for (int i = 1; i < Math.abs(t.colonne - colonne); i++)
                            if (t.scacchiera.getPezzo(t.colonne - i, t.righe - i) != null)
                                return true;

                    // destra sopra
                    if (t.colonne < colonne && t.righe > righe)
                        for (int i = 1; i < Math.abs(t.colonne - colonne); i++)
                            if (t.scacchiera.getPezzo(t.colonne + i, t.righe - i) != null)
                                return true;

                    // sinistra sotto
                    if (t.colonne > colonne && t.righe < righe)
                        for (int i = 1; i < Math.abs(t.colonne - colonne); i++)
                            if (t.scacchiera.getPezzo(t.colonne - i, t.righe + i) != null)
                                return true;

                    // sinistra sotto
                    if (t.colonne < colonne && t.righe < righe)
                        for (int i = 1; i < Math.abs(t.colonne - colonne); i++)
                            if (t.scacchiera.getPezzo(t.colonne + i, t.righe + i) != null)
                                return true;
                }

                return false;
            }

            case "torre": {

                //sinistra
                if (t.colonne > colonne)
                    for (int i = t.colonne - 1; i > colonne; i--)
                        if (t.scacchiera.getPezzo(i, t.righe) != null)
                            return true;

                //destra
                if (t.colonne < colonne)
                    for (int i = t.colonne + 1; i < colonne; i++)
                        if (t.scacchiera.getPezzo(i, t.righe) != null)
                            return true;

                //sopra
                if (t.righe > righe)
                    for (int i = t.righe - 1; i > righe; i--)
                        if (t.scacchiera.getPezzo(t.colonne, i) != null)
                            return true;

                //sotto
                if (t.righe < righe)
                    for (int i = t.righe + 1; i < righe; i++)
                        if (t.scacchiera.getPezzo(t.colonne, i) != null)
                            return true;

            }

            default:
                return false;
        }

    }
}

