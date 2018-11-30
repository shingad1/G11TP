package com.sps.game.dialogue;

import javax.swing.*;
import java.awt.event.*;

public class Dialogue extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Dialogue() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

<<<<<<< HEAD
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
=======
    private static final String ASSETS_PATH = "core/assets/textureAtlas/npcAtlas/";


    public Dialogue() {
        skin = new Skin();


    }

    public void showDialog() {
        Dialog dialog = new Dialog("Warning", skin, "dialog");

        dialog.text("Are you sure you want to quit?");
        dialog.button("Yes", true); //sends "true" as the result
        dialog.button("No", false);  //sends "false" as the result
        dialog.key(Input.Keys.ENTER, true); //sends "true" when the ENTER key is pressed
        dialog.show(stage);
    }

    public void result(Object obj)
    {
        System.out.println("result " + obj);
    }


    public void create() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
>>>>>>> 13aa7a82e355b6b79e9a4c5f76686a21655dd9db

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
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

<<<<<<< HEAD
    private void onOK() {
        // add your code here
        dispose();
=======
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
>>>>>>> 13aa7a82e355b6b79e9a4c5f76686a21655dd9db
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

<<<<<<< HEAD
    public static void main(String[] args) {
        Dialogue dialog = new Dialogue();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
=======
    public void dispose() {

        stage.dispose();
>>>>>>> 13aa7a82e355b6b79e9a4c5f76686a21655dd9db
    }
}
