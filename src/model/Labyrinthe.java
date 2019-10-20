package model;

import engine.Cmd;
import engine.Game;

public class Labyrinthe implements Game {

    private  final int HEIGHT ;
    private  final int WIDTH ;
    private Hero hero;

    public Labyrinthe(int height, int width) {
        HEIGHT = height;
        WIDTH = width;
        hero = new Hero(0 , 0);
    }

    @Override
    public void evolve(Cmd userCmd) {
           switch (userCmd){
               case UP:
                   if (hero.getY()>0 && isFree( hero.getX(), hero.getY()-1)) {
                       hero.goUp();
                   }
                   break;
               case DOWN:
                   if (hero.getY()<HEIGHT && isFree(hero.getX(), hero.getY()+1)) {
                       hero.goDown();
                   }
                   break;
               case LEFT:
                   if (hero.getX()>0 && isFree(hero.getX()-1, hero.getY())) {
                       hero.goLeft();
                   }
                   break;
               case RIGHT:
                   if (hero.getX()< WIDTH && isFree(hero.getX()+1, hero.getY())) {
                       hero.goRight();
                   }
                   break;
               case IDLE: break;
           }

        System.out.println("("+this.hero.getX()+" , "+this.hero.getY()+" )");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    public boolean isFree(int x , int y){
        /**
         * Verifier si la position est libre
         */
        return true;
    }
}
