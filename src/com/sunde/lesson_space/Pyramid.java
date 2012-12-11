package com.sunde.lesson_space;

import javax.microedition.khronos.opengles.GL10;

import com.sunde.glib.Mesh;

public class Pyramid extends Mesh {

	private int angle;
	
	public Pyramid() {
		float[] vertexs = new float[] {
				0f, 1f, 0f,   //0
				0f, -1f, 1f,   //1
				1f, -1f, 0f,   //2
				0f, -1f, -1f,  //3
				-1f, -1f, 0f,  //4
		};
		
		float[] colors = new float[] {
				1f, 0f, 0f, 1f,
				0f, 1f, 0f, 1f,
				0f, 0f, 1f, 1f,
				
				1f, 0f, 0f, 1f,
				0f, 0f, 1f, 1f,
				0f, 1f, 0f, 1f,
				
				1f, 0f, 0f, 1f,
				0f, 1f, 0f, 1f,
				0f, 0f, 1f, 1f,
				
				1f, 0f, 0f, 1f,
				0f, 0f, 1f, 1f,
				0f, 1f, 0f, 1f,
				
		};
		
		short[] indices = new short[] { 0, 1, 2, 0, 2, 3, 0, 3, 4, 0, 4, 1};
		
		setVertexs(vertexs);
		setColors(colors);
		setIndices(indices);
	}
	
	@Override
	protected void glModelViewChange(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glTranslatef(-3f, 0, 0);
		gl.glRotatef(angle, 0f, 1f, 0f);
		angle += 2;
	}
}
