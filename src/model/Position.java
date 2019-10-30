package model;


public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
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

    public boolean equals(Position p) {
        return p.x == this.x && p.y == this.y;
    }

    public boolean on(Position p){
        if (p.x >= this.x && p.x <= this.x + 1) {
            if (p.y <= this.y && p.y >= this.y - 5){
                return true;
            }
        }
        return false;
    }

}
