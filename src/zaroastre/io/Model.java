package zaroastre.io;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

final class Model {

	private int draw_count;
	private int v_id;
	private int t_id;
	
	public Model(float[] vertices, float[] textures_coords) {
		super();
		this.draw_count = vertices.length;
		
//		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length);
//		buffer.put(vertices);
//		buffer.flip();
		
		
		
		this.v_id = glGenBuffers();
		
		
		glBindBuffer(GL_ARRAY_BUFFER, this.v_id);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(vertices), GL_STATIC_DRAW);
		
		this.t_id = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, this.t_id);
		
		glBufferData(GL_ARRAY_BUFFER, createBuffer(textures_coords), GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public final void render() {
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		
		glBindBuffer(GL_ARRAY_BUFFER, this.v_id);
		glVertexPointer(3, GL_FLOAT, 0, 0);

		glBindBuffer(GL_ARRAY_BUFFER, this.v_id);
		glTexCoordPointer(2, GL_FLOAT, 0, 0);
		
		glDrawArrays(GL_TRIANGLES, 0, this.draw_count);
		
		glBindBuffer(GL_ARRAY_BUFFER, 0);

		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		
	}
	
	private FloatBuffer createBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

}
