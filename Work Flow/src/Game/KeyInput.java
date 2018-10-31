package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
Game game;

	public KeyInput(Game game) {
		this.game = game;//As the input while be implemented into the game class. Producing this in player limits input to when player is available
	}
	
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

}
