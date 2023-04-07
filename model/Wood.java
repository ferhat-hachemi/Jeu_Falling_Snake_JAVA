package model;

import java.awt.Color;

/**
 * cette class est utilisee pour creer un obstacle de type bois (wood)
 * @author hache
 */
public class Wood extends Obstacle{
	
	private Color color;

	/**
	 * initialise la couleur de bois
	 * @param snake 
	 */
	public Wood(Snake snake) {
		super(snake);
		this.color = Color.BLUE;
	}

	/**
	 * retourne la couleur de bois
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * quand le serpent rencontre un obstacle bois il descend et change de direction
	 */
	public void getEffect() {
		snake.change_snakeDirection(snake.getSnake_part().get(snake.last()));
	}
}