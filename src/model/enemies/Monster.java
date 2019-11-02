package model.enemies;

public class Monster extends Enemy {
    private int x;
    private int y;

    public void action() {

    }

    public Monster(int x, int y) {
        this.x = x;
        this.y = y;
        this.nbMvts = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



}
