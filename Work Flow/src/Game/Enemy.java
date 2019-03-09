package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject {
	
	Random r = new Random();
	private int speed = r.nextInt(5) + 1;
	private Game game;
	
	private Textures tex;
	
	Animation anim;
	


	public Enemy(double x, double y, Textures tex) {
		super(x, y);
		this.tex = tex;
		anim = new Animation(2, tex.enemyR[0], tex.enemyR[1], tex.enemyR[2], tex.enemyR[3]);
		this.game = game;	
	}
	
	public void tick() {
		anim.runAnimation();
	}
	
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32); //TO CHANGE as enemy is shorter than player	
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

}
