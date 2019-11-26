package model.enemies;

import engine.Cmd;
import model.Labyrinthe;

import static util.Constants.HEIGHT;
import static util.Constants.WIDTH;

public class Boss extends Enemy {

    public Boss(int x, int y, Labyrinthe labyrinthe) {
        super(x, y, 3 , labyrinthe);
    }

    @Override
    public String getType() {
        return "Boss";
    }

    @Override
    public void goUp(){
        if(y>0) {
            this.setY(--y);
        }
        this.currentCmd = Cmd.UP;
    }

    @Override
    public void goDown(){
        if(y < HEIGHT-10) {
            this.setY(++y);
        }
        this.currentCmd = Cmd.DOWN;
    }

    @Override
    public void goLeft(){
        if(x>0) {
            this.setX(--x);
        }
        this.currentCmd = Cmd.LEFT;
    }

    @Override
    public void goRight(){
        if(x< WIDTH-10){
            this.setX(++x);
        }
        this.currentCmd = Cmd.RIGHT;
    }
}
