package model;

import java.awt.Color;

/**
 * cette class est utilisee pour creer un obstacle de type myrtille (blueberry)
 * @author hache
 */
public class Blueberry extends Obstacle{
	
	private Color color;

	/**
	 * initialise la couleur de la myrtille
	 * @param snake 
	 */
	public Blueberry(Snake snake) {
		super(snake);
		this.color = new Color(100,50,100);
	}

	/**
	 * retourne la couleur de myrtille
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * l'effet de la myrtille permet de rendre le serpent invincible donc met superSnake a true
	 */
	public void getEffect() {
		snake.setSuperSnake(true);
	}
}