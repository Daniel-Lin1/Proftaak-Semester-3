package game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Yannick on 12-4-2017.
 */
public class TextureVault {

    private TextureVault() {
        throw new IllegalStateException("Utility class");
    }

    public static final Texture selected = new Texture(Gdx.files.internal("assets/Selected.png"));
    public static final Texture townCenter = new Texture(Gdx.files.internal("assets/TOWN_CENTER.png"));
    public static final Texture knight = new Texture(Gdx.files.internal("assets/Knight1.png"));
    public static final Texture archer = new Texture(Gdx.files.internal("assets/Archer1.png"));
    public static final Texture pikeMan = new Texture(Gdx.files.internal("assets/PikeMan1.png"));
    public static final Texture Health100 = new Texture(Gdx.files.internal("assets/Healthbar100.png"));
    public static final Texture Health75 = new Texture(Gdx.files.internal("assets/Healthbar75.png"));
    public static final Texture Health50 = new Texture(Gdx.files.internal("assets/Healthbar50.png"));
    public static final Texture Health25 = new Texture(Gdx.files.internal("assets/Healthbar25.png"));
    public static final Texture uiElementAttack = new Texture(Gdx.files.internal("assets/uiElementAttack.png"));
    public static final Texture gatherResource = new Texture(Gdx.files.internal("assets/GatherResource.png"));
    public static final Texture tree = new Texture(Gdx.files.internal("assets/Tree.png"));
    public static final Texture gold = new Texture(Gdx.files.internal("assets/GOLD.png"));
    public static final Texture stone = new Texture(Gdx.files.internal("assets/STONE.png"));
    public static final Texture berries = new Texture(Gdx.files.internal("assets/Berries.png"));
    public static final Texture uiBar = new Texture(Gdx.files.internal("assets/UI/UIBar.png"));
    public static final Texture uiBox = new Texture(Gdx.files.internal("assets/UI/UIBox.png"));
    public static final Texture builder = new Texture(Gdx.files.internal("assets/BUILDER.png"));
}