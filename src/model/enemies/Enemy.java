package model.enemies;


import engine.Cmd;
import model.Labyrinthe;

public abstract class Enemy {
    protected int x;
    protected int y;
    private EnemyMovementStrategy movementStrategy;
    private Cmd currentCmd;
    private int noSprite = -1;
    protected int nbMvts;

    public int getNoSprite() {
        noSprite = noSprite +1;
        if(noSprite==4){
            noSprite=0;
        }
        return noSprite;
    }

    public void setNoSprite(int noSprite) {
        this.noSprite = noSprite;
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
        if(y>0) {
            this.setY(--y);
            this.setCurrentCmd(Cmd.IDLEUP);
        }
        nbMvts++;
        if (nbMvts ==10) {
            setCurrentCmd(randomCmd());
            nbMvts=0;
        }
    }

    public void goDown(){
        System.out.println(y <Labyrinthe.HEIGHT);
        if(y<= Labyrinthe.HEIGHT) {
            this.setY(++y);
            this.setCurrentCmd(Cmd.IDLEDOWN);
        }
        nbMvts++;
        if (nbMvts ==10) {
            setCurrentCmd(randomCmd());
            nbMvts=0;
        }

    }

    public void goLeft(){
        if(x>0) {
            this.setX(--x);
            this.setCurrentCmd(Cmd.IDLELEFT);
        }
        nbMvts++;
        if (nbMvts ==10) {
            setCurrentCmd(randomCmd());
            nbMvts=0;
        }

    }

    public void goRight(){
        if(x< Labyrinthe.WIDTH){
            this.setX(++x);
            this.setCurrentCmd(Cmd.IDLERIGHT);
        }
        nbMvts++;
        if (nbMvts ==10) {
            setCurrentCmd(randomCmd());
            nbMvts=0;
        }

    }

    public void move(){
        movementStrategy.move(this);
    }

    public EnemyMovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public void setMovementStrategy(EnemyMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public Cmd getCurrentCmd() {
        return currentCmd;
    }

    public Cmd randomCmd(){
        int n = (int) (Math.random() * 4) + 1;
        Cmd cmd= null;
        switch (n){
            case 1: cmd= Cmd.IDLEUP;break;
            case 2:cmd= Cmd.IDLEDOWN;break;
            case 3:cmd= Cmd.IDLELEFT;break;
            case 4:cmd= Cmd.IDLERIGHT;break;
        }
        return cmd;
    }

    public void setCurrentCmd(Cmd currentCmd) {
        this.currentCmd = currentCmd;
    }
}
