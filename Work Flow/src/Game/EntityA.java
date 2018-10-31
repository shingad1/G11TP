package Game;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityA {
	
	public void tick(boolean w, boolean a, boolean s, boolean d);
	public void render(Graphics g, boolean w, boolean a, boolean s, boolean d);
	public Rectangle getBounds();
	
	public double getX();
	public double getY();

}
