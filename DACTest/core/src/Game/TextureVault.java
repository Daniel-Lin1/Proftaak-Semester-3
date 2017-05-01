package Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Yannick on 12-4-2017.
 */
public class TextureVault {

    public Texture knight;
    public Texture swordMan;
    public Texture pikeMan;
    public Texture slinger;
    public Texture archer;
    public Texture builder;
    public Texture horseArcher;
    public Texture attack;

    public Texture stable;
    public Texture townCenter;

    public Texture gatherResource;

    public TextureVault(){
        knight = new Texture(Gdx.files.internal("assets/Knight.png"));
        attack = new Texture(Gdx.files.internal("assets/Attack.png"));
        gatherResource = new Texture(Gdx.files.internal("assets/GatherResources.png"));
        stable = new Texture(Gdx.files.internal("assets/Stable.png"));
        townCenter = new Texture(Gdx.files.internal("assets/Towncenter.png"));

    }
}
