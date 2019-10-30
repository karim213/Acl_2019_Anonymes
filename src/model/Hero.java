package model;

import engine.Cmd;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero {
    private int x;
    private int y;
    private Cmd currentCmd;
    private  boolean isAttaque;
    private BufferedImage[] sprites;


    public Hero(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentCmd = Cmd.IDLEDOWN;
        isAttaque = false;

    }

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public void goUp(){
        if(this.currentCmd == Cmd.UP || this.currentCmd == Cmd.IDLEUP) {
            this.setY(--y);
            this.currentCmd = Cmd.UP;
        }else{
            this.currentCmd = Cmd.IDLEUP;
        }
    }

    public void goDown(){
        if(this.currentCmd == Cmd.DOWN || this.currentCmd == Cmd.IDLEDOWN) {
            this.setY(++y);
            this.currentCmd = Cmd.DOWN;
        }else{
            this.currentCmd = Cmd.IDLEDOWN;
        }

    }

    public void goLeft(){
        System.out.println("elf");
        if(this.currentCmd == Cmd.LEFT || this.currentCmd == Cmd.IDLELEFT) {
            this.setX(--x);
            this.currentCmd = Cmd.LEFT;
        }else{
            this.currentCmd = Cmd.IDLELEFT;
        }
    }

    public void goRight(){
        if(this.currentCmd == Cmd.RIGHT || this.currentCmd == Cmd.IDLERIGHT) {
            this.setX(++x);
            this.currentCmd = Cmd.RIGHT;
        }else{
            this.currentCmd = Cmd.IDLERIGHT;
        }
    }

    public Cmd getCurrentCmd() {
        return currentCmd;
    }
    public void stop(){
        if (this.currentCmd == Cmd.LEFT || this.currentCmd == Cmd.IDLELEFT ){
            this.currentCmd = Cmd.IDLELEFT;
        }else if (this.currentCmd == Cmd.RIGHT || this.currentCmd == Cmd.IDLERIGHT){
            this.currentCmd = Cmd.IDLERIGHT;
        }else if (this.currentCmd == Cmd.UP || this.currentCmd == Cmd.IDLEUP){
            this.currentCmd = Cmd.IDLEUP;
        }else {
            this.currentCmd = Cmd.IDLEDOWN;
        }
    }

    public void attaque(){
        isAttaque = true;
    }
    public  Boolean isAttaque(){
        return isAttaque;
    }
    public void setAttaque(Boolean isAttaque){
        this.isAttaque = isAttaque;
    }

}