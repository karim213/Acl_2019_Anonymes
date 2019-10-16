package model;

public class Labyrinth {
    private Hero hero;
    private final static int HEIGHT = 100;
    private final static int WIDTH = 100;

    public Labyrinth() {
        this.hero = new Hero();
    }

    public void goDown(){
        int y = this.hero.getY();
        if(y <  this.HEIGHT){
            this.hero.setY(++y);
        }
    }

    public void goUp(){
        int y = this.hero.getY();
        if(y  > 0 ){
            this.hero.setY(--y);
        }
    }

    public void goLeft(){
        int x = this.hero.getX();
        if(x  > 0 ){
            this.hero.setX(--x);
        }
    }

    public void goRight(){
        int x = this.hero.getX();
        if(x <  this.WIDTH){
            this.hero.setX(++x);
        }
    }

    public Hero getHero() {
        return hero;
    }
}
