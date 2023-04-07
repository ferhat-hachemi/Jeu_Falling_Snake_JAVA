package model;

import java.awt.Color;

/**
 * cette class est utilisee pour creer un obstacle de type fraise (strawberry)
 * @author hache
 */
public class Strawberry extends Obstacle{
	
	private Color color;

	/**
	 * initialise la couleur de la fraise
	 * @param snake 
	 */
	public Strawberry(Snake snake) {
		super(snake);
		this.color = Color.RED;
	}

	/**
	 * retourne la couleur de la fraise
	 * @return
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * l'effet de la fraise est de mettre grow a true pour rendre le serpent plus long
	 */
	public void getEffect() {
		snake.setGrow(true);
	}
}