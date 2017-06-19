package game.userinterface;

import building.Building;
import game.GameManager;
import game.TextureVault;
import units.Unit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.*;

/**
 * Created by Yannick on 10-4-2017.
 */
public class UIManager {

    private SpriteBatch spriteBatch;
    private GameManager gameManager;
    private BitmapFont font;
    private BitmapFont abilityFont;

    private int amountOfAbilities;

    private Point selectedObjectInfoLocation = new Point(160, 200);
    private Point selectedObjectImgLocation = new Point(50,120);

    public UIManager(GameManager manager){
        this.gameManager = manager;
    }

    public void create(){
        //UI inladen van bestanden
        Skin uiskin = new Skin(Gdx.files.internal("assets/UI/medieval.json"));
        uiskin.addRegions(new TextureAtlas(Gdx.files.internal("assets/UI/medieval.atlas")));
        spriteBatch = new SpriteBatch();
        font = new BitmapFont();
        abilityFont = new BitmapFont();
        abilityFont.getData().setScale(3, 3);
    }

    public void render()
    {
        Boolean uiRendered = false;
        amountOfAbilities = 0;
        spriteBatch.begin();
        spriteBatch.draw(TextureVault.uiBar,350, 0 ,1570, 200);
        spriteBatch.draw(TextureVault.uiBox,0,0,350,250);
        drawResources();
        for(Unit unit : gameManager.getOwnPlayer().getSelectedUnits())
        {
            if (!uiRendered)
            {
                uiRendered = true;
                spriteBatch.draw(unit.getSprite(), selectedObjectImgLocation.x, selectedObjectImgLocation.y, 100, 100);
                font.draw(spriteBatch, unit.getUIInfo(), selectedObjectInfoLocation.x, selectedObjectInfoLocation.y);

                switch(unit.getUnitType())
                {
                    case KNIGHT:
                        renderAttacker();
                        break;
                    case ARCHER:
                        renderAttacker();
                        break;
                    case PIKE_MAN:
                        renderAttacker();
                        break;
                    case BUILDER:
                        renderBuilder();
                        break;
                    default:
                            //do nothing
                }
            }
        }
        Building building = gameManager.getOwnPlayer().getSelectedBuilding();
        if(building != null && !uiRendered){
            font.draw(spriteBatch, building.getUIInfo(), selectedObjectInfoLocation.x, selectedObjectInfoLocation.y);
            spriteBatch.draw(building.getSprite(), selectedObjectImgLocation.x, selectedObjectImgLocation.y, 100, 100);
            renderTownCenter();
        }
        drawAbilitiesKeys();
        spriteBatch.end();
    }

    private void renderTownCenter()
    {
        amountOfAbilities = 4;
        spriteBatch.draw(TextureVault.knight, 400, 40, 140, 140);
        spriteBatch.draw(TextureVault.pikeMan, 600, 40, 140, 140);
        spriteBatch.draw(TextureVault.archer, 800, 40, 140, 140);
        spriteBatch.draw(TextureVault.builder, 1000, 40, 140, 140);
    }

    private void renderAttacker(){
        amountOfAbilities = 1;
        spriteBatch.draw(TextureVault.uiElementAttack, 400, 40, 140, 140);
    }

    private void renderBuilder(){
        amountOfAbilities = 1;
        spriteBatch.draw(TextureVault.gatherResource, 400, 40, 140, 140);
    }

    private void drawAbilitiesKeys(){
        for (int i = 0; i < amountOfAbilities; i++)
        {
            abilityFont.draw(spriteBatch, Integer.toString(i + 1), 400 + (i *200), 80);
        }
    }
    public void drawResources(){
        font.draw(spriteBatch, "Goud : " + gameManager.getOwnPlayer().getAmountGold() +"\nWOOD : " + gameManager.getOwnPlayer().getAmountWood() + "\nFOOD : " + gameManager.getOwnPlayer().getAmountFood() + "\nSTONE : " + gameManager.getOwnPlayer().getAmountStone(), 10, 1070);
    }
}
