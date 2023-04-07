package model;

import java.util.ArrayList;
import java.util.List;
import resources.Coordinate;

/**
 * cree et manipule les projectiles
 * @author hache
 */
public class Projectile {

	private List<Coordinate> projectile;
	private Player player;
	/**
	 * initialise player <br>
	 * initialise la liste projectile <br>
	 * @param player
	 * @param snake
	 */
	public Projectile(Player player) {
		this.player = player;
		projectile = new ArrayList<>();
	}
	/**
	 * retourne la liste projectile
	 * @return
	 */
	public List<Coordinate> getProjectile() {
		return projectile;
	}
	/**
	 * cree un projectile a la meme position actuelle de player <br>
	 */
	public void addProjectile() {
		projectile.add(new Coordinate(player.getFirst().getX(), player.getFirst().getY()));
	}
	/**
	 * cette fonction permet de bouger tous les projectiles lances par player
	 */
	public void moveProjectile() {
		for(int i=0;i<projectile.size();i++) {
			Coordinate current  = projectile.get(i);
			current.setY(current.getY()-1 );
		}
	}

	/**
	 * cette fonction parcourt la liste des projectiles et des obstacles <br>
	 * verifie si un projectile touche un obstacle <br>
	 * retire ces deux elements et renvoie true<br>
	 * @param obst
	 * @return
	 * sinon renvoie false
	 */
	public boolean projectile_collision_obstacle(Obstacle obst) {
		for(int i=0;i<getProjectile().size();i++) {
			for(int j=0;j<obst.getObstacle().size();j++) {
				Coordinate obs = obst.getObstacle().get(j);
				
				if(getProjectile().get(i).getX()== obs.getX() && getProjectile().get(i).getY() == obs.getY()) {
					projectile.remove(i);
					obst.getObstacle().remove(j);
					return true;
				}
			}
		}
		return false;
	}
}