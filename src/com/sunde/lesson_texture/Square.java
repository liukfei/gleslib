package com.sunde.lesson_texture;

import javax.microedition.khronos.opengles.GL10;

import com.sunde.glib.Mesh;

public class Square extends Mesh {

	public Square() {
		float[] rectVertexs = new float[] {
			0f, 2f, 0f, //0
			0f, 0f, 0f, //1
			2f, 0f, 0f, //2
			2f, 2f, 0f, //3
			
			2f, 2f, 0f, //4
			2f, 0f, 0f, //5
			4f, 0f, -5f,//6
			4f, 2f, -5f //7
		};
		
		float[] texturesCoordinates = new float[] {
			0f, 0f,  //0   0,0   -------  1,0
			0f, 1f,  //1    |               |
			1f, 1f,  //2   0,1   -------  1,1
			1f, 0f,  //3
			
			0f, 0f,  //0   0,0   -------  1,0
			0f, 1f,  //1    |               |
			1f, 1f,  //2   0,1   -------  1,1
			1f, 0f,  //3
		};
		
		short[] rectIndices = new short[] { 0, 1, 2, 0, 2, 3, 4, 5, 6, 4, 6, 7};
		
		setVertexs(rectVertexs);
		setIndices(rectIndices);
		setTextureCoordinates(texturesCoordinates);
	}
	
	@Override
	protected void glModelViewChange(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glTranslatef(-2, 0, 0);
	}

}
