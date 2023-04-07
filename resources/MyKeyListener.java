package resources;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Player;
import model.Projectile;

/**
 * cette classe implement KeyListener pour manipuler avec les touches de clavier les mouvements de player et le lancement des projectiles
 * @author hache
 */
public class MyKeyListener implements KeyListener{

	private Player player;
	private Projectile proj;
	/**
	 * constructeur de la class MyKeyListener <br>
	 * initilaise player et projectile
	 * @param player
	 * @param proj
	 */
	public MyKeyListener(Player player,Projectile proj) {
		this.player = player;
		this.proj = proj;
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	
	/**
	 * cette fonction permet de bouger le player avec les touches RIGHT-LEFT en manipulant le premier element de joueur <br>
	 * chaque appuie de la touche SPACE cree et lance un projectile
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()){
		case KeyEvent.VK_RIGHT:
			player.movePlayer(Direction.Right);
			break;

		case KeyEvent.VK_LEFT:
			player.movePlayer(Direction.Left);
			break;

		case KeyEvent.VK_SPACE:
			proj.addProjectile();
			break;

		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}
}
