package Game.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapImageLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.DistressAndConflict;
import Game.GameManager;

import java.rmi.RemoteException;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapStage extends Stage {
    private DistressAndConflict dac;
    private TiledMap tiledMap;
    private Group background = new Group();
    private Group foreground = new Group();
    private GameManager gameManager;

    public TiledMapStage(TiledMap tiledMap, GameManager gameManager) throws RemoteException {
        this.tiledMap = tiledMap;
        this.gameManager = gameManager;

        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        foreground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        addActor(background);
        addActor(foreground);

        for (MapLayer layer : tiledMap.getLayers()) {
            try{
                TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
                createActorsForLayer(tiledLayer);
            }catch (Exception ex){
                System.out.println(ex.toString() + "  !!! fix dit nog ff. dit staat in de tiledmapstage constructor :)  !!!");
                TiledMapImageLayer tiledLayer = (TiledMapImageLayer)layer;
            }

        }
    }

    private void createActorsForLayer(TiledMapTileLayer tiledLayer) throws RemoteException {
        for (int x = 0; x <= tiledLayer.getWidth(); x++) {
            for (int y = 0; y <= tiledLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell);
                actor.setBounds(x * tiledLayer.getTileWidth(), y * tiledLayer.getTileHeight(), tiledLayer.getTileWidth(), tiledLayer.getTileHeight());
                addActor(actor);
                EventListener eventListener = new TiledMapClickListener(actor, this, gameManager);
                actor.addListener(eventListener);
            }
        }
    }
}
