package model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import resources.Coordinate;
import view.GamePanel;

/**
 * cette classe permet de creer et manipuler des obstacles selon le type
 * @author hache
 */
public class Obstacle {

	private static final int nb_obstacle = 2;
	private List<Coordinate> obstacle;
	private Random rand = new Random();
	protected int x;
	protected int y;
	protected Snake snake;

	/**
	 * initialise snake <br>
	 * initialise la liste obstacle <br>
	 * cree (nb_obstacle) obstacle dans le plateau en faisant appel a la fonction random_position()
	 */
	public Obstacle(Snake snake) {
		this.snake = snake;
		obstacle = new ArrayList<>();
		
		for(int i=0;i<nb_obstacle;i++) {
			random_position();
			obstacle.add(new Coordinate(x, y));
		}
	}

	/**
	 * retourne la liste obstacle
	 * @return
	 */
	public List<Coordinate> getObstacle() {
		return obstacle;
	}

	/**
	 * cette fonction genere des positions aleatoires dans le plateau
	 */
	public void random_position() {
		x = rand.nextInt((GamePanel.width/GamePanel.SIZE)-1);
		y = rand.nextInt((GamePanel.height/GamePanel.SIZE)-2);
		if(x==0) x = x+1;
		if(y==0) y = y+1;
	}

	/**
	 * cette fonction parcourt la liste des obstacle et dessine ces obstacle sous forme de rectangle 
	 * @param g
	 */
	public void drawObstacle(Graphics g) {
		for(Coordinate obs : getObstacle()) {
			g.fillRect(obs.getX() *GamePanel.SIZE, obs.getY()*GamePanel.SIZE ,GamePanel.SIZE ,GamePanel.SIZE );
		}
	}

	/**
	 * cette fonction permet de regenerer d'autres positions pour les obstacle <br>
	 * on fait appel a cette fonction quand snake mange une piece d'or
	 */
	public void changePosition() {
		for(int i=0;i<obstacle.size();i++) {
			random_position();
			obstacle.get(i).setX(x);
			obstacle.get(i).setY(y);
		}
	}
	
	/**
	 * cette fonction parcourt la liste des obstacles selon le type<br>
	 * verifie si snake touche un obstacle et renvoie true <br>
	 * retire cet obstacle dans le cas d'un obstacle de type fraise,myrtille,piece d'or <br>
	 * si l'obstacle est de type bois on le retire pas <br>
	 * @return
	 * sinon renvoie false
	 */
	public boolean snake_collision_obstacle(Obstacle obst) {
		Coordinate current = snake.getSnake_part().get(snake.last());
		for(int i=0;i<obst.getObstacle().size();i++) {			
			if(current.getX() == obst.getObstacle().get(i).getX() && current.getY() == obst.getObstacle().get(i).getY()){
				if(!(obst.getClass().equals(Wood.class))) {
					obst.getObstacle().remove(i);
				}
				return true;
			}
		}
		return false;
	}
}