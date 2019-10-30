package model.walls;

import model.Position;

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
        for (Wall w : walls) {
            if (!w.isPosFree(x, y)){
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

}
