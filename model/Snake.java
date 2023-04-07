package model;

import java.util.ArrayList;
import java.util.List;
import resources.Coordinate;
import resources.Direction;
import view.GamePanel;
/**
 * cree et manipule les mouvements de snake
 * @author hache
 *
 */
public class Snake {

	private static final int SNAKE_LENGTH = 4;
	private List<Coordinate> snake_part;
	private Direction direction;
	private boolean grow;
	private boolean superSnake;
	private int countDown;
	
	/**
	 * initialise la liste snake_part <br>
	 * ajoute les elements a la liste snake
	 */
	public Snake() {

		snake_part = new ArrayList<>();
		this.setDirection(Direction.Right); 

		for(int i=0;i<SNAKE_LENGTH;i++) {
			snake_part.add(new Coordinate(i-(SNAKE_LENGTH-1),0));
		}
	}
	/**
	 * retoune la liste snake_part
	 * @return
	 */
	public List<Coordinate> getSnake_part() {
		return snake_part;
	}
	/**
	 * retourne la direction de snake
	 * @return
	 */
	public Direction getDirection() {
		return direction;
	}
	/**
	 * modifie la direction de snake
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	/**
	 * modifie le champs grow 
	 * @param grow
	 */
	public void setGrow(boolean grow) {
		this.grow = grow;
	}
	/**
	 * retourne le champs superSnake
	 * @return
	 */
	public boolean isSuperSnake() {
		return superSnake;
	}
	/**
	 * modifie le champs superSnake
	 * @param superSnake
	 */
	public void setSuperSnake(boolean superSnake) {
		this.superSnake = superSnake;
	}
	/**
	 * retourne le dernier element de la liste snake_part
	 * @return
	 */
	public int last() {
		return snake_part.size()-1;
	}
	/**
	 * cette fonction manipule les mouvements de snake <br>
	 * le mouvementse fait en ajoutant un element au debut de la liste et en retirant le dernier element<br>
	 * verifie si le champs grow est true pour ajouter un element a la liste snake_part<br>
	 * verifie si le snake est superSnake et compte 30 pas et remet superSnake a false<br>
	 * verifie si le snake est arrive a droite/gauche de plateau, descends d'un niveau et change de direction 
	 */
	public void move() {
		Coordinate current = snake_part.get(last());
		Coordinate next = new Coordinate( current.getX()+direction.getX(), current.getY()+direction.getY());

		if(grow) {
			snake_part.add(next);
		}
		setGrow(false);
		if(superSnake) {
			countDown++;
		
			if(countDown == 30) {
				superSnake = false;
				countDown = 0;
			}
		}
		
		if(next.getX()>= ( GamePanel.width /GamePanel.SIZE) || next.getX() == -1) {
			change_snakeDirection(next);
		}
		snake_part.add(next);
		snake_part.remove(0);
	}
	/**
	 * cette fonction permet de changer la direction de snake dans deux cas : <br>
	 * quand il arrive a la fin de plateau ou quand il rencontre un obstacle bois
	 * @param cor
	 */
	public void change_snakeDirection(Coordinate cor) {

		if(direction == Direction.Right) {
			cor.setX(cor.getX()-1);
			setDirection(Direction.Left);

		}else{
			cor.setX(cor.getX()+1);
			setDirection(Direction.Right);
		}
		cor.setY(cor.getY()+1);
	}
	
	/**
	 * cette fonction parcourt la liste des projectiles et tous les elements de serpent <br>
	 * verifie si l'un des projectile a touche un element de snake<br>
	 * retire cet element de serpent et renvoie true <br>
	 * @return
	 * sinon renvoie false
	 */
	public boolean projectile_collision_snake(Projectile proj) {
		for(int i=0;i<proj.getProjectile().size();i++) {
			Coordinate cproj = proj.getProjectile().get(i);
			for(int j=0;j<snake_part.size();j++) {
				
				if( snake_part.get(j).getX() == cproj.getX() && snake_part.get(j).getY() == cproj.getY()) {
					proj.getProjectile().remove(i);
					return true;
				}
			}
		}
		return false;
	}
}