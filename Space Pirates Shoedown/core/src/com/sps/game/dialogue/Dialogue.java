package com.sps.game.dialogue;

import com.badlogic.gdx.Gdx;
import com.sps.game.Sprites.InteractiveNPCMoving;

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
    private InteractiveNPCMoving npc;

    private String npcName = "";

    private int counter;
    private String[] dialogue;
    private boolean cancel;

    public Dialogue(String npcName)
    {
        cancel = false;
        counter = 0;
        dialogue = new String[3];
        dialogue[0] = "";
        dialogue[1] = "";
        dialogue[2] = "";
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

    private boolean onCancel()
    {
        cancel = true;
        Dialogue.this.dispose();
        return cancel;
    }

    public void setText(String npcName)
    {
        if(npcName.equals("Linda"))
        {
            dialogue[0] = "*crying*";
            dialogue[1] = "someone....is..inside..my...house...";
            dialogue[2] = "HELP ME!!!!";
        }
        else if(npcName.equals("Bob"))
        {
            dialogue[0] = "OH MY GOD!!!";
            dialogue[1] = "I have lost all my shoes";
            dialogue[2] = "What's going on?!";
        }
        else if(npcName.equals("Ellie"))
        {
            dialogue[0] = "AHHHHH!!!!!";
            dialogue[1] = "WHAT IS THAT!!!!";
            dialogue[2] = "WHERE DID IT COME FROM!!!!";
        }
        else if(npcName.equals("Mo"))
        {
            dialogue[0] = "*SCREAMING*";
            dialogue[1] = "SOMETHING WEIRD IS ATTACKING EVERYONE....*CRYING*";
            dialogue[2] = "LINDA IS CRYING";
        }
        else if (npcName.equals("Enemy"))
        {
            dialogue[0] = "You may have beaten me";
            dialogue[1] = "But...you can't beat all of us!!!";
            dialogue[2] = "*DYING*" + " \n " + "*MAP DROPPED*";
        }
        if(dialogue[0] != null) {
            dialoguesTextArea.setText(dialogue[0]);
        }
    }

}