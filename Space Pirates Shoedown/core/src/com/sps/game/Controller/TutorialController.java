package com.sps.game.Controller;

import javax.swing.*;
import java.awt.event.*;

public class TutorialController extends JDialog {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JLabel label;

    private ImageIcon tutorial;

    public TutorialController() {

        tutorial = new ImageIcon( "core/assets/Controls1.png");

        label.setIcon(tutorial);
        label.setText(" ");


        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonCancel);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        dispose();
    }
}
