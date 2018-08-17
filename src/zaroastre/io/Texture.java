/**
 * 
 */
package zaroastre.io;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;

/**
 * @author Zaroastre
 *
 */
final class Texture {
	
	private int id;
	private int width;
	private int height;
	


	public int getId() {
		return this.id;
	}



	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}




	/**
	 * 
	 */
	public Texture(String fileName) {
		super();
		
		BufferedImage picture;
		
		try {
			picture = ImageIO.read(new File(fileName));
			this.width = picture.getWidth();
			this.height = picture.getHeight();
			
			int[] pixel_raw = new int[this.width * this.height];
			pixel_raw = picture.getRGB(0, 0, this.width, this.height, null, 0, this.width);
			
			ByteBuffer pixels = BufferUtils.createByteBuffer(this.width * this.height * 4);
			
			for (int y = 0; y < this.height; y++) {
				for (int x = 0; x < this.width; x++) {
					int pixel = pixel_raw[x * this.width + y];
					pixels.put((byte) ((pixel >> 16) & 0xFF)); 	// Red
					pixels.put((byte) ((pixel >> 8) & 0xFF));	// Green
					pixels.put((byte) (pixel & 0xFF));	// Blue
					pixels.put((byte) ((pixel >> 24) & 0xFF));	// Alpha
				}
			}
			
			pixels.flip();
			
			this.id = glGenTextures();
			
			glBindTexture(GL_TEXTURE_2D, this.id);
			
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, this.width, this.height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
			
			
		} catch (Exception error) {
			error.printStackTrace();
		}
	}
	
	public final void bind() {
		glBindTexture(GL_TEXTURE_2D, this.id);
	}

}
