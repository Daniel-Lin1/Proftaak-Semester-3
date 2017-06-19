package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by Marc-Antoine on 4/10/2017.
 */
public class OrthographicCameraControlClass {
    private int SCROLL_SPEED = 10;
    private TiledMap tiledMap;

    public OrthographicCameraControlClass(int SCROLL_SPEED, TiledMap tiledMap) {
        this.SCROLL_SPEED = SCROLL_SPEED;
        this.tiledMap = tiledMap;
    }

    public OrthographicCamera render(OrthographicCamera orthographicCamera){
        //TODO Might use a switch here?
        //map scroll
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)){
            if (orthographicCamera.position.x <= orthographicCamera.viewportWidth/2 * orthographicCamera.zoom){
            }else{
                orthographicCamera.translate(-SCROLL_SPEED * Gdx.graphics.getDeltaTime(), 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)){
            if (orthographicCamera.position.x >= tiledMap.getLayers().get(0).getProperties().get("width", Integer.class) - (orthographicCamera.viewportWidth/2* orthographicCamera.zoom + 1450)){
            }else {
                orthographicCamera.translate(SCROLL_SPEED * Gdx.graphics.getDeltaTime(), 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
            if (orthographicCamera.position.y >= tiledMap.getLayers().get(0).getProperties().get("height", Integer.class) - (orthographicCamera.viewportHeight/2 * orthographicCamera.zoom)){
            }else {
                orthographicCamera.translate(0, SCROLL_SPEED * Gdx.graphics.getDeltaTime());
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)|| Gdx.input.isKeyPressed(Input.Keys.S)){
            if (orthographicCamera.position.y <= orthographicCamera.viewportHeight/2* orthographicCamera.zoom - 250){

            }else{
                orthographicCamera.translate(0, - SCROLL_SPEED * Gdx.graphics.getDeltaTime());
            }
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.X)){
            if (orthographicCamera.zoom < 1)
            {
                orthographicCamera.zoom += 0.01;
            }
        }
        if ( Gdx.input.isKeyPressed(Input.Keys.Z)){
            if (orthographicCamera.zoom > 0.1)
            {
                orthographicCamera.zoom -= 0.01;
            }
        }
        return orthographicCamera;
    }
}
