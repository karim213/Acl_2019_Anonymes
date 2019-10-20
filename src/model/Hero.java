package model;

public class Hero {
    private int x;
    private int y;

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public void goUp(){
        this.setY(--y);
    }

    public void goDown(){
        this.setY(++y);
    }

    public void goLeft(){
        this.setX(--x);
    }

    public void goRight(){
        this.setX(++x);
    }
}
