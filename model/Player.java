package model;

import java.util.ArrayList;
import java.util.List;
import resources.Coordinate;
import resources.Direction;
import view.GamePanel;

/**
 * cree et manipule les mouvements de player
 * @author hache 
 */
public class Player {

	private List<Coordinate> player;
	private Coordinate first;

	/**
	 * initialise la liste player <br>
	 * creation de joueur dans le plateau <br>
	 * initialise le premier element de la liste player
	 */
	public Player() {
		player = new ArrayList<>();
		player.add(new Coordinate(0, (GamePanel.height/GamePanel.SIZE)-2));
		first= player.get(0);
	}
	/**
	 * retourne la liste player	
	 * @return
	 */
	public List<Coordinate> getPlayer() {
		return player;
	}
	/**
	 * retourne le premier element de la liste player
	 * @return
	 */
	public Coordinate getFirst() {
		return first;
	}
	/**
	 * cette fonction qui permet de bouger le player dans la direction Right ou Left selon le parametre direction
	 */
	public void movePlayer(Direction direction) {
		first.setX(first.getX() + direction.getX());
	}
	/** 
	 * cette fonction permet de touujour garder player dans le plateau et de ne pas depasser la borne droite/gauche
	 */
	public void player_out() {
		if(first.getX()> ( GamePanel.width /GamePanel.SIZE)-1) {
			first.setX(0);
		}
		if(first.getX()< 0) {
			first.setX((GamePanel.width /GamePanel.SIZE)-1);	
		}
	}
}