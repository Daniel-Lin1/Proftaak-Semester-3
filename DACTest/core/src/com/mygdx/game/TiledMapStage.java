package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapStage extends Stage {
    private TiledMap tiledMap;

    public TiledMapStage(TiledMap tiledMap) {
        this.tiledMap = tiledMap;

        for (MapLayer layer : tiledMap.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            createActorsForLayer(tiledLayer);
        }
    }

    private void createActorsForLayer(TiledMapTileLayer tiledLayer) {
        for (int x = 0; x < tiledLayer.getWidth(); x++) {
            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell);
                actor.setBounds(x * tiledLayer.getTileWidth(), y * tiledLayer.getTileHeight(), 32, 32);
                addActor(actor);
                EventListener eventListener = new TiledMapClickListener(actor, this);
                actor.addListener(eventListener);
            }
        }
    }

    public void createUnit(Actor actor) {
        Texture texture = new Texture(Gdx.files.internal("assets/Knight.png"));
        Image unit = new Image();
        unit.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
        unit.setSize(texture.getWidth(), texture.getHeight());
        unit.setPosition(actor.getX(), actor.getY());
        addActor(unit);
    }
}
