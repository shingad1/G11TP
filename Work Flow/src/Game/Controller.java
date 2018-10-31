package Game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
	
	private LinkedList<EntityA> ea = new LinkedList<EntityA>();
	
	EntityA enta;
	
	private Textures tex;
	Random r = new Random();
	private Game game;
	
    public Controller(Textures tex, Game game) {
		this.tex = tex;
		this.game = game;
	}
    
    public void tick(boolean w, boolean s, boolean a, boolean d) {
		
		//A Class
		for(int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
			
			enta.tick(w, a, s, d);
		}
    }
	
	public void render(Graphics g, boolean w, boolean s, boolean a, boolean d) {
			
		//A Class
		for(int i = 0; i < ea.size(); i++) {
			enta = ea.get(i);
				
			enta.render(g, w, a, s, d);
		}}
	
	public void addEntity(EntityA block) {
		ea.add(block);
	}
	public void removeEntity(EntityA block) {
		ea.remove(block);
	}
	public LinkedList<EntityA> getEntityA() {
		return ea;
	}

}
