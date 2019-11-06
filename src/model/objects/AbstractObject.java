package model.objects;

import model.Hero;
import model.Position;

import java.awt.*;

public abstract class AbstractObject {

    private Position position;

    public AbstractObject(Position position) {
        this.position = position;
    }

    public boolean isOn(Position pos){
        Rectangle rect1 = new Rectangle(pos.getX()*5, pos.getY()*5, 20, 40);
        Rectangle rect2 = new Rectangle(position.getX()*5 , position.getY()*5, 20, 10);
        Rectangle intersection = rect1.intersection(rect2);

        if(intersection.getHeight() > 0 && intersection.getWidth() > 0) {
            return true;
        }
        return false;
    }

    public Position getPosition() {
        return position;
    }

    public abstract void action(Hero hero);
}
