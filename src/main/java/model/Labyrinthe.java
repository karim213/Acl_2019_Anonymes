package model;

import engine.Cmd;
import engine.Game;
import factories.TestFactory;
import model.enemies.*;
import model.objects.*;
import model.walls.Walls;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private Position teleportPosition;


    public Labyrinthe(Hero hero, Walls walls,Objects objects) {
        this.hero = hero;
        this.walls = walls;
        this.objects = objects;
        this.isFinished = -1;
        teleportPosition = null;
    }

    public Labyrinthe() {
        this.hero = new Hero(4,4);
        this.level = 1;
        this.walls = new Walls();
        this.objects = new Objects();
        this.enemies=new Enemies();
        this.isFinished = -3;
        teleportPosition = null;

        Scanner lineOfFile = new Scanner(TestFactory.class.getClassLoader().getResourceAsStream("maze.txt")) ;
        for(int  i = 0 ;i<100;i++) {
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
                    hero.goUp();
                }
                break;
            case DOWN:
                if (hero.getY()<HEIGHT-10 && isFree(x, y+hero.getSpeed())){
                    hero.goDown();
                }
                break;
            case LEFT:
                if (hero.getX()>0 && isFree(x-hero.getSpeed(), y)){
                    hero.goLeft();
                }
                break;
            case RIGHT:
                if (hero.getX()<WIDTH-10 &&  isFree(x+hero.getSpeed(), y)){
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

        if (objects.isOnChest(new Position(hero.getX(), hero.getY()))) {
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

        this.objects.performObjectActions(hero);

        if(enemies.isEnemy(getHero().getX(),getHero().getY())){

            this.getHero().receiveDamage();
        }

        if(hero.isDead()){
            isFinished = -1;
        }
        enemiesProcess();
    }

    private void enemiesProcess(){
        enemies.processMonsters();
    }

    @Override
    public int isFinished() {
        return isFinished;
    }

    @Override
    public void setisFinished(int status) {
        this.isFinished = status;
    }

    @Override
    public boolean isOver() {
        System.out.println(hero.isDead());
        if (hero.isDead()) {
            level = 1;
            hero = new Hero(4, 4);
            setLabyrinthe();
            return true;
        }
        else {
            return false;
        }
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

    public void save() {
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            List<String> lines = new ArrayList<>();
            int rowsnew = 20*(level-1);
            String line;

            for (int i = rowsnew; i < file.size(); i++) {
                line = file.get(i);
                lines.add(line);
            }

            Path file = Paths.get(j.getSelectedFile().getAbsolutePath());
            try {
                Files.write(file, lines, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void load() {
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int r = j.showOpenDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {

            Path file = Paths.get(j.getSelectedFile().getAbsolutePath());
            try {
                this.file = Files.readAllLines(file);
                this.level = (NB_LEVELS +2 - this.file.size()/20);
                this.walls = new Walls();
                this.objects = new Objects();
                this.enemies=new Enemies();
                setLabyrinthe();
                setisFinished(0);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setLabyrinthe(){

        int rows = 20;
        int cols = 40;

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
                else if(start == 'R'){
                    objects.addObject(new Sand(new Position(x*4,(y-rowsnew )* 4)));
                }
                else if(start == 'T'){
                    objects.addObject(new Trap(new Position(x*4,(y-rowsnew )* 4)));
                }
                else if(start == 'P'){
                    if (teleportPosition == null) {
                        teleportPosition = new Position(x*4,(y-rowsnew )* 4);
                    }
                    else {
                        objects.addObject(new Teleporter(teleportPosition, new Position(x*4,(y-rowsnew )* 4)));
                        teleportPosition = null;
                    }

                }
            }

            hero.setX(4);
            hero.setY(4);
        }
    }


}

