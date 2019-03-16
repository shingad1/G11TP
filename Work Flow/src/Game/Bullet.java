package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends GameObject implements EntityA {
	
	private Game game;
	private Textures tex;

	public Bullet(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
	}
	
    public void tick(boolean w, boolean s, boolean a, boolean d) {
    	if(w){
		    y -= 5;
		    w = true;
		    a = false;
    		s = false;
    		d = false;
    	}
    	else if(s) {
    		y += 5;
    		w = false;
    		a = false;
    		s = true;
    		d = false;
    	}
    	else if(a) {
    		x -= 5;
    		w = false;
    		a = true;
    		s = false;
    		d = false;
    	}
    	else if (d) {
    		x += 5;
    		w = false;
    		a = false;
    		s = false;
    		d = true;
    	}
    	else {
    	w = false;
	    a = false;
		s = false;
		d = false;
    	}
	}
    
    public void render(Graphics g, boolean w, boolean s, boolean a, boolean d) {
    	if(a){
		g.drawImage(tex.bH, (int)x, (int)y, null);
		w = false;
		a = true;
		s = false;
		d = false;
    	}
    	else if(d){
    	g.drawImage(tex.bH, (int)x, (int)y, null);
    	w = false;
		a = false;
		s = false;
		d = true;
        }
    	else if(w){
    	g.drawImage(tex.bV, (int)x, (int)y, null);
    	w = true;
	    a = false;
		s = false;
		d = false;
        }
    	else if(s){
        g.drawImage(tex.bV, (int)x, (int)y, null);
        w = false;
		a = false;
		s = true;
		d = false;
        }
    	else {
    	w = false;
	    a = false;
		s = false;
		d = false;
    	}
	}
    
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
