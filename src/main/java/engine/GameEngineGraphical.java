package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 *            
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique


		while (game.isFinished() == -1) {
				this.gui.paintMenu();
		     	Thread.sleep(100);
		}



		// boucle de game
		while (game.isFinished() >= 0) {
			// demande controle utilisateur
			Cmd c = this.gameController.getCommand();
			// fait evoluer le game
			if (game.isFinished() == 0)
				this.game.evolve(c);
			// affiche le game
			//this.gui.paintParty(false,null);
			if (game.isFinished() == -1) {
				this.gui.paintMenu();
			}else
				this.gui.paintParty(false,null);
			// met en attente
			Thread.sleep(80);
		}
		    this.gui.paintParty(true,game.isOver()?"lose":"win");
		    Thread.sleep(4000);
		    game.setisFinished(-1);
		    run();
		}
}
