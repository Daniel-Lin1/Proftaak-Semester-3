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

    public OrthographicCamera Render(OrthographicCamera orthographicCamera){
        //Map scroll
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if (orthographicCamera.position.x <= orthographicCamera.viewportWidth/2){
            }else{
                orthographicCamera.translate(-SCROLL_SPEED, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if (orthographicCamera.position.x >= tiledMap.getLayers().get(0).getProperties().get("width", Integer.class) - (orthographicCamera.viewportWidth/2)){
            }else {
                orthographicCamera.translate(SCROLL_SPEED, 0);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            if (orthographicCamera.position.y >= tiledMap.getLayers().get(0).getProperties().get("height", Integer.class) - (orthographicCamera.viewportHeight/2-200)){
            }else {
                orthographicCamera.translate(0, SCROLL_SPEED);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if (orthographicCamera.position.y <= orthographicCamera.viewportHeight/2-200){

            }else{
                orthographicCamera.translate(0, -SCROLL_SPEED);
            }
        }
        return orthographicCamera;
    }
}
