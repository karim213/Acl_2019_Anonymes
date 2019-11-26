package model.objects;

import model.Hero;
import model.Position;

public class Sand extends AbstractObject {

    private boolean isOnAction;

    public Sand(Position position) {
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
