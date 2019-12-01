package model;

import engine.Cmd;
import engine.Game;
import factories.TestFactory;
import model.enemies.*;
import model.objects.Heal;
import model.objects.Objects;
import model.walls.Wall;
import model.walls.Walls;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.Constants.*;


public class Labyrinthe implements Game {

    private Hero hero;
    private Enemies enemies;
    private Walls walls;
    private Objects objects;
    private int isFinished;
    private int level;
    private List<String>  file = new ArrayList<>();


    public Labyrinthe(Hero hero, Walls walls,Objects objects) {
        this.hero = hero;
        this.walls = walls;
        this.objects = objects;
        this.isFinished = -1;
    }

    public Labyrinthe() {
        this.level = 1;
        this.walls = new Walls();
        this.objects = new Objects();
        this.enemies=new Enemies();
        this.isFinished = -1;

        Scanner lineOfFile = new Scanner(TestFactory.class.getClassLoader().getResourceAsStream("maze.txt")) ;
        for(int  i = 0 ;i<60;i++) {
            file.add(lineOfFile.nextLine());
        }
        setLabyrinthe();
    }

    public void setEnemies(Enemies enemies) {
        this.enemies = enemies;
    }
    @Override
    public void evolve(Cmd userCmd) {
        int x = hero.getX();
        int y = hero.getY();

        switch (userCmd){
            case UP:
                if (hero.getY()>0 &&  isFree(x, y-hero.getSpeed())){
                    if (objects.isOnChest(new Position( x, y-hero.getSpeed()))){
                        if(level == NB_LEVELS) {
                            isFinished = -1;
                            level = 1;
                            setLabyrinthe();
                        }
                        else {
                            level = level + 1;
                            setLabyrinthe();
                        }
                    }
                    hero.goUp();
                }
                break;
            case DOWN:
                if (hero.getY()<HEIGHT-10 && isFree(x, y+hero.getSpeed())){
                    if (objects.isOnChest(new Position(x, y+hero.getSpeed()))){
                        if(level == NB_LEVELS) {
                            isFinished = -1;
                            level = 1;
                            setLabyrinthe();
                        }
                        else {
                            level = level + 1;
                            setLabyrinthe();
                        }

                    }
                    hero.goDown();
                }
                break;
            case LEFT:
                if (hero.getX()>0 && isFree(x-hero.getSpeed(), y)){
                    if (objects.isOnChest(new Position(x-hero.getSpeed(), y))){
                        if(level == NB_LEVELS) {
                            isFinished = -1;
                            level = 1;
                            setLabyrinthe();
                        }
                        else {
                            level = level + 1;
                            setLabyrinthe();
                        }
                    }
                    hero.goLeft();
                }
                break;
            case RIGHT:
                if (hero.getX()<WIDTH-10 &&  isFree(x+hero.getSpeed(), y)){
                    if (objects.isOnChest(new Position(x+hero.getSpeed(), y))){
                        if(level == NB_LEVELS) {
                            isFinished = -1;
                            level = 1;
                            setLabyrinthe();
                        }
                        else {
                            level = level + 1;
                            setLabyrinthe();
                        }
                    }
                    hero.goRight();
                }
                break;
            case IDLE:
                hero.stop();
                break;
            case ATTACK:
                this.hero.attaque();
                this.attack();
                break;
        }
        this.objects.performObjectActions(hero);

        if(enemies.isEnemy(getHero().getX(),getHero().getY())){

            this.getHero().receiveDamage();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        enemiesProcess();
    }

    private void enemiesProcess(){
        enemies.processMonsters();
    }

    @Override
    public int isFinished() {
        if (hero.isDead())
            isFinished = -1;

        return isFinished;
    }

    @Override
    public void setisFinished(int status) {
        this.isFinished = status;
    }

    @Override
    public boolean isOver() {
        return hero.isDead();
    }

    public boolean isFree(int x , int y){
        return walls.isPosFree(x, y);
    }

    public Hero getHero() {
        return hero;
    }

    public Objects getObjects(){
        return objects;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public Walls getWalls() {
        return walls;
    }

    public void attack(){
        this.enemies.attack(this.hero.getX(), this.hero.getY());
    }


    public void setLabyrinthe(){

        int rows = 20;
        int cols = 40;

        if(this.level==1) {
            hero = new Hero(4,4);
            for (int y = 0; y < rows; y++) {
                String line = file.get(y);
                for (int x = 0; x < cols; x++) {
                    char start = line.charAt(x);
                    if (start == '#') {
                        walls.addWall(x * 4, y * 4);
                    }
                    else if(start == 'S'){
                        Monster m = new Monster(x*4,y*4,this);
                        m.setMovementStrategy(new SnakeMovement());
                        enemies.addEnemie(m);
                    }
                    else if(start == 'G'){
                        Ghost m = new Ghost(x*4,y* 4,this);
                        m.setMovementStrategy(new GhostMovement());
                        enemies.addEnemie(m);
                    }
                    else if(start == 'H'){
                        objects.addObject(new Heal(new Position(x*4,y*4)));
                    }
                    else if(start == 'C'){
                         objects.addChest(new Position(x*4,y*4));
                    }
                }
            }
        }
        else{
            walls.emptyWalls();
            enemies.emptyEnemies();
            objects.emptyObjects();
            int rowsnew = rows*(level-1);
            for (int y = rowsnew; y < rowsnew+20; y++) {
                String line = file.get(y);
                for (int x = 0; x < cols; x++) {
                    char start = line.charAt(x);
                    if (start == '#') {
                        walls.addWall(x * 4, (y-rowsnew )* 4);
                    }
                    else if(start == 'S'){
                        Monster m = new Monster(x*4,(y-rowsnew )* 4,this);
                        m.setMovementStrategy(new SnakeMovement());
                        enemies.addEnemie(m);
                    }
                    else if(start == 'G'){
                        Ghost m = new Ghost(x*4,(y-rowsnew )* 4,this);
                        m.setMovementStrategy(new GhostMovement());
                        enemies.addEnemie(m);
                    }
                    else if(start == 'H'){
                        objects.addObject(new Heal(new Position(x*4,(y-rowsnew )* 4)));
                    }
                    else if(start == 'C'){
                        objects.addChest(new Position(x*4,(y-rowsnew )* 4));
                    }
                }

                hero.setX(4);
                hero.setY(4);
            }
        }
    }


}

