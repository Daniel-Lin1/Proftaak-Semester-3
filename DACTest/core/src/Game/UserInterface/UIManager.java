package game.UserInterface;

import building.Building;
import game.GameManager;
import game.TextureVault;
import Units.Unit;
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

    private SpriteBatch UIBatch;
    private GameManager gameManager;
    private BitmapFont font;
    private BitmapFont abilityFont;

    private int amountOfAbilities;

    private Point SelectedObjectInfoLocation = new Point(160, 200);
    private Point SelectedObjectImgLocation = new Point(50,120);

    public UIManager(GameManager manager){
        this.gameManager = manager;
    }

    public void create(){
        //UI inladen van bestanden
        Skin uiskin = new Skin(Gdx.files.internal("assets/UI/medieval.json"));
        uiskin.addRegions(new TextureAtlas(Gdx.files.internal("assets/UI/medieval.atlas")));
        UIBatch = new SpriteBatch();
        font = new BitmapFont();
        abilityFont = new BitmapFont();
        abilityFont.getData().setScale(3, 3);
    }

    public void render()
    {
        Boolean uiRendered = false;
        amountOfAbilities = 0;
        UIBatch.begin();
        UIBatch.draw(TextureVault.uiBar,350, 0 ,1570, 200);
        UIBatch.draw(TextureVault.uiBox,0,0,350,250);
        drawResources();
        for(Unit unit : gameManager.getOwnPlayer().getSelectedUnits())
        {
            if (uiRendered == false)
            {
                uiRendered = true;
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
        Building building = gameManager.getOwnPlayer().getSelectedBuilding();
        if(building != null && uiRendered == false){
            font.draw(UIBatch, building.getUIInfo(), SelectedObjectInfoLocation.x, SelectedObjectInfoLocation.y);
            UIBatch.draw(building.getSprite(), SelectedObjectImgLocation.x, SelectedObjectImgLocation.y, 100, 100);
            renderTownCenter();
        }
        drawAbilitiesKeys();
        UIBatch.end();
    }

    private void renderTownCenter()
    {
        amountOfAbilities = 4;
        UIBatch.draw(TextureVault.knight, 400, 40, 140, 140);
        UIBatch.draw(TextureVault.pikeMan, 600, 40, 140, 140);
        UIBatch.draw(TextureVault.archer, 800, 40, 140, 140);
        UIBatch.draw(TextureVault.builder, 1000, 40, 140, 140);
    }

    private void renderAttacker(){
        amountOfAbilities = 1;
        UIBatch.draw(TextureVault.uiElementAttack, 400, 40, 140, 140);
    }

    private void renderBuilder(){
        amountOfAbilities = 1;
        UIBatch.draw(TextureVault.gatherResource, 400, 40, 140, 140);
    }

    private void drawAbilitiesKeys(){
        for (int i = 0; i < amountOfAbilities; i++)
        {
            abilityFont.draw(UIBatch, Integer.toString(i + 1), 400 + (i *200), 80);
        }
    }
    public void drawResources(){
        font.draw(UIBatch, "Goud : " + gameManager.getOwnPlayer().getAmountGold() +"\nWood : " + gameManager.getOwnPlayer().getAmountWood() + "\nFood : " + gameManager.getOwnPlayer().getAmountFood() + "\nStone : " + gameManager.getOwnPlayer().getAmountStone(), 10, 1070);
    }
}
