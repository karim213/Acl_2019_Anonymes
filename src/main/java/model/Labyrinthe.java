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
import java.io.File;
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
        setLabyrinthe(null,null);
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

        if (chest.isOn(new Position(x, y))){
            if(level == NB_LEVELS) {
                isFinished = -1;
                level = 1;
                setLabyrinthe(null,null);
            }
            else {
                level = level + 1;
                setLabyrinthe(null,null);
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
                break;
            }
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

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void setHeals(List<Heal> heals) {
        this.heals = heals;
    }

    public void setSands(List<Sand> sands) {
        this.sands = sands;
    }

    public void setTeleporters(List<Teleporter> teleporters) {
        this.teleporters = teleporters;
    }

    public void setTraps(List<Trap> traps) {
        this.traps = traps;
    }

    public void setWaters(List<Water> waters) {
        this.waters = waters;
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }

    @Override
    public boolean isOver() {
        if (hero.isDead()) {
            level = 1;
            hero = new Hero(4, 4);
            setLabyrinthe(null,null);
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
        Rectangle rect1 = new Rectangle(hero.getX()*5-10 , hero.getY()*5-10 , 60, 60);
        Rectangle rect2;
        for(Wall wall : walls){
            rect2 = new Rectangle(wall.getPosition().getX()*5,wall.getPosition().getY()*5,20,20);
            Rectangle intersection = rect1.intersection(rect2);
            System.out.println(hero.getCurrentCmd());
            if(intersection.getHeight() > 0 && intersection.getWidth() > 0) {
                walls.remove(wall);
                break;
            }
        }

        rect1 = new Rectangle(hero.getX()*5 - 20, hero.getY()*5 - 20, 80, 80);
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
            char[][] l = new char[20][40];
            for (int i = 0 ; i< 20;i++){
                for(int t = 0 ;t<40;t++)
                    l[i][t] = '.';
            }
            int ligne =0;
            for(int i = 0;i<getWallsPosition().size();i++){
                if(getWallsPosition().get(i).getY() != ligne){
                    ligne = getWallsPosition().get(i).getY();
                    l[ligne/4][0]='#';
                    if (ligne==76){
                        for (int t = 0 ; t< 40;t++){
                            l[ligne/4][t] = '#';
                        }
                    }
                }
                else{
                    l[ligne/4][getWallsPosition().get(i).getX()/4] = '#';
                }
            }

            ligne =0;
            for(int i = 0;i<getEnemies().size();i++){
                if(getEnemies().get(i).getY() != ligne){
                    ligne = getEnemies().get(i).getY();
                    if(getEnemies().get(i).getType()=="Monster")
                        l[ligne/4][getEnemies().get(i).getX()/4] = 'S';
                    if(getEnemies().get(i).getType()=="Boss")
                        l[ligne/4][getEnemies().get(i).getX()/4] = 'B';
                    if(getEnemies().get(i).getType()=="Ghost")
                        l[ligne/4][getEnemies().get(i).getX()/4] = 'G';
                }
                else{
                    if(getEnemies().get(i).getType()=="Monster")
                        l[ligne/4][getEnemies().get(i).getX()/4] = 'S';
                    if(getEnemies().get(i).getType()=="Boss")
                        l[ligne/4][getEnemies().get(i).getX()/4] = 'B';
                    if(getEnemies().get(i).getType()=="Ghost")
                        l[ligne/4][getEnemies().get(i).getX()/4] = 'G';
                }
            }

            ligne =0;
            for(int i = 0;i<traps.size();i++){
                if(traps.get(i).getPosition().getY() != ligne){
                    ligne =traps.get(i).getPosition().getY();
                    l[ligne/4][traps.get(i).getPosition().getX()/4] = 'T';
                }
                else{
                    l[ligne/4][traps.get(i).getPosition().getX()/4] = 'T';
                }
            }

            ligne =0;
            for(int i = 0;i<teleporters.size();i++){
                if(teleporters.get(i).getPosition().getY() != ligne){
                    ligne =teleporters.get(i).getPosition().getY();
                    l[ligne/4][teleporters.get(i).getPosition().getX()/4] = 'P';
                }
                else{
                    l[ligne/4][teleporters.get(i).getPosition().getX()/4] = 'P';
                }
            }

            ligne =0;
            for(int i = 0;i<sands.size();i++){
                if(sands.get(i).getPosition().getY() != ligne){
                    ligne =sands.get(i).getPosition().getY();
                    l[ligne/4][sands.get(i).getPosition().getX()/4] = 'R';
                }
                else{
                    l[ligne/4][sands.get(i).getPosition().getX()/4] = 'R';
                }
            }

            ligne =0;
            for(int i = 0;i<heals.size();i++){
                if(heals.get(i).getPosition().getY() != ligne){
                    ligne =heals.get(i).getPosition().getY();
                    l[ligne/4][heals.get(i).getPosition().getX()/4] = 'H';
                }
                else{
                    l[ligne/4][heals.get(i).getPosition().getX()/4] = 'H';
                }
            }

            l[getPosChest().getY()/4][getPosChest().getX()/4] = 'C';
            List<String> lines = new ArrayList<>();

            for(int i = 0 ; i<20;i++){
                lines.add(String.valueOf(l[i]));
            }
            lines.add(String.valueOf(hero.getX()));
            lines.add(String.valueOf(hero.getY()));
            lines.add(String.valueOf(hero.getPv()));
            lines.add(String.valueOf(level));
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
            if (verifyFile(file)){
                try {
                    File f = file.toFile();
                    List<String> save = new ArrayList<>();
                    Scanner lineOfFile = new Scanner(f) ;
                    for(int  i = 0 ;i<24;i++) {
                        save.add(lineOfFile.nextLine());
                    }
                    this.level = Integer.parseInt(save.get(23));
                    this.hero = new Hero(Integer.parseInt(save.get(20)),Integer.parseInt(save.get(21)));
                    this.hero.setPv(Integer.parseInt(save.get(22)));
                    setLabyrinthe("load",save);
                    setisFinished(0);

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(new Frame(), "Error while opening the file");
                }
            }
        }
    }

    private boolean verifyFile(Path file) {
        List<String> save = new ArrayList<>();

        try {
            save = Files.readAllLines(file);
        } catch (IOException e) {

        }

        if (save.size() > 24) {
            System.out.println(save.size());
            JOptionPane.showMessageDialog(new Frame(), "Invalid file");
            return false;
        }

        for (String s : save) {
            if (s.length() > 40) {
                System.out.println(s.length() + "sdlmjfmlsdfj");
                JOptionPane.showMessageDialog(new Frame(), "Invalid file");
                return false;
            }
        }
        return true;
    }

    public void setLabyrinthe(String mode,List<String> save){

        int rows = 20;
        int cols = 40;

        walls.clear();
        enemies.clear();
        heals.clear();
        sands.clear();
        teleporters.clear();
        traps.clear();
        waters.clear();

        if(mode=="load") {
            for (int y = 0; y < 20; y++) {
                String line = save.get(y);
                for (int x = 0; x < cols; x++) {
                    char start = line.charAt(x);
                    if (start == '#') {
                        walls.add(new Wall(x * 4, y * 4));
                    } else if (start == 'B') {
                        Boss b = new Boss(x * 4, y * 4, this);
                        b.setMovementStrategy(new GhostMovement());
                        enemies.add(b);
                    } else if (start == 'S') {
                        Monster m = new Monster(x * 4, y * 4, this);
                        m.setMovementStrategy(new SnakeMovement());
                        enemies.add(m);
                    } else if (start == 'G') {
                        Ghost m = new Ghost(x * 4, y * 4, this);
                        m.setMovementStrategy(new GhostMovement());
                        enemies.add(m);
                    } else if (start == 'H') {
                        heals.add(new Heal(new Position(x * 4, y * 4)));
                    } else if (start == 'C') {
                        chest = new Chest(new Position(x * 4, y * 4));
                    } else if (start == 'R') {
                        sands.add(new Sand(new Position(x * 4, y * 4)));
                    } else if (start == 'T') {
                        traps.add(new Trap(new Position(x * 4, y * 4)));
                    } else if (start == 'B') {
                        enemies.add(new Boss(x * 4, y * 4, this));
                    } else if (start == 'P') {
                        System.out.println(teleporters);
                        if (teleportPosition == null) {
                            teleportPosition = new Position(x * 4, y * 4);
                            teleporters.add(new Teleporter(teleportPosition, new Position(50, 42)));
                        } else {
                            teleporters.add(new Teleporter(teleportPosition, new Position(50, 42)));
                            teleportPosition = null;
                        }

                    }
                }
            }
        }else {
            int rowsnew = rows * (level - 1);
            for (int y = rowsnew; y < rowsnew + 20; y++) {
                String line = file.get(y);
                for (int x = 0; x < cols; x++) {
                    char start = line.charAt(x);
                    if (start == '#') {
                        walls.add(new Wall(x * 4, (y - rowsnew) * 4));
                    } else if (start == 'B') {
                        Boss b = new Boss(x * 4, y * 4, this);
                        b.setMovementStrategy(new GhostMovement());
                        enemies.add(b);
                    } else if (start == 'S') {
                        Monster m = new Monster(x * 4, (y - rowsnew) * 4, this);
                        m.setMovementStrategy(new SnakeMovement());
                        enemies.add(m);
                    } else if (start == 'G') {
                        Ghost m = new Ghost(x * 4, (y - rowsnew) * 4, this);
                        m.setMovementStrategy(new GhostMovement());
                        enemies.add(m);
                    } else if (start == 'H') {
                        heals.add(new Heal(new Position(x * 4, (y - rowsnew) * 4)));
                    } else if (start == 'C') {
                        chest = new Chest(new Position(x * 4, (y - rowsnew) * 4));
                    } else if (start == 'R') {
                        sands.add(new Sand(new Position(x * 4, (y - rowsnew) * 4)));
                    } else if (start == 'T') {
                        traps.add(new Trap(new Position(x * 4, (y - rowsnew) * 4)));
                    } else if (start == 'B') {
                        enemies.add(new Boss(x * 4, (y - rowsnew) * 4, this));
                    } else if (start == 'P') {

                        if (teleportPosition == null) {
                            teleportPosition = new Position(x * 4, (y - rowsnew) * 4);
                        } else {
                            teleporters.add(new Teleporter(teleportPosition, new Position(50, 42)));
                            teleportPosition = null;
                        }

                    }
                }

                hero.setX(4);
                hero.setY(4);
            }
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

