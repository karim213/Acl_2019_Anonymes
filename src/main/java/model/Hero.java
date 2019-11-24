package model;


public class Hero extends MovableEntity {
    private  boolean isAttaque;


    public Hero(int x, int y) {
        super(x, y, 3, 2);
        this.isAttaque = false;
    }

    public void attaque(){
        isAttaque = true;
    }

    public  Boolean isAttaque(){
        return isAttaque;
    }

    public void slow() {
        this.setSpeed(1);
    }

    public void normalSpeed() {
        setSpeed(2);
    }

    public void setAttaque(Boolean isAttaque){
        this.isAttaque = isAttaque;
    }
}
