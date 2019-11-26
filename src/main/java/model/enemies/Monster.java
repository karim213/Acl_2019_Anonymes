package model.enemies;

import model.Labyrinthe;

public class Monster extends Enemy {


    public Monster(int x, int y , Labyrinthe labyrinthe) {
        super(x, y, 1 , labyrinthe);
    }

    @Override
    public String getType() {
        return "Monster";
    }

}
