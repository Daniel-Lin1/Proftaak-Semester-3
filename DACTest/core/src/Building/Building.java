package Building;

import Enums.BuildingType;
import Enums.UnitType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import javax.xml.soap.Text;
import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Building {
    private Point coordinate;
    private int sizeX;
    private int sizeY;
    private BuildingType buildingtype;
    private int health;
    private Texture sprite;

    public void searchSprite()
    {
        if (buildingtype == BuildingType.Stable)
        {
            sprite = new Texture(Gdx.files.internal("assets/Stable.png"));
        }
        else
        {
            sprite = null;
        }
    }
}
