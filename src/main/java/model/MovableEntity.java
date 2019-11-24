package model;

import engine.Cmd;

public abstract class MovableEntity {
    protected int x;
    protected int y;
    protected Cmd currentCmd;
    private int pv;
    private boolean isDead;
    private int speed;

    public MovableEntity(int x, int y, int pv, int speed) {
        this.x = x;
        this.y = y;
        currentCmd = Cmd.DOWN;
        this.pv = pv;
        this.isDead = false;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isDead() {
        return isDead;
    }

    public void receiveHeal(){
        this.pv++;
    }

    public void receiveDamage(){
        this.pv--;
        if (this.pv == 0)
            isDead = true;
    }

    public int getPv(){
        return this.pv;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void goUp(){
        this.setY(y - speed);
        this.currentCmd = Cmd.UP;
    }

    public void goDown(){
        this.setY(y + speed);
        this.currentCmd = Cmd.DOWN;
    }

    public void goLeft(){
        this.setX(x - speed);
        this.currentCmd = Cmd.LEFT;
    }

    public void goRight(){
        this.setX(x + speed);
        this.currentCmd = Cmd.RIGHT;
    }

    public void teleport(Position posTo) {
        this.setX(posTo.getX());
        this.setY(posTo.getY());
    }

    public void stop(){
        currentCmd = Cmd.IDLE;
    }

    public Cmd getCurrentCmd() {
        return currentCmd;
    }
}
