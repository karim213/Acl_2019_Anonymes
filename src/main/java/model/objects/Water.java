package model.objects;

import model.Hero;
import model.Position;

public class Water extends AbstractObject {

    private boolean isOnAction;

    public Water(Position position) {
        super(position);
        isOnAction = false;
    }

    @Override
    public void action(Hero hero) {
        if (!isOnAction) {
            hero.slow();
            isOnAction = true;
        }
    }

    public boolean isOnAction() {
        return isOnAction;
    }

    public void removeAction(Hero hero) {
        if (isOnAction) {
            hero.normalSpeed();
            isOnAction = false;
        }
    }
}
