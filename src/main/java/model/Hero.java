package model;


public class Hero extends MovableEntity implements java.io.Serializable {
    private  boolean isAttaque;
    private int cmpt;

    public Hero(){
        super(0 , 0 , 3 , 2);
        cmpt = 0;
    }

    public Hero(int x, int y) {
        super(x, y, 6, 2);
        cmpt = 0;
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

    public void teleport(Position posTo) {
        this.setX(posTo.getX());
        this.setY(posTo.getY());
    }

    public void kill(){
        this.pv = 0;
        isDead = true;
    }

    @Override
    public boolean isDead() {
        cmpt++;
        System.out.println(isDead);
        return isDead;
    }

    @Override
    public void receiveDamage(){
        if (cmpt == 5) {
            cmpt = 0;
            this.pv--;
            if (this.pv <= 0) {
                isDead = true;
            }
        }
    }
}
