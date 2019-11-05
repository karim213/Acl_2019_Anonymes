package model.enemies;

import model.Labyrinthe;

public class Monster extends Enemy {


    public Monster(int x, int y , Labyrinthe labyrinthe) {
        this.labyrinthe =labyrinthe;
        this.x = x;
        this.y = y;
        this.nbMvts = 0;
    }



}
