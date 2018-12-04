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
    private JTextArea dialoguesTextArea;

    private String npcName = "";

    private int counter;
    private String[] dialogue;

    public Dialogue(String npcName)
    {
        counter = 0;
        dialogue = new String[3];
        setText(npcName);

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
            }
        });

        // call onCancel() when cross is clicked
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void previousButton()
    {
        if (counter > 0)
        {
            counter --;
            dialoguesTextArea.setText(dialogue[counter]);
        }
    }

    private void afterButton()
    {
        if (counter < dialogue.length - 1)
        {
            counter ++;
            dialoguesTextArea.setText(dialogue[counter]);
        }

    }

    private void onCancel()
    {
        Dialogue.this.dispose();
    }

    public void setText(String npcName)
    {
        if(npcName.equals("Rick"))
        {
            dialogue[0] = "Hello";
            dialogue[1] = "my name is libgdx";
            dialogue[2] = "nigga bye";
        }
        else if(npcName.equals("C"))
        {
            dialogue[0] = "OH MY GOD!!!";
            dialogue[1] = "I have lost all my shoes";
            dialogue[2] = "";
        }
        else if (npcName.equals("Enemy"))
        {
            dialogue[0] = "Grrrrrrrr";
            dialogue[1] = "Nuuuuuuuu";
            dialogue[2] = "I am DEAD!!!";
        }
        dialoguesTextArea.setText(dialogue[0]);
    }

}