package Game;

import java.awt.image.BufferedImage;

public class Textures {
	
public BufferedImage pW, pA, pS, pD, bH, bV;
public BufferedImage[] enemyL = new BufferedImage[4];
public BufferedImage[] enemyR = new BufferedImage[4];

	
	private SpriteSheet ss;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures() {
		
		
		pD= ss.grabImage(1, 1, 44, 94);
		pA= ss.grabImage(2, 1, 44, 94);
		pS= ss.grabImage(3, 1, 44, 94);
		pW= ss.grabImage(4, 1, 44, 94);
		
		bH = ss.grabImage(4, 2, 44, 94);
		bV = ss.grabImage(5, 2, 44, 94);
		
		enemyL[0] = ss.grabImage(6, 1, 44, 94);
		enemyL[1] = ss.grabImage(5, 1, 44, 94);
		enemyL[2] = ss.grabImage(7, 1, 44, 94);
		enemyL[3] = ss.grabImage(5, 1, 44, 94);
		enemyR[0] = ss.grabImage(1, 2, 44, 94);
		enemyR[1] = ss.grabImage(2, 2, 44, 94);
		enemyR[2] = ss.grabImage(3, 2, 44, 94);
		enemyR[3] = ss.grabImage(2, 2, 44, 94);
	}

}
