package model;

import engine.Cmd;


public class Hero {
    private Position position;
    private Cmd currentCmd;
    private  boolean isAttaque;
    private boolean over;
    private int pv;


    public Hero(int x, int y) {
        this.position = new Position(x, y);
        this.currentCmd = Cmd.IDLE;
        this.isAttaque = false;
        this.pv = 6;
        this.over = false;

    }

    public int getX() {
        return position.getX();
    }

    private void setX(int x) {
        this.position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    private void setY(int y) {
        this.position.setY(y);
    }

    public void goUp(){
        this.position.setY(this.position.getY()-1);
        this.currentCmd = Cmd.UP;
    }

    public void goDown(){
        this.position.setY(this.position.getY()+1);
        this.currentCmd = Cmd.DOWN;
    }

    public void goLeft(){
        this.position.setX(this.position.getX()-1);
        this.currentCmd = Cmd.LEFT;
    }

    public void goRight(){
        this.position.setX(this.position.getX()+1);
        this.currentCmd = Cmd.RIGHT;
    }

    public void stop(){
        currentCmd = Cmd.IDLE;
    }

    public Cmd getCurrentCmd() {
        return currentCmd;
    }

    public void attaque(){
        isAttaque = true;
    }
    public  Boolean isAttaque(){
        return isAttaque;
    }
    public void setAttaque(Boolean isAttaque){
        this.isAttaque = isAttaque;
    }
    public void setOver(boolean over) {
        this.over = over;
    }

    public boolean isOver() {
        return over;
    }
    public void addPv(){
        this.pv++;
    }
    public void subPv(){
        this.pv--;
        if (this.pv == 0)
            setOver(true);
    }

    public int getPv(){
        return this.pv;
    }

    public void teleport(Position posTo) {
        this.setX(posTo.getX());
        this.setY(posTo.getY());
    }
}
