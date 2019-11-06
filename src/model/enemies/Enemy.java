package model.enemies;

import engine.Cmd;
import model.Labyrinthe;

import static util.Constants.*;


public abstract class Enemy {
    private int x;
    private int y;
    private EnemyMovementStrategy movementStrategy;
    private Cmd currentCmd;
    private Labyrinthe labyrinthe;

    protected Enemy(int x, int y, Labyrinthe labyrinthe) {
        this.x = x;
        this.y = y;
        this.labyrinthe = labyrinthe;
        currentCmd = Cmd.DOWN;
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

    public void goUp(){
        if(y>0 && labyrinthe.isFree( x, y-1)) {
            this.setY(--y);
        }
        this.currentCmd = Cmd.UP;
    }

    public void goDown(){
        if(y < HEIGHT && labyrinthe.isFree( x, y+1)) {
            this.setY(++y);
        }
        this.currentCmd = Cmd.DOWN;
    }

    public void goLeft(){
        if(x>0 && labyrinthe.isFree( x-1, y)) {
            this.setX(--x);
        }
        this.currentCmd = Cmd.LEFT;
    }

    public void goRight(){
        if(x< WIDTH && labyrinthe.isFree( x+1, y)){
            this.setX(++x);
        }
        this.currentCmd = Cmd.RIGHT;
    }

    public void move(){
        movementStrategy.move(this);
    }

    public void setMovementStrategy(EnemyMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public Cmd getCurrentCmd() {
        return currentCmd;
    }
}
