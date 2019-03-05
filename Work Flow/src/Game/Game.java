package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int width = 1260;
	public static final int height = 710;
	public final String title = "Work Flow";
	
	public boolean running = false;
	public Thread thread;
	
	public static int counter = 0;
				
	public boolean w = false, s = false, a = false, d = false;
	
	private boolean is_shooting = false;
	
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage blackout1 = null;
	private BufferedImage blackout2 = null;
	private BufferedImage blackout3 = null;
	
	private Player p;
	private Enemy e;
	private Controller c;
    private Textures tex;
    
    public LinkedList<EntityA> ea;
    
    	
	public void init() {
		
       requestFocus();       
       		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/WorkFlowTemplate.png");
			blackout1 = loader.loadImage("/blackout1.png");
			blackout2 = loader.loadImage("/blackout2.png");
			blackout3 = loader.loadImage("/blackout3.png");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		tex = new Textures(this);
		p = new Player(width / 2 - 44, height / 2 - 90, tex);
		e = new Enemy(400, 400, tex);
		c = new Controller(tex, this);
		
		ea = c.getEntityA();
		
		this.addKeyListener(new KeyInput(this));
	}
	
	private synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	public void run() {
		
        init();
        
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(running) {
			
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				updates = 0;
				frames = 0;
			}
			render();
			counter++;
		}
		stop();
		
	}
	public void tick() {
		p.tick();
		e.tick();
		c.tick(w, a, s, d);

	}
	
	public void render() {
        BufferStrategy bs = this.getBufferStrategy(); //Initialised
    	
    	if(bs == null) {
    		
    		createBufferStrategy(3);
    		return;
    		
    	}
    	
    	Graphics g = bs.getDrawGraphics(); //g draws buffers
    	/////////START DRAW//////////
    	g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, 1350, 800);
    	    	
    	p.render(g, w, s, a, d);
    	e.render(g);
    	c.render(g, w, s, a, d);
    	
    	//g.drawImage(blackout1, 0, 0, null);
   
    	/////////END DRAW///////////
    	
    	g.dispose();
    	bs.show();
    	
	}
	
	public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	
    	if(key == KeyEvent.VK_W) {
    		p.setVelY(-8);
    		w = true;
    		a = false;
    		s = false;
    		d = false;
    	}
    	else if(key == KeyEvent.VK_S) {
    		p.setVelY(8);
    		w = false;
    		a = false;
    		s = true;
    		d = false;
    	}
    	else if(key == KeyEvent.VK_A) {
    		p.setVelX(-8);
    		w = false;
    		a = true;
    		s = false;
    		d = false;
    	}
    	else if(key == KeyEvent.VK_D) {
    		p.setVelX(8);
    		w = false;
    		a = false;
    		s = false;
    		d = true;
    	}
    	else if(key == KeyEvent.VK_SPACE && !is_shooting) {
    		if(w = true){
    			a = false;
        		s = false;
        		d = false;
    		c.addEntity(new Bullet(p.getX(), p.getY() - 60, tex, this));
    		is_shooting = true;
    		
    		}
    		else if(s = true){
    			w = false;
        		a = false;
        		d = false;
    		c.addEntity(new Bullet(p.getX(), p.getY() + 25, tex, this));
    		is_shooting = true;
    		
    		}
    		else if(a = true){
    			w = false;
            	s = false;
            	d = false;
        	c.addEntity(new Bullet(p.getX() - 25, p.getY(), tex, this));
        	is_shooting = true;
        	
        	}
    		else if(d = true){
    			w = false;
            	s = false;
            	a = false;
        	c.addEntity(new Bullet(p.getX() + 25, p.getY(), tex, this));
        	is_shooting = true;
        	}
    		else {
    			s = true;
    		}
    
    	}
    	

	}
	public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
    	    	
    	if(key == KeyEvent.VK_W) {
    		p.setVelY(0);
    	}
    	else if(key == KeyEvent.VK_S) {
    		p.setVelY(0);
    	}
    	else if(key == KeyEvent.VK_A) {
    		p.setVelX(0);
    	}
    	else if(key == KeyEvent.VK_D) {
    		p.setVelX(0);
    	}
    	else if(key == KeyEvent.VK_SPACE) {
    		is_shooting = false;
    	}

	}
	
        public static void main(String args[]) {
		
    	Game game = new Game();
    	    	
    	game.setPreferredSize(new Dimension(width, height));
    	game.setMaximumSize(new Dimension(width, height));
    	game.setMinimumSize(new Dimension(width, height));
    	
    	JFrame frame = new JFrame(game.title);
    	frame.add(game);
    	frame.pack();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setLocationRelativeTo(null);
    	frame.requestFocus();
    	frame.setResizable(false);
    	frame.setVisible(true);
    	
    	game.start();
    	
	}

		public BufferedImage getSpriteSheet() {
			return spriteSheet;
		}
}


