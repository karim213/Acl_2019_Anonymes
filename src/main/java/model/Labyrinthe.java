package model;

import engine.Cmd;
import engine.Game;
import factories.TestFactory;
import model.enemies.*;
import model.objects.*;
import model.walls.Wall;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
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
    private List<Wall> walls;
    private List<Enemy> enemies;
    private List<Heal> heals = new ArrayList<>();
    private List<Sand> sands = new ArrayList<>();
    private List<Teleporter> teleporters = new ArrayList<>();
    private List<Trap> traps = new ArrayList<>();
    private List<Water> waters = new ArrayList<>();
    private Chest chest;
    private int isFinished;
    private int level;
    private List<String>  file = new ArrayList<>();
    private Position teleportPosition;



    public Labyrinthe() {
        this.hero = new Hero(4,4);
        this.level = 1;
        this.walls = new ArrayList<>();
        this.enemies=new ArrayList<>();
        this.isFinished = -3;
        teleportPosition = null;

        Scanner lineOfFile = new Scanner(TestFactory.class.getClassLoader().getResourceAsStream("maze.txt")) ;
        for(int  i = 0 ;i<100;i++) {
            file.add(lineOfFile.nextLine());
        }
        setLabyrinthe();
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

        if (chest.isOn(new Position(x+hero.getSpeed(), y))){
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

        this.performObjectActions(hero);

        if(isEnemy(getHero().getX(),getHero().getY())){
            this.getHero().receiveDamage();
        }

        if(hero.isDead()){
            isFinished = -1;
        }
        enemiesProcess();
    }

    public void performObjectActions(Hero hero) {
        for(Trap trap : traps){
            if(trap.isOn(new Position(hero.getX(), hero.getY()))){
                trap.action(hero);
            }
        }
        for(Teleporter teleporter : teleporters){
            if(teleporter.isOn(new Position(hero.getX(), hero.getY()))){
                teleporter.action(hero);
            }
        }
        for (Heal heal : heals) {
            if (heal.isOn(new Position(hero.getX(), hero.getY()))) {
                heal.action(hero);
                heals.remove(heal);
            }
            break;
        }

        for(Sand sand : sands){
            if (sand.isOn(new Position(hero.getX(), hero.getY())))
                sand.action(hero);
            else sand.removeAction(hero);
        }
        for(Water water : waters){
            if (water.isOn(new Position(hero.getX(), hero.getY())))
                water.action(hero);
            else water.removeAction(hero);
        }
    }

    public boolean isEnemy(int x , int y){
        Rectangle rect1 = new Rectangle(x*5, y*5, 40, 40);

        boolean isEnem=false;
        for (Enemy enemy : enemies){
            Rectangle rect2 = new Rectangle(enemy.getX()*5 , enemy.getY()*5, 20, 20);
            Rectangle intersection = rect1.intersection(rect2);

            if(intersection.getHeight() > 0 && intersection.getWidth() > 0) {
                isEnem= true;
            }
        }
        return isEnem;
    }

    private void enemiesProcess(){
        for (Enemy e : enemies){
            e.move();
        }
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
        Rectangle rect1 = new Rectangle(x*5, y*5, 20, 40);

        for (Wall w : walls) {
            Rectangle rect2 = new Rectangle(w.getPosition().getX() *5 , (w.getPosition().getY())*5, 20, 20);
            Rectangle intersection = rect1.intersection(rect2);

            if (intersection.getWidth() > 0 && intersection.getHeight() > 0){
                return false;
            }
        }
        return true;
    }

    public Hero getHero() {
        return hero;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void attack(){
        Rectangle rect1 = new Rectangle(hero.getX()*5 - 20, hero.getY()*5 - 20, 80, 80);
        Rectangle rect2;

        for (Enemy enemy : enemies){
            if (enemy.getType().equals("Monster")) {
                rect2 = new Rectangle(enemy.getX() *5 , enemy.getY()*5, 20, 20);
            }
            else {
                rect2 = new Rectangle(enemy.getX() *5 , enemy.getY()*5, 40, 40);
            }
            Rectangle intersection = rect1.intersection(rect2);

            if(intersection.getHeight() > 0 && intersection.getWidth() > 0) {
                enemy.receiveDamage();
                if(enemy.getLabyrinthe().getHero().getCurrentCmd() == Cmd.RIGHT) {
                    enemy.goRight();
                    enemy.goRight();
                    enemy.goRight();
                    enemy.goRight();
                }else{
                    enemy.goLeft();
                    enemy.goLeft();
                    enemy.goLeft();
                    enemy.goLeft();
                }
                if (enemy.isDead()) {
                    this.enemies.remove(enemy);
                }
                return;
            }
        }
    }

    public void save() {
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int r = j.showSaveDialog(null);

        if (r == JFileChooser.APPROVE_OPTION) {
            List<String> lines = new ArrayList<>();
            String line;

            for (int i = 0; i < file.size(); i++) {
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
                System.out.println(level);
                this.walls = new ArrayList<>();
                heals = new ArrayList<>();
                sands = new ArrayList<>();
                teleporters = new ArrayList<>();
                traps = new ArrayList<>();
                waters = new ArrayList<>();
                this.enemies=new ArrayList<>();
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

        walls.clear();
        enemies.clear();
        heals.clear();
        sands.clear();
        teleporters.clear();
        traps.clear();
        waters.clear();

        int rowsnew = rows*(level-1);
        System.out.println(rowsnew);
        for (int y = rowsnew; y < rowsnew+20; y++) {
            String line = file.get(y);
            for (int x = 0; x < cols; x++) {
                char start = line.charAt(x);
                if (start == '#') {
                    walls.add(new Wall(x * 4, (y-rowsnew ) * 4));
                }
                else if(start == 'B'){
                    Boss b = new Boss(x*4,y*4,this);
                    b.setMovementStrategy(new RandomMovement());
                    enemies.add(b);
                }
                else if(start == 'S'){
                    Monster m = new Monster(x*4,(y-rowsnew )*4,this);
                    m.setMovementStrategy(new SnakeMovement());
                    enemies.add(m);
                }
                else if(start == 'G'){
                    Ghost m = new Ghost(x*4,(y-rowsnew )* 4,this);
                    m.setMovementStrategy(new GhostMovement());
                    enemies.add(m);
                }
                else if(start == 'H'){
                    heals.add(new Heal(new Position(x*4,(y-rowsnew )*4)));
                }
                else if(start == 'C'){
                    chest = new Chest(new Position(x*4,(y-rowsnew )*4));
                }
                else if(start == 'R'){
                    sands.add(new Sand(new Position(x*4,(y-rowsnew )* 4)));
                }
                else if(start == 'T'){
                    traps.add(new Trap(new Position(x*4,(y-rowsnew )* 4)));
                }
                else if(start == 'P'){
                    if (teleportPosition == null) {
                        teleportPosition = new Position(x*4,(y-rowsnew )* 4);
                    }
                    else {
                        teleporters.add(new Teleporter(teleportPosition, new Position(50,42)));
                        teleportPosition = null;
                    }

                }
            }

            hero.setX(4);
            hero.setY(4);
        }
    }
    public List<Position> getWallsPosition(){
        List<Position> res = new ArrayList<>();

        for (Wall w : walls){
            res.add(w.getPosition());
        }

        return res;
    }

    public List<Position> getPosTeleporters() {
        List<Position> res = new ArrayList<>();
        for (Teleporter teleporter : teleporters) {
            res.add(teleporter.getPosition());
        }
        return res;
    }
    public List<Position> getPosTraps() {
        List<Position> res = new ArrayList<>();
        for (Trap trap : traps) {
            res.add(trap.getPosition());
        }
        return res;
    }
    public List<Position> getPosSands() {
        List<Position> res = new ArrayList<>();
        for (Sand sand : sands) {
            res.add(sand.getPosition());
        }
        return res;
    }
    public List<Position> getPosHeals() {
        List<Position> res = new ArrayList<>();
        for (Heal heal : heals) {
            res.add(heal.getPosition());
        }
        return res;
    }
    public List<Position> getPosWaters() {
        List<Position> res = new ArrayList<>();
        for (Water water : waters) {
            res.add(water.getPosition());
        }
        return res;
    }

    public Position getPosChest(){
        return chest.getPosition();
    }

}

