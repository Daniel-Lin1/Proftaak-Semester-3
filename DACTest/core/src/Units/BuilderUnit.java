package Units;
import Enums.BuildingType;
import Building.Building;
import Enums.ResourceEnum;

/**
 * Created by Daniel on 26-3-2017.
 */
public class BuilderUnit extends Unit {
    private int amountResource;
    private ResourceEnum resourceEnum;

    public void getResource(){

    }

    public Building build(BuildingType buildingtype){
        return null;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
