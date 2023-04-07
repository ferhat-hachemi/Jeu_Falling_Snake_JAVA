package model;

import java.awt.Color;

/**
 * cette class est utilisee pour creer un obstacle de type piece d'or (gold)
 * @author hache
 */
public class Gold extends Obstacle{
	
	private Color color;

	/**
	 * initialise la couleur de la piece d'or
	 * @param snake 
	 */
	public Gold(Snake snake) {
		super(snake);
		this.color = Color.YELLOW;
	}

	/**
	 * retourne la couleur de piece d'or
	 * @return
	 */
	public Color getColor() {
		return color;
	}
}