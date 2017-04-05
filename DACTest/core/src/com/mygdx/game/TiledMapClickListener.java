package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapClickListener extends ClickListener {
    private TiledMapActor actor;
    private TiledMapStage stage;

    public TiledMapClickListener(TiledMapActor actor, TiledMapStage stage) {
        this.actor = actor;
        this.stage = stage;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println(actor.cell + " has been clicked.");
        stage.createUnit(actor);
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        //System.out.println("Hovering over " + actor.cell);
    }
}
