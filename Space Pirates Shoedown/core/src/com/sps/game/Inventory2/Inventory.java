package com.sps.game.Inventory2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.sps.game.Utility;

public class Inventory extends ScreenAdapter {

    private Stage stage = new Stage();
    public String UISKIN_PATH = "core/assets/Inventory/uiskin.json";

    public Inventory() {
        final Skin skin = new Skin();
        skin.add("uiskin", UISKIN_PATH);
        stage.setDebugAll(true);

        final List<String> inventory = new List<String>(skin);
        final List<String> sell = new List<String>(skin);

        inventory.setItems("Axe", "Fuel", "Helmet", "Flux Capacitor", "Shoes", "Hamster", "Hammer", "Pirates Eye", "Cucumber");
        Table table = new Table(skin);
        table.setFillParent(true);
        stage.addActor(table);

        table.defaults();
        table.add(inventory);
        table.add("Merchant").row();
        table.add(inventory).expand().fill();
        table.add(sell).expand().fill();

        DragAndDrop dnd = new DragAndDrop();
        dnd.addSource(new Source(inventory) {
            final DragAndDrop.Payload payload = new Payload();

            @Override
            public Payload dragStart(InputEvent event, float x, float y, int pointer) {
                String item = inventory.getSelected();
                payload.setObject(item);
                inventory.getItems().removeIndex(inventory.getSelectedIndex());
                payload.setDragActor(new Label(item, skin));
                payload.setInvalidDragActor(new Label(item + " (\"No thanks!\")", skin));
                payload.setValidDragActor(new Label(item + " (\"I'll buy this!\")", skin));
                return payload;
            }

            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
                if (target == null)
                    inventory.getItems().add((String) payload.getObject());
            }
        });

        dnd.addTarget(new Target(sell) {
            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                return !"Cucumber".equals(payload.getObject());
            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                sell.getItems().add((String) payload.getObject());
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

}
