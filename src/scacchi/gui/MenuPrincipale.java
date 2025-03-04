package scacchi.gui;


import scacchi.engine.service.ComparatoreService;
import scacchi.engine.service.GameData;
import scacchi.engine.service.PartitaService;
import scacchi.engine.service.ScacchieraService;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import static scacchi.engine.input.InputA.scacchieraS;


public class MenuPrincipale extends ScacchieraService {
    private JPanel Start1;
    private JButton caricaPartitaButton;
    private JButton giocatoreVsGiocatoreButton;
    private JButton giocatoreVsComputerButton;
    private JButton comparaPartiteButton;
    private static JFrame frame;
    public static File[] filesDaConfrontare;


    /**
     * Costruttore di MenuPrincipale contiene l'implementazione GiocatorevGiocatore, GiocatorevComputer e carica Partita.
     */
    public MenuPrincipale() {

        giocatoreVsGiocatoreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SwingUtilities.invokeLater(() -> {
                    FramePartita frame = new FramePartita();
                    frame.setTitle("Giocatore Vs Giocatore");
                    JTextAreaPanel textAreaPanel = frame.getTextAreaPanel();
                });
                PartitaService.giocatoreB.aggPezzo(scacchieraS);
                PartitaService.giocatoreN.aggPezzo(scacchieraS);
                frame.dispose();
            }
        });


        //Metodo che crea un nuovo frame Giocatore vs Computer
        giocatoreVsComputerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SwingUtilities.invokeLater(() -> {
                    FramePartita frame = new FramePartita();
                    frame.setTitle("Giocatore Vs Computer");
                    JTextAreaPanel textAreaPanel = frame.getTextAreaPanel();
                });
                PartitaService.giocatoreB.aggPezzo(scacchieraS);
                PartitaService.giocatoreN.aggPezzo(scacchieraS);
                frame.dispose();
            }
        });

        //Metodo che crea frame per caricare partita
        caricaPartitaButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser filescelto = new JFileChooser();
                GameData game = new GameData();

                String pathCartella = System.getProperty("user.dir") + File.separator + "Salvataggi" + File.separator;
                filescelto.setCurrentDirectory(new File(pathCartella));
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("File chess", "chess");
                filescelto.setFileFilter(filtro);
                if (filescelto.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File fileSelezionato = filescelto.getSelectedFile();

                    game.Carica(fileSelezionato);
                    PartitaService.giocatoreB.listaPezzi.clear();
                    PartitaService.giocatoreN.listaPezzi.clear();
                    GameData.aggPezzoB(scacchieraS);
                    GameData.aggPezzoN(scacchieraS);

                    SwingUtilities.invokeLater(() -> {
                        FramePartita frame = new FramePartita();
                        frame.setTitle("Giocatore Vs Giocatore");
                        JTextAreaPanel textAreaPanel = frame.getTextAreaPanel();
                    });

                    frame.dispose();
                }

            }

        });

        comparaPartiteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFileChooser fc = new JFileChooser();
                fc.setMultiSelectionEnabled(true);
                String pathCartella = System.getProperty("user.dir") + File.separator + "Salvataggi" + File.separator;
                fc.setCurrentDirectory(new File(pathCartella));
                FileNameExtensionFilter filtro = new FileNameExtensionFilter("File chess", "chess");
                fc.setFileFilter(filtro);
                if (fc.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    filesDaConfrontare = fc.getSelectedFiles();
                    String[] opzioniConfronto = {"Numero pedine", " Valore pedine"};
                    int scelta = JOptionPane.showOptionDialog(frame, "Come vuoi confrontare i file tra di loro ?", "Metodo di confronto", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opzioniConfronto, opzioniConfronto[0]);

                    if (scelta == 0) {

                        ComparatoreService prova = new ComparatoreService();
                        int numeroPedine;
                        File[] numeroFile = new File[filesDaConfrontare.length];
                        int[] numeroPedineArray = new int[filesDaConfrontare.length];

                        for (int u = 0; u < filesDaConfrontare.length; u++) {
                            numeroPedine = prova.numPedine(filesDaConfrontare[u]);
                            numeroFile[u] = filesDaConfrontare[u];
                            numeroPedineArray[u] = numeroPedine;
                        }

                        for (int i = 0; i < filesDaConfrontare.length - 1; i++) {
                            for (int j = i + 1; j < filesDaConfrontare.length; j++) {
                                if (numeroPedineArray[j] > numeroPedineArray[i]) {
                                    int tempPedine = numeroPedineArray[i];
                                    numeroPedineArray[i] = numeroPedineArray[j];
                                    numeroPedineArray[j] = tempPedine;

                                    File tempFile = numeroFile[i];
                                    numeroFile[i] = numeroFile[j];
                                    numeroFile[j] = tempFile;
                                }
                            }
                        }

                        System.out.println("La partita con il numero delle pedine piu alto è " + numeroFile[0].getName() + " con " + numeroPedineArray[0] + " pedine totali.\n");

                        System.out.println("Elenco partite in ordine di grandezza delle pedine:");
                        for (int i = 0; i < numeroFile.length; i++) {
                            System.out.println((i + 1) + ") - " + numeroFile[i].getName());
                        }
                    }

                    if (scelta == 1) {
                        ComparatoreService prova = new ComparatoreService();
                        int valore;
                        File[] partite = new File[filesDaConfrontare.length];
                        int[] valoreArray = new int[filesDaConfrontare.length];

                        for (int u = 0; u < filesDaConfrontare.length; u++) {
                            valore = prova.valPedine(filesDaConfrontare[u]);
                            partite[u] = filesDaConfrontare[u];
                            valoreArray[u] = valore;
                        }

                        for (int i = 0; i < filesDaConfrontare.length - 1; i++) {
                            for (int j = i + 1; j < filesDaConfrontare.length; j++) {
                                if (valoreArray[j] > valoreArray[i]) {
                                    int tempValore = valoreArray[i];
                                    valoreArray[i] = valoreArray[j];
                                    valoreArray[j] = tempValore;

                                    File tempFile = partite[i];
                                    partite[i] = partite[j];
                                    partite[j] = tempFile;
                                }
                            }
                        }

                        System.out.println("La partita con il valore delle pedine piu alto è " + partite[0].getName() + " con " + valoreArray[0] + " valore totale.\n");

                        System.out.println("Elenco partite in ordine di valore delle pedine:");
                        for (int i = 0; i < partite.length; i++) {
                            System.out.println((i + 1) + ") - " + partite[i].getName());
                        }
                    }
                }
            }
        });
    }


    /**
     * Metodo che apre il frame del menu Principale.
     */
    public void Start() {
        frame = new JFrame("Menu Principale");
        frame.setContentPane(new MenuPrincipale().Start1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}

