package engine;

import javax.swing.*;
import java.awt.*;


/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * interface graphique avec son controller et son afficheur
 *
 */
public class GraphicalInterface  {

	/**
	 * le Panel pour l'afficheur
	 */
	private DrawingPanel panel;
	private DrawingScorePanel scorePanel;


	/**
	 * la construction de l'interface graphique: JFrame avec panel pour le game
	 * 
	 * @param gamePainter l'afficheur a utiliser dans le moteur
	 * @param gameController l'afficheur a utiliser dans le moteur
	 * 
	 */
	public GraphicalInterface(GamePainter gamePainter, GameController gameController){
		JFrame f=new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// attacher le panel contenant l'afficheur du game
		this.panel=new DrawingPanel(gamePainter);
		this.scorePanel = new DrawingScorePanel(gamePainter);

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(this.scorePanel );
		panel.add(this.panel );
		f.setContentPane(panel);

		// attacher controller au panel du game
		panel.addKeyListener(gameController);

		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}

	/**
	 * mise a jour du dessin
	 */
	public void paint(boolean over,String s) {
		this.scorePanel.drawGame(over, s);
		this.panel.drawGame(over,s);
	}
	
}
