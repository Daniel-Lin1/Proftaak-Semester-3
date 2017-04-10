package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.prism.image.ViewPort;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapStage extends Stage {
    private DistressAndConflict dac;
    private TiledMap tiledMap;
    private Group background = new Group();
    private Group foreground = new Group();
    private OrthographicCamera ogc;



    public TiledMapStage(TiledMap tiledMap, DistressAndConflict dac) {
        this.dac = dac;
        this.tiledMap = tiledMap;
        this.ogc = dac.gameManager.getOrthographicCamera();

        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        foreground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addActor(background);
        addActor(foreground);

        for (MapLayer layer : tiledMap.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            createActorsForLayer(tiledLayer);
        }
    }

    private void createActorsForLayer(TiledMapTileLayer tiledLayer) {
        for (int x = 0; x <= tiledLayer.getWidth(); x++) {
            for (int y = 0; y <= tiledLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell);
                actor.setBounds(x * tiledLayer.getTileWidth(), y * tiledLayer.getTileHeight(), tiledLayer.getTileWidth(), tiledLayer.getTileHeight());
                addActor(actor);
                EventListener eventListener = new TiledMapClickListener(actor, this);
                actor.addListener(eventListener);
            }
        }
    }

    public void createUnit(Actor actor) {
//        Texture texture = new Texture(Gdx.files.internal("assets/Knight.png"));
//        Image unit = new Image();
//        unit.setDrawable(new TextureRegionDrawable(new TextureRegion(texture)));
//        unit.setSize(texture.getWidth(), texture.getHeight());
//        unit.setPosition(actor.getX(), actor.getY());
//        foreground.addActor(unit);
        dac.gameManager.addUnit((int)actor.getX(), (int)actor.getY());
    }
}
