package Game.UserInterface;

import Building.Building;
import Units.Unit;
import Game.GameManager;
import Game.TextureVault;
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
    private BitmapFont abilityFont;

    private int amountOfAbilities;

    private Point SelectedObjectInfoLocation = new Point(160, 200);
    private Point SelectedObjectImgLocation = new Point(50,120);

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
        abilityFont = new BitmapFont();
        abilityFont.getData().setScale(3, 3);

    }

    public void render()
    {
        amountOfAbilities = 0;
        UIBatch.begin();
        UIBatch.draw(UISkin.getSprite("buttonlong_brown"),350,0,1570, 200);
        UIBatch.draw(UISkin.getSprite("buttonSquare_brown"),0,0,350,250);


        for(Unit unit : manager.getUnits())
        {
            if(unit.getSelected() == true)
            {
                UIBatch.draw(unit.getSprite(), SelectedObjectImgLocation.x, SelectedObjectImgLocation.y, 100, 100);
                font.draw(UIBatch, unit.getUIInfo(), SelectedObjectInfoLocation.x, SelectedObjectInfoLocation.y);

                switch(unit.getUnitType())
                {
                    case Knight:
                        renderAttacker();
                        break;
                    case Archer:
                        renderAttacker();
                        break;
                    case PikeMan:
                        renderAttacker();
                        break;
                    case Builder:
                        renderBuilder();
                        break;
                }
            }
        }
        for(Building building : manager.getBuildings())
        {
            if(building.getSelected() == true)
            {
                font.draw(UIBatch, building.getUIInfo(), SelectedObjectInfoLocation.x, SelectedObjectInfoLocation.y);
                UIBatch.draw(building.getSprite(), SelectedObjectImgLocation.x, SelectedObjectImgLocation.y, 100, 100);
                renderTownCenter();
            }
        }
        drawAbilitiesKeys();
        UIBatch.end();

    }

    private void renderTownCenter()
    {
        amountOfAbilities = 3;
        UIBatch.draw(TextureVault.knight, 400, 40, 140, 140);
        UIBatch.draw(TextureVault.pikeMan, 600, 40, 140, 140);
        UIBatch.draw(TextureVault.archer, 800, 40, 140, 140);
    }

    private void renderAttacker(){
        amountOfAbilities = 1;
        UIBatch.draw(TextureVault.uiElementAttack, 400, 40, 140, 140);
    }

    private void renderBuilder(){
        amountOfAbilities = 3;
        UIBatch.draw(TextureVault.townCenter, 400, 40, 140, 140);
        UIBatch.draw(TextureVault.gatherResource, 600, 40, 140, 140);
    }

    private void drawAbilitiesKeys(){
        for (int i = 0; i < amountOfAbilities; i++)
        {
            abilityFont.draw(UIBatch, Integer.toString(i + 1), 400 + (i *200), 80);
        }
    }
}
