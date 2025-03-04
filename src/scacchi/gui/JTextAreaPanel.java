package scacchi.gui;

import javax.swing.*;
import java.awt.*;


//Classe che crea l'area dove verrà scritto il log delle mosse
public class JTextAreaPanel extends JPanel {

    public static JTextArea logTextArea;

    public JTextAreaPanel() {
        setLayout(new BorderLayout());
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(335, 150));
        add(scrollPane, BorderLayout.WEST);
        logTextArea.setPreferredSize(new Dimension(255, 5000));

    }

    /**
     *Metodo che prende in input una stringa che viene mostrata nel JtextareaPanel nel frame selezionato.
     *
     * @param message
     */
    public static void appendToLog(String message) {
        logTextArea.append(message + "\n");
        logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
    }
}