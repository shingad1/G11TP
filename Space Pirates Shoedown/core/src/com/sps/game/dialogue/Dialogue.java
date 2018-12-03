package com.sps.game.dialogue;

import javax.swing.*;
import java.awt.event.*;

/**
 * This class
 * @author Mahamuda Akhter
 * @version 1.0
 */

public class Dialogue extends JDialog {
    private JPanel contentPane;
    private JButton buttonPrevious;
    private JButton buttonCancel;
    private JButton buttonNext;
    private JTextArea helloTextField;

    private int counter;
    private String[] cryingNpc;

    public Dialogue() {
        setText();
        counter = 0;
        helloTextField.setText(cryingNpc[0]);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonPrevious);

        buttonPrevious.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                previousButton();
            }
        });

        buttonNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                afterButton();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
                //goBack();
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

    private void previousButton() {
        if (counter > 0) {
            System.out.println("before:" + counter);
            counter--;
            helloTextField.setText(cryingNpc[counter]);
            System.out.println("after:" + counter);

        }
    }

    private void afterButton() {
        if (counter < cryingNpc.length - 1) {
            counter++;
            helloTextField.setText(cryingNpc[counter]);

        }

    }

    private void onCancel() {
        // add your code here if necessary
        Dialogue.this.dispose();
    }

    public void setText() {
        cryingNpc = new String[3];
        cryingNpc[0] = "Hello";
        cryingNpc[1] = "my name is libgdx";
        cryingNpc[2] = "nigga bye";
    }

}