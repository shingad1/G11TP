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
    private int counter;

    private Texture slide1;
    private Texture slide2;
    private Texture slide3;
    private Texture slide4;
    private Texture slide5;
    private Texture slide6;
    private Texture slide7;
    private Texture slide8;
    private Texture slide9;
    private Texture slide10;
    private Texture slide11;
    private Texture slide12;

    private static final String ASSETS_PATH = "core/assets/SP/";

    private Texture[] bob;

    private SpriteBatch batch;

    public TutorialController1() throws IOException {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonPrevious);

        slide1 = new Texture(ASSETS_PATH + "Slide1.png");
        slide2 = new Texture(ASSETS_PATH + "Slide2.png");
        slide3 = new Texture(ASSETS_PATH + "Slide3.png");
        slide4 = new Texture(ASSETS_PATH + "Slide4.png");
        slide5 = new Texture(ASSETS_PATH + "Slide5.png");
        slide6 = new Texture(ASSETS_PATH + "Slide6.png");
        slide7 = new Texture(ASSETS_PATH + "Slide7.png");
        slide8 = new Texture(ASSETS_PATH + "Slide8.png");
        slide9 = new Texture(ASSETS_PATH + "Slide9.png");
        slide10 = new Texture(ASSETS_PATH + "Slide10.png");
        slide11 = new Texture(ASSETS_PATH + "Slide11.png");
        slide12 = new Texture(ASSETS_PATH + "Slide11.png");

        ImageIcon iconic = new ImageIcon(ASSETS_PATH + "Slide1.png");

        batch = new SpriteBatch();

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
        }
    }

    private void afterButton()
    {
        //if (counter < dialogue.length - 1)
        {
            counter ++;
            //dialoguesTextArea.setText(dialogue[counter]);
        }

    }

    private void onCancel()
    {
        dispose();
    }

    /*JFrame f = new JFrame("stackoverflow") {
        private Image backgroundImage = ImageIO.read(new File("background.jpg"));
        public void paint( Graphics g ) {
            super.paint(g);
            g.drawImage(backgroundImage, 0, 0, null);
        }
    };*/

    public void render() {
        batch.begin();
        batch.draw(slide1, 0,0);
        batch.draw(slide2, 0,0);
        batch.draw(slide3, 0,0);
        batch.draw(slide4, 0,0);
        batch.draw(slide5, 0,0);
        batch.draw(slide6, 0,0);
        batch.draw(slide7, 0,0);
        batch.draw(slide8, 0,0);
        batch.draw(slide9, 0,0);
        batch.draw(slide10, 0,0);
        batch.draw(slide11, 0,0);
        batch.draw(slide12, 0,0);
        batch.end();
    }
}
