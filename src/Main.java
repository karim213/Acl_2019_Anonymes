import model.Labyrinth;

import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Labyrinth labyrinth = new Labyrinth();
        Scanner obj = new Scanner(System.in);

        do{
            System.out.println("Enter commande(L/R/U/D)");
            String command = obj.nextLine();
            switch (command){
                case "L" : labyrinth.goLeft(); break;
                case "R" : labyrinth.goRight(); break;
                case "U" : labyrinth.goUp(); break;
                case "D" : labyrinth.goDown(); break;
            }

            System.out.println("("+labyrinth.getHero().getX()+","+labyrinth.getHero().getY()+")");

        }while(true);

    }

}
