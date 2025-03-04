package scacchi.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FramePartita extends JFrame {
    public final JTextAreaPanel textAreaPanel;
    private FramePartitaS service = new FramePartitaS();

    /**
     *Il FramePartita crea tre nuovi bottoni: SalvaPartita, Resa e undo.
     */
    public FramePartita() {

        textAreaPanel = new JTextAreaPanel();
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton salvaPartitaButton = new JButton("Salva partita");
        JButton resaButton = new JButton("Resa");
        JButton undoButton = new JButton("Undo");

        setMinimumSize(new Dimension(952, 640));
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setResizable(false);
        setName("Giocatore Vs Giocatore");

        GridBagConstraints textAreaConstraints = new GridBagConstraints();
        textAreaConstraints.gridx = 1;
        textAreaConstraints.gridy = 0;
        textAreaConstraints.gridwidth = 10;
        textAreaConstraints.gridheight = 10;
        textAreaConstraints.fill = GridBagConstraints.BOTH;
        textAreaConstraints.weightx = 10.0;
        textAreaConstraints.weighty = 10.0;
        textAreaConstraints.insets = new Insets(250, 0, 0, 0);
        textAreaPanel.setMaximumSize(new Dimension(310, 635));
        add(textAreaPanel, textAreaConstraints);

        DisplayScacchiera displayScacchiera = new DisplayScacchiera();
        GridBagConstraints displayScacchieraConstraints = new GridBagConstraints();
        displayScacchieraConstraints.gridx = 0;
        displayScacchieraConstraints.gridy = 0;
        displayScacchieraConstraints.gridwidth = 1;
        displayScacchieraConstraints.gridheight = 1;
        displayScacchieraConstraints.fill = GridBagConstraints.BOTH;
        displayScacchieraConstraints.weightx = 1.0;
        displayScacchieraConstraints.weighty = 1.0;
        add(displayScacchiera, displayScacchieraConstraints);

        GridBagConstraints buttonPanelConstraints = new GridBagConstraints();
        buttonPanelConstraints.gridx = 1;
        buttonPanelConstraints.gridy = 0;
        buttonPanelConstraints.anchor = GridBagConstraints.NORTHEAST;
        buttonPanel.add(salvaPartitaButton);
        buttonPanel.add(resaButton);
        buttonPanel.add(undoButton);
        add(buttonPanel, buttonPanelConstraints);

        salvaPartitaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                service.salvaButton(e);

            }
        });


        resaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                service.resaButton(e);

            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                service.undoButton(e);

                if (Input.inputGiocatorevsGiocatore.i > 1) {
                    Input.inputGiocatorevsGiocatore.i--;
                }
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JTextAreaPanel getTextAreaPanel() {
        return textAreaPanel;
    }

}
