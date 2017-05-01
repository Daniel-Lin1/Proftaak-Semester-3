package Game.UserInterface;

import Building.Building;
import Units.Unit;
import Game.GameManager;
import Game.TextureVault;
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
    private BitmapFont abilityFont;
    private TextureVault textureVault;

    private int amountOfAbilities;

    private Point SelectedObjectInfoLocation = new Point(200, 200);
    private Point SelectedObjectImgLocation = new Point(50,50);

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
        textureVault = new TextureVault();

    }

    public void Render()
    {
        amountOfAbilities = 0;
        UIBatch.begin();
        UIBatch.draw(UISkin.getSprite("buttonlong_brown"),350,0,1570, 200);
        UIBatch.draw(UISkin.getSprite("buttonSquare_brown"),0,0,350,250);


        for(Unit unit : manager.getUnits())
        {
            if(unit.getSelected() == true)
            {
                UIBatch.draw(unit.getSprite(), SelectedObjectImgLocation.x, SelectedObjectImgLocation.y, 150, 160);
                font.draw(UIBatch, unit.GetUIInfo(), SelectedObjectInfoLocation.x, SelectedObjectInfoLocation.y);

                switch(unit.getUnitType())
                {
                    case Knight:
                    case Archer:
                    case HorseArcher:
                    case PikeMan:
                    case Slinger:
                    case SwordMan:
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
                font.draw(UIBatch, building.GetUIInfo(), SelectedObjectInfoLocation.x, SelectedObjectInfoLocation.y);
                UIBatch.draw(building.getSprite(), SelectedObjectImgLocation.x, SelectedObjectImgLocation.y, 150, 150);
            }
        }

        drawAbilitiesKeys();
        UIBatch.end();

    }

    private void renderAttacker(){
        amountOfAbilities = 1;
        UIBatch.draw(textureVault.attack, 400, 40, 140, 140);
    }

    private void renderBuilder(){
        amountOfAbilities = 3;
        UIBatch.draw(textureVault.townCenter, 400, 40, 140, 140);
        UIBatch.draw(textureVault.gatherResource, 600, 40, 140, 140);
        UIBatch.draw(textureVault.stable, 800, 40, 140, 140);
    }

    private void drawAbilitiesKeys(){
        if (amountOfAbilities >= 1){
            abilityFont.draw(UIBatch, "Q", 400, 80);
            if (amountOfAbilities >= 2){
                abilityFont.draw(UIBatch, "W", 600, 80);
                if (amountOfAbilities >= 3){
                    abilityFont.draw(UIBatch, "E", 800, 80);
                    if (amountOfAbilities >= 4){
                        abilityFont.draw(UIBatch, "R", 1000, 80);
                    }
                }
            }
        }

    }
}
