/**
package com.sps.game.dialogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sps.game.Controller.DialogueController;
import com.sps.game.Extra.DialogueBox;
import com.sps.game.Extra.OptionBox;
import com.badlogic.gdx.InputMultiplexer;
import javafx.stage.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import java.util.List;

public class Conversation
{
/*
    private Dialogue dialogue;

    private Stage uiStage;

    private DialogueBox dialogueBox;
    private OptionBox optionBox;

    private int uiScale = 2;

    DialogueController dialogueController;

    private InputMultiplexer multiplexer;

    private Table root;

    public Conversation()
    {
        dialogue = new Dialogue();
        dialogueController = new DialogueController(dialogueBox,optionBox);
        multiplexer = new InputMultiplexer();
        root = new Table();

        multiplexer.addProcessor(0,dialogueController);
        //helper();
    }

    private void helper()
    {
        DialogueNode node1 = new LinearDialogueNode("hello", 0) ;
        DialogueNode node2 = new ChoiceDialogueNode("bye", 1);
        DialogueNode node3 = new ChoiceDialogueNode("hello", 2) ;
        DialogueNode node4 = new LinearDialogueNode("bye", 3);

        node1.makeLinear(node2.getID());
        node2.addChoice("boy",2);
        node2.addChoice("girl",3);
        //node4.


        dialogue.addNode(node1);
        dialogue.addNode(node2);
        dialogue.addNode(node3);
        dialogue.addNode(node4);

        dialogueController.startDialogue(dialogue);
    }

    private void random()
    {
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewPort().update(Gdx.graphics.getWidth()/uiScale, Gdx.graphics.getHeight()/uiScale, true);

        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);

        dialogueBox = new DialogueBox(getApp().getSkin());
        dialogueBox.setVisible(false);

        Table dialogueTable = new Table();

        dialogueTable.add(optionBox)
                .expand()
                .align(Align.right)
                .space(8f)
                .row();

        dialogueTable.add(dialogueBox)
                .expand()
                .align(Align.bottom)
                .space(8f)
                .row();

        root.add(dialogueTable).expand().align(Align.bottom);
    }*/
}
*/