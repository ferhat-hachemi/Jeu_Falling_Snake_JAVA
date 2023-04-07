package controller;

import javax.swing.JOptionPane;
import model.Player;
import model.Projectile;
import model.Snake;
import view.GamePanel;

/**
 * cette class est la class main de projet, permet de lancer l'application
 * @author hache
 */
public class App {
	
	/**
	 * Lancement de la fenetre 
	 * @param args
	 */
	public static void main(String[] args) {

		initGame();	
	}
	
	/**
	 * initalisation de jeu 
	 */
	public static void initGame() {

		Snake snake = new Snake();
		Player player = new Player();
		Projectile projectile = new Projectile(player);
		GamePanel gamePanel = new GamePanel(snake,player,projectile);
		gamePanel.init_frame();	
		JOptionPane.showMessageDialog(gamePanel, "Utilisez les touches RIGHT-LEFT pour bouger le canon et SPACE pour tirer. Press OK to start");
		gamePanel.getTimer().start();
	}
}
