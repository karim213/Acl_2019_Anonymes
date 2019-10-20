package start;


import engine.Cmd;
import engine.Game;
import model.Labyrinthe;

import java.util.Scanner;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) {

		Game labyrinthe = new Labyrinthe(30, 30);
        Cmd commandeEnCours ;

        Scanner sc = new Scanner(System.in);

		while (!labyrinthe.isFinished()){
			System.out.println("ecrire commande(L,R,U,D,S)");
			switch (sc.next().charAt(0)) {
				// si on appuie sur 'q',commande joueur est gauche
				case 'l':
				case 'L':
					commandeEnCours = Cmd.LEFT;
					break;

				case 'r':
				case 'R':
					commandeEnCours = Cmd.RIGHT;
					break;

				case 'u':
				case 'U':
					commandeEnCours = Cmd.UP;
					break;

				case 'd':
				case 'D':
					commandeEnCours = Cmd.DOWN;
					break;

				default:
					commandeEnCours = Cmd.IDLE;
					break;
			}
			labyrinthe.evolve(commandeEnCours);
		}
	}

}
