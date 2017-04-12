package Game;

import Building.Building;
import Units.Unit;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.DistressAndConflict;

import java.awt.*;

/**
 * Created by Yannick on 10-4-2017.
 */
public class UIManager {

    private DistressAndConflict dac;
    //Skin en spritebatch voor UI inladen
    private Skin UISkin;
    private SpriteBatch UIBatch;
    private GameManager manager;
    private BitmapFont font;

    private Point SelectedObjectInfoLocation = new Point(400, 170);
    private Point SelectedObjectImgLocation = new Point(50,50);

    private Texture Knight1;
    public UIManager(DistressAndConflict dac, GameManager manager){
        this.dac = dac;
        this.manager = manager;
    }

    public void create(){
        //UI inladen van bestanden
        UISkin = new Skin(Gdx.files.internal("assets/UI/medieval.json"));
        UISkin.addRegions(new TextureAtlas(Gdx.files.internal("assets/UI/medieval.atlas")));
        UIBatch = new SpriteBatch();
        font = new BitmapFont();

        Knight1 = new Texture(Gdx.files.internal("assets/Knight.png"));

    }

    public void Render()
    {
        UIBatch.begin();
        UIBatch.draw(UISkin.getSprite("buttonlong_brown"),350,0,1570, 200);
        UIBatch.draw(UISkin.getSprite("buttonSquare_brown"),0,0,350,250);

        //UIBatch.draw( Knight1, SelectedObjectImgLocation.x, SelectedObjectImgLocation.y, 150, 150);

        for(Unit unit : manager.getUnits())
        {
            if(unit.getSelected() == true)
            {
                font.draw(UIBatch, unit.GetUIInfo(), SelectedObjectInfoLocation.x, SelectedObjectInfoLocation.y);
                UIBatch.draw(unit.getSprite(), SelectedObjectImgLocation.x, SelectedObjectImgLocation.y, 180, 180);
            }
        }
        for(Building building : manager.getBuildings())
        {
            if(building.getSelected() == true)
            {
                font.draw(UIBatch, building.GetUIInfo(), SelectedObjectInfoLocation.x, SelectedObjectInfoLocation.y);
                UIBatch.draw(building.getSprite(), SelectedObjectImgLocation.x, SelectedObjectImgLocation.y, 180, 180);
            }
        }
        UIBatch.end();
    }
}
