package com.sps.game.Controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class TutorialController1 extends JDialog {
    private JPanel contentPane;
    private JButton buttonPrevious;
    private JButton buttonNext;
    private JButton buttonCancel;
    private JLabel label;

    private int counter;

    private static final String ASSETS_PATH = "core/assets/SP/";

    private ImageIcon[] bob;

    ImageIcon iconic1;
    ImageIcon iconic2;
    ImageIcon iconic3;
    ImageIcon iconic4;
    ImageIcon iconic5;
    ImageIcon iconic6;
    ImageIcon iconic7;
    ImageIcon iconic8;
    ImageIcon iconic9;
    ImageIcon iconic10;
    ImageIcon iconic11;
    ImageIcon iconic12;

    public TutorialController1() throws IOException {

        iconic1 = new ImageIcon(ASSETS_PATH + "Slide1.png");
        iconic2 = new ImageIcon(ASSETS_PATH + "Slide2.png");
        iconic3 = new ImageIcon(ASSETS_PATH + "Slide3.png");
        iconic4 = new ImageIcon(ASSETS_PATH + "Slide4.png");
        iconic5 = new ImageIcon(ASSETS_PATH + "Slide5.png");
        iconic6 = new ImageIcon(ASSETS_PATH + "Slide6.png");
        iconic7 = new ImageIcon(ASSETS_PATH + "Slide7.png");
        iconic8 = new ImageIcon(ASSETS_PATH + "Slide8.png");
        iconic9 = new ImageIcon(ASSETS_PATH + "Slide9.png");
        iconic10 = new ImageIcon(ASSETS_PATH + "Slide10.png");
        iconic11 = new ImageIcon(ASSETS_PATH + "Slide11.png");
        iconic12 = new ImageIcon(ASSETS_PATH + "Slide12.png");

        counter = 0;
        bob = new ImageIcon[12];
        set();
        label.setIcon(bob[counter]);
        label.setText(" ");

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

    private void previousButton()
    {
        if (counter > 0)
        {
            counter --;
            label.setIcon(bob[counter]);
        }
    }

    private void afterButton()
    {
        if (counter < bob.length - 1)
        {
            counter ++;
            label.setIcon(bob[counter]);
        }

    }

    private void onCancel()
    {
        dispose();
    }

    private void set(){
        bob[0] = iconic1;
        bob[1] = iconic2;
        bob[2] = iconic3;
        bob[3] = iconic4;
        bob[4] = iconic5;
        bob[5] = iconic6;
        bob[6] = iconic7;
        bob[7] = iconic8;
        bob[8] = iconic9;
        bob[9] = iconic10;
        bob[10] = iconic11;
        bob[11] = iconic12;
    }
}
