package model.objects;

import model.Position;

public class Chest {
    private Position position;

    public Chest(Position position) {
        this.position = position;
    }

    public boolean isOnChest(int x, int y){
        return position.on(new Position(x, y));
    }

    public Position getPosition() {
        return position;
    }
}
