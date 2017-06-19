package game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import game.GameManager;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapStage extends Stage {
    private TiledMap tiledMap;
    private Group background = new Group();
    private Group foreground = new Group();
    private GameManager gameManager;

    Logger logger = Logger.getLogger(TiledMapStage.class.getName());

    public TiledMapStage(TiledMap tiledMap, GameManager gameManager) {
        this.tiledMap = tiledMap;
        this.gameManager = gameManager;

        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        foreground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addActor(background);
        addActor(foreground);


        try{
            createActorsForLayer((TiledMapTileLayer)tiledMap.getLayers().get(0));
        }catch (ClassCastException ex){
            logger.log(Level.SEVERE, "dit word veroorzaakt doordat de map niet goed is opgebouwed. zorg dat layer 1 in het .tmx bestand een TiledMapTileLayer is. GR marc-a", ex);
        }
    }

    private void createActorsForLayer(TiledMapTileLayer tiledLayer)  {
        for (int x = 0; x <= tiledLayer.getWidth(); x++) {
            for (int y = 0; y <= tiledLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell);
                actor.setBounds(x * tiledLayer.getTileWidth(), y * tiledLayer.getTileHeight(), tiledLayer.getTileWidth(), tiledLayer.getTileHeight());
                addActor(actor);
                EventListener eventListener = new TiledMapClickListener(actor, gameManager);
                actor.addListener(eventListener);
            }
        }
    }
}
