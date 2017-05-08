package Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Yannick on 12-4-2017.
 */
public class TextureVault {

    private Texture knight;
    private Texture swordMan;
    private Texture pikeMan;
    private Texture slinger;
    private Texture archer;
    private Texture builder;
    private Texture horseArcher;
    private Texture uiElementAttack;
    private Texture stable;
    private Texture townCenter;
    private Texture gatherResource;

    public TextureVault(){
        this.setKnight(new Texture(Gdx.files.internal("assets/Knight.png")));
        this.setUiElementAttack(new Texture(Gdx.files.internal("assets/uiElementAttack.png")));
        this.setGatherResource(new Texture(Gdx.files.internal("assets/GatherResources.png")));
        this.setTownCenter(new Texture(Gdx.files.internal("assets/Towncenter.png")));
    }

    public Texture getKnight() {
        return knight;
    }

    public void setKnight(Texture knight) {
        this.knight = knight;
    }

    public Texture getSwordMan() {
        return swordMan;
    }

    public void setSwordMan(Texture swordMan) {
        this.swordMan = swordMan;
    }

    public Texture getPikeMan() {
        return pikeMan;
    }

    public void setPikeMan(Texture pikeMan) {
        this.pikeMan = pikeMan;
    }

    public Texture getSlinger() {
        return slinger;
    }

    public void setSlinger(Texture slinger) {
        this.slinger = slinger;
    }

    public Texture getArcher() {
        return archer;
    }

    public void setArcher(Texture archer) {
        this.archer = archer;
    }

    public Texture getBuilder() {
        return builder;
    }

    public void setBuilder(Texture builder) {
        this.builder = builder;
    }

    public Texture getHorseArcher() {
        return horseArcher;
    }

    public void setHorseArcher(Texture horseArcher) {
        this.horseArcher = horseArcher;
    }

    public Texture getUiElementAttack() {
        return uiElementAttack;
    }

    public void setUiElementAttack(Texture uiElementAttack) {
        this.uiElementAttack = uiElementAttack;
    }

    public Texture getStable() {
        return stable;
    }

    public void setStable(Texture stable) {
        this.stable = stable;
    }

    public Texture getTownCenter() {
        return townCenter;
    }

    public void setTownCenter(Texture townCenter) {
        this.townCenter = townCenter;
    }

    public Texture getGatherResource() {
        return gatherResource;
    }

    public void setGatherResource(Texture gatherResource) {
        this.gatherResource = gatherResource;
    }
}
