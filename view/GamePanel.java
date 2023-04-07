package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import controller.App;
import model.Blueberry;
import model.Gold;
import model.Wood;
import model.Player;
import model.Projectile;
import model.Snake;
import model.Strawberry;
import resources.Coordinate;
import resources.MyKeyListener;

/**
 * cette classe permet d'initialiser l'interface graphique et d'afficher tous les composants de plateau
 * @author hache
 */
public class GamePanel extends JPanel  implements ActionListener {

	protected static final long serialVersionUID = 1L;
	
	public static final int width = 490;
	public static final int height = 485;
	public static final int SIZE = 30;
	private Snake snake;
	private Player player;
	private Projectile proj;
	private Wood wood;
	private Strawberry strwb;
	private Gold gold;
	private Blueberry berry;
	private Timer timer;

	/**
	 * initialise snake,player,projectile <br>
	 * cree une instance de classe de chaque type d'obstacle <br>
	 * initialise timer a 90 ms <br>
	 * ajoute un KeyListyener a GamePanel <br>
	 * @param snake
	 * @param player
	 * @param proj
	 */
	public GamePanel(Snake snake,Player player,Projectile proj) {
		this.snake = snake;
		this.player = player;
		this.proj = proj;
		this.wood = new Wood(snake);
		this.strwb = new Strawberry(snake);
		this.gold = new Gold(snake);
		this.berry = new Blueberry(snake);
		this.timer = new Timer(90,this);
		this.addKeyListener(new MyKeyListener(player,proj));
		this.setFocusable(true);
		this.requestFocusInWindow(true);
		this.setFocusTraversalKeysEnabled(false);
	}

	/**
	 * cette fonction permets de creer une interface graphique JFrame
	 */
	public void init_frame() {
		JFrame jframe = new JFrame("Falling Snakes");
		jframe.setContentPane(this);
		jframe.setSize(width,height);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setAlwaysOnTop(true);
		setBackground(Color.BLACK);
		jframe.setVisible(true);	
	}

	/**
	 * retourne timer
	 * @return
	 */
	public Timer getTimer() {
		return timer;
	}

	/**
	 * cette fonction permet de dessiner un composant de typer List sous forme de rectangle 
	 * @param g
	 * @param list
	 * @param color
	 */
	public void draw_component(Graphics g, List <Coordinate> list, Color color) {
		for(Coordinate cor : list) {		
			g.setColor(color);
			g.fillRect(cor.getX() *GamePanel.SIZE ,cor.getY()*GamePanel.SIZE ,GamePanel.SIZE ,GamePanel.SIZE );
		}
	}

	/**
	 * fait un cast de composant graphic en graphic 2 dimensions <br>
	 * cette fonction permet de dessiner les composants de jeu <br>
	 * affiche snake en couleur different quand il devient invincible
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g1 = (Graphics2D) g;

		if(snake.isSuperSnake()) {
			draw_component(g1, snake.getSnake_part(),new Color(100,0,100));
		}else
			draw_component(g1, snake.getSnake_part(),new Color(0,100,100));

		draw_component(g1, player.getPlayer(), Color.ORANGE);
		draw_component(g1, proj.getProjectile(),Color.PINK);

		g1.setColor(wood.getColor());
		wood.drawObstacle(g1);

		g1.setColor(strwb.getColor());
		strwb.drawObstacle(g1);

		g1.setColor(gold.getColor());
		gold.drawObstacle(g1);

		g1.setColor(berry.getColor());
		berry.drawObstacle(g1);
	}
	
	/**
	 * cette fonction permet de verifier si une partie de jeu est terminee et retourne true dans deux cas : <br>
	 * le serpent arrive au niveau inferieur donc il gagne <br>
	 * le serpent est detruit et le joueur gagne
	 * @return
	 * sinon retourne false
	 */
	public boolean game_is_over() {
		if(snake.getSnake_part().size() < 1) {
			JOptionPane.showMessageDialog(this, "Bravo! Vous avez gagne");
			return true;
		}

		if(snake.getSnake_part().get(0).getY() == (height/SIZE)-2) {
			JOptionPane.showMessageDialog(this, "Dommage! Vous avez perdu");
			return true;
		}
		return false;
	}
	
	/**
	 * rejouer ou fermer le plateau quand la partie est finie
	 */
	public void rejouer() {
		if(game_is_over()) {
			timer.stop();
			int reply = JOptionPane.showConfirmDialog(this, "Une nouvelle Partie ? ","Rejouer!",JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION){
				App.initGame();

			}else{
				System.exit(0);
			}
		}
	}

	/**
	 * verifie toutes les collisions snake et obstacles <br>
	 * met a jour le plateau
	 */
	public void update() {
		if(wood.snake_collision_obstacle(wood)) {
			wood.getEffect();
		}
		if(strwb.snake_collision_obstacle(strwb)) {
			strwb.getEffect();
		}
		if(gold.snake_collision_obstacle(gold)) {
			wood.changePosition();
			strwb.changePosition();
			berry.changePosition();
			gold.changePosition();
		}
		if(berry.snake_collision_obstacle(berry)) {
			berry.getEffect();
		}
		if(snake.projectile_collision_snake(proj) && !snake.isSuperSnake()) {
			snake.getSnake_part().remove(0);
		}
	}

	/**
	 * cette fonction recoit les evenements envoye par ActionListener pour mettre a jour le plateau
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!game_is_over()) {
			snake.move();
			player.player_out();
			proj.moveProjectile();
	
			proj.projectile_collision_obstacle(wood);
			proj.projectile_collision_obstacle(strwb);
			proj.projectile_collision_obstacle(gold);
			proj.projectile_collision_obstacle(berry);
			update();
		}
		rejouer();
		repaint();
	}
}