package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * moteur de game generique.
 * On lui passe un game et un afficheur et il permet d'executer un game.
 */
public class GameEngineGraphical {

	double interpolation = 0;
	final int TICKS_PER_SECOND = 25;
	final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
	final int MAX_FRAMESKIP = 5;
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
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter,this.gameController);

		// boucle de game
		double next_game_tick = System.currentTimeMillis();
    int loops;

    while (!this.game.isFinished()) {
        loops = 0;
        while (System.currentTimeMillis() > next_game_tick
                && loops < MAX_FRAMESKIP) {

            // demande controle utilisateur
			Cmd c = this.gameController.getCommand();
			// fait evoluer le game
			this.game.evolve(c);
			// met en attente

            next_game_tick += SKIP_TICKS;
            loops++;
        }

        interpolation = (System.currentTimeMillis() + SKIP_TICKS - next_game_tick
                / (double) SKIP_TICKS);
		// affiche le game
		this.gui.paint(false,null);
    }
		this.gui.paint(true,game.isOver()?"lose":"win");	}
}
