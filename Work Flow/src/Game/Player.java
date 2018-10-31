package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
		
	private double velX = 0;
	private double velY = 0;
		
	private Textures tex;
	
	public Player(double x, double y, Textures tex) {
		super(x, y);
		this.tex = tex;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0) {
			x = 0;
		}
		if(x >= 1280) {
			x = 1280;
		}
		if(y <= 0) {
			y = 0;
		}
		if(y >= 720 - 30) {
			y = 720 - 30;
		}
	}
	public void render(Graphics g, boolean w, boolean s, boolean a, boolean d) {
		if(a){
		g.drawImage(tex.pA, (int)x, (int)y,null);
		}
		else if(w) {
			g.drawImage(tex.pW, (int)x, (int)y,null);
		}
		else if(s) {
			g.drawImage(tex.pS, (int)x, (int)y,null);
		}
		else if(d) {
			g.drawImage(tex.pD, (int)x, (int)y,null);
		}
		else{
			g.drawImage(tex.pS, (int)x, (int)y,null);

		}
	}
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 44, 94);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}


}
