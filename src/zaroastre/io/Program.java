package zaroastre.io;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

/**
 * 
 * @author Zaroastre
 *
 */
final class Program {
	
	// https://www.youtube.com/watch?v=7NsXcedg5fo&list=PLILiqflMilIxta2xKk2EftiRHD4nQGW0u&index=6
	private static float positionX = 0F, positionY = 0F;
	
	private static float color_red = 0F;
	private static float color_green = 0F;
	private static float color_blue = 0F;

	/**
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		
		
		// On essaie d'initialiser le rendu 3D.
		if (!glfwInit()) {
			throw new IllegalStateException("Impossible d'initialiser GLFW.");
		}

		// On définit l'état de la fenetre.
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		// On créé une fenetre de jeu.
		long window = glfwCreateWindow(640, 480, "Nemesys", 0, 0);
		
		// On vérifie que la fenetre a pu être créée.
		if (window == 0) {
			throw new IllegalStateException("Impossible de créer la fenetre.");
		}
		
		// On défini un rendu video à partir du premier moniteur.
		GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		// On définit la position de la fenetre dans l'écran.
		glfwSetWindowPos(window, (videoMode.width() - 640) / 2, (videoMode.height() - 480) / 2);
		
		// On affiche la fenetre.
		glfwShowWindow(window);
		
		// Creation d'une scene au niveau d'un contexte.
		glfwMakeContextCurrent(window);
		
		// Creation des capacites.
		GL.createCapabilities();

		glEnable(GL_TEXTURE_2D);
		
		Texture texture = new Texture("./ressources/pictures/ZaroastreGitHub.png");

		float[] vertices = new float[] {
			-0.5F, 0.5F, 0, // TOP LEFT
			0.5f, 0.5f, 0, // TOP RIGHT
			0.5f, -0.5f, 0, // BOTTOM RIGHT
			
			0.5f, -0.5f, 0, // BOTTOM RIGHT
			-0.5f, -0.5f, 0, // BOTTOM LEFT
			-0.5F, 0.5F, 0 // TOP LEFT
		};
		
		float[] textures = new float[] {
				0, 0,
				1,0,
				1,1,
				
				1,1,
				0,1,
				0,0
		};
		
		Model model = new Model(vertices, textures);
		
		
		// Tant que la fenetre ne doit pas être fermée...
		while (!glfwWindowShouldClose(window)) {			
			glfwPollEvents();
			// Initialisation du gestionnaire d'éevenements.
			analyzeInputEvents(window);
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			texture.bind();
			
			model.render();
			
			// Afficher une texture.
//			glBegin(GL_QUADS);
//				glColor4f(1, 0, 0, 0);
//				glTexCoord2f(0, 0);
//				glVertex2d(-0.5f + positionX, 0.5f + positionY);
//				glTexCoord2f(0, 1);
//				glVertex2d(0.5f + positionX, 0.5f + positionY);
//				glTexCoord2f(1, 1);
//				glVertex2d(0.5f + positionX, -0.5f + positionY);
//				glTexCoord2f(1, 0);
//				glVertex2d(-0.5f + positionX, -0.5f + positionY);			
//			glEnd();
			
			glfwSwapBuffers(window);
		}
		
		// On arrete le rendu.
		glfwTerminate();
		
	}

	
	private final static void analyzeInputEvents(long window) {
		if ((glfwGetKey(window, GLFW_KEY_D) == GL_TRUE) || (glfwGetKey(window, GLFW_KEY_RIGHT) == GL_TRUE)) {
			positionX += 0.01F;
		} else if ((glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) || (glfwGetKey(window, GLFW_KEY_LEFT) == GL_TRUE)) {
			positionX -= 0.01F;
		}
		if ((glfwGetKey(window, GLFW_KEY_W) == GL_TRUE) || (glfwGetKey(window, GLFW_KEY_UP) == GL_TRUE)){
			positionY += 0.01F;
		} else if ((glfwGetKey(window, GLFW_KEY_S) == GL_TRUE) || (glfwGetKey(window, GLFW_KEY_DOWN) == GL_TRUE)){
			positionY -= 0.01F;
		}
		if (glfwGetMouseButton(window, 0) == GL_TRUE) {
			System.out.println("Click détecté.");
		}
		if (glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
			glfwSetWindowShouldClose(window, true);
		}
	}

}
