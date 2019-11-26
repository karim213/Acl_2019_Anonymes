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
	private DrawingPanel partypanel;
	private DrawingScorePanel scorePanel;
	private DrawingMenuHome menuHome;
    private JPanel panel;

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
		this.partypanel=new DrawingPanel(gamePainter);
		this.scorePanel = new DrawingScorePanel(gamePainter);
		menuHome = new DrawingMenuHome(gamePainter);

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		//panel.add(this.scorePanel );
		this.panel.add(this.menuHome);
		f.setContentPane(panel);


		// attacher controller au panel du game
		panel.addKeyListener(gameController);
		panel.addMouseListener(gameController);


		f.pack();
		f.setVisible(true);
		f.getContentPane().setFocusable(true);
		f.getContentPane().requestFocus();
	}

	/**
	 * mise a jour du dessin
	 */
	public void paintParty(boolean over,String s) {
		this.panel.removeAll();
		this.panel.repaint();
		this.panel.add(this.scorePanel);
		this.panel.add(this.partypanel);
		this.scorePanel.drawGame(over, s);
		this.partypanel.drawGame(over,s);
		this.panel.updateUI();

	}

	public void paintMenu() {
		this.panel.removeAll();
		this.panel.add(this.menuHome);
		this.menuHome.drawGame();
		this.panel.updateUI();

	}
}
