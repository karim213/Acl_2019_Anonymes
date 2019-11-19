package model.walls;

import model.Position;

public class Wall {
    Position position;

    public Wall(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }
}
