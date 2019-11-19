package model.enemies;

import model.Labyrinthe;

public class SmartMovement implements EnemyMovementStrategy {
    @Override
    public void move(Enemy enemy, Labyrinthe game)  {
        if(game.getHero().getX()<enemy.getX()&&game.getHero().getY()==enemy.getY())
            enemy.goLeft();
        else if(game.getHero().getX()>enemy.getX()&&game.getHero().getY()==enemy.getY())
            enemy.goRight();
        else if(game.getHero().getY()<enemy.getY()&&game.getHero().getX()==enemy.getX())
            enemy.goUp();
        else if(game.getHero().getY()>enemy.getY()&&game.getHero().getX()==enemy.getX())
            enemy.goDown();
        else if(game.getHero().getX()<enemy.getX()&&game.getHero().getY()>enemy.getY()){
            enemy.goDown();
            enemy.goLeft();
        }
        else if(game.getHero().getX()<enemy.getX()&&game.getHero().getY()<enemy.getY()){
            enemy.goUp();
            enemy.goLeft();
        }
        else if(game.getHero().getX()>enemy.getX()&&game.getHero().getY()<enemy.getY()){
            enemy.goUp();
            enemy.goRight();
        }
        else if(game.getHero().getX()>enemy.getX()&&game.getHero().getY()>enemy.getY()){
            enemy.goDown();
            enemy.goRight();
        }
    }
}
