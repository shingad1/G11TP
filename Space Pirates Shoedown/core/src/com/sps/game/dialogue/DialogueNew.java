package com.sps.game.dialogue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class DialogueNew extends ScreenAdapter {
    private Stage stage;
    private Skin skin;

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage = new Stage());
        skin = new Skin(Gdx.files.internal(Assets.uiskin));

        new Dialog("confirm exit", skin) {
            {
                text("really exit");
                button("yes", "goodbye");
                button("no", "glad you ");
            }
            @Override
            protected void result(final Object object) {
                new Dialog("", skin) {
                    {
                    text(object.toString());

                    button("Ok");
                }
            }.show(stage);
        }
    }.show(stage);
}
    @Override
    public void resize(int width, int height)
    {
        stage.setViewport(width, height);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
