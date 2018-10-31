package Game;

import java.awt.image.BufferedImage;

public class SpriteSheet {
		
		private BufferedImage image;
		
		public SpriteSheet(BufferedImage image) {
			
			this.image = image;
			
		}
		
		public BufferedImage grabImage(int col, int row, int width, int height) {
			
			BufferedImage img = image.getSubimage((col * 44) - 44, (row * 94) - 94, width, height);
			return img;
			
		}

	}
