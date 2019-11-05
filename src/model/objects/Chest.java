package model.objects;

import model.Position;

import java.awt.*;

public class Chest {
    private Position position;

    public Chest(Position position) {
        this.position = position;
    }

    public boolean isOnChest(int x, int y){
        Rectangle rect1 = new Rectangle(x*5, y*5, 20, 40);
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
}
