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

    private String npcStatus = "";

    private int counter;
    private String[] dialogue;

    public Dialogue()
    {
        setText(npcStatus);
        counter = 0;
        dialogue = new String[3];
        //dialoguesTextArea.setText(dialogue[0]);
        //String string = dialoguesTextArea.toString();

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
            System.out.println("before:" + counter);
            counter --;
            dialoguesTextArea.setText(dialogue[counter]);
            System.out.println("after:" + counter);
        }
    }

    private void afterButton()
    {
        if (counter < dialogue.length - 1) {
            counter ++;
            dialoguesTextArea.setText(dialogue[counter]);
        }

    }

    private void onCancel()
    {
        Dialogue.this.dispose();
    }

    public void setText(String npcStatus)
    {
        this.npcStatus = npcStatus;
        String string = "CryingNpc";
        if(string.indexOf("CryingNpc") != -1)
        //if(this.npcStatus.contains("CryingNpc"))
        {
            dialogue[0] = "Hello";
            dialogue[1] = "my name is libgdx";
            dialogue[2] = "nigga bye";
        }
        else
        {
            System.out.println("test");
        }
        dialoguesTextArea.setText(dialogue[0]);
    }

}