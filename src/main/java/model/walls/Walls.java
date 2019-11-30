package model.walls;

import model.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Walls {
    private List<Wall> walls;

    public Walls() {
        walls = new ArrayList<>();
    }

    public void addWall(int x, int y) {
        walls.add(new Wall(x, y));
    }

    public boolean isPosFree(int x, int y) {

        Rectangle rect1 = new Rectangle(x*5, y*5, 20, 40);

        for (Wall w : walls) {
            Rectangle rect2 = new Rectangle(w.getPosition().getX() *5 , (w.getPosition().getY())*5, 20, 20);
            Rectangle intersection = rect1.intersection(rect2);

            if (intersection.getWidth() > 0 && intersection.getHeight() > 0){
                return false;
            }
        }
        return true;
    }

    public List<Position> getWallsPosition(){
        List<Position> res = new ArrayList<>();

        for (Wall w : walls){
            res.add(w.getPosition());
        }

        return res;
    }

    public void emptyWalls(){
        walls.clear();
    }

}
