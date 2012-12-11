package com.sunde.glib;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.opengl.GLU;
import android.opengl.GLUtils;

public abstract class Mesh {

	private FloatBuffer mVertexBuffer;
	private FloatBuffer mColorBuffer;
	private FloatBuffer mTextureBuffer;
	private ShortBuffer mIndiceBuffer;
	
	private Bitmap mBitmap;
	
	private boolean mShouldLoadTexture = false;
	private boolean mHasColor = false;
	
	private int mTextureId;
	private int mIndiceCount;
	
	/**
	 * Implement it to modify the matrix of the model view.
	 */
	protected abstract void glModelViewChange(GL10 gl);
	
	public void setVertexs(float[] vertexs) {
		mVertexBuffer = initFloatBuffer(vertexs);
	}
	
	public void setColors(float[] colors) {
		mColorBuffer = initFloatBuffer(colors);
		mHasColor = true;
	}
	
	public void setTextureCoordinates(float[] textures) {
		mTextureBuffer = initFloatBuffer(textures);
	}
	
	public void loadTexture(Bitmap bitmap) {
		mBitmap = bitmap;
		mShouldLoadTexture = true;
	}
	
	public void setIndices(short[] indices) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(indices.length * 4);
		buffer.order(ByteOrder.nativeOrder());
		mIndiceBuffer = buffer.asShortBuffer();
		mIndiceBuffer.put(indices);
		mIndiceBuffer.position(0);
		mIndiceCount = indices.length;
	}
	
	/**
	 * Draw the mesh, must call glLoadIndentiy first
	 * @param gl
	 */
	public void draw(GL10 gl) {
		if (mShouldLoadTexture) {
			setTexture(gl);
			mShouldLoadTexture = false;
		}
		
		gl.glFrontFace(GL10.GL_CCW);
		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE);
		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK);
		
		glModelViewChange(gl);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
		
		if (mHasColor) {
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
			gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);
		}
		
		if (-1 != mTextureId && null != mTextureBuffer) {
			gl.glEnable(GL10.GL_TEXTURE_2D);
			gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
		}
		
		gl.glDrawElements(GL10.GL_TRIANGLES, mIndiceCount, GL10.GL_UNSIGNED_SHORT, mIndiceBuffer);
		
		if (-1 != mTextureId && null != mTextureBuffer) {
			gl.glDisable(GL10.GL_TEXTURE_2D);
			gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		}
		
		if (mHasColor) {
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		}
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
	
	protected void setTexture(GL10 gl) {
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);
		mTextureId = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureId);
		//图片过大时，多个像素对应一个纹理素
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, 
				GL10.GL_TEXTURE_MAG_FILTER, 
				GL10.GL_LINEAR);
		//图片过小时，1个像素对应多个纹理素
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, 
				GL10.GL_TEXTURE_MIN_FILTER, 
				GL10.GL_LINEAR);
		//在横向上铺不满就重复
		gl.glTexParameterf(GL10.GL_TEXTURE_2D,
				GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_REPEAT);
		//在纵向上铺不满就重复
		gl.glTexParameterf(GL10.GL_TEXTURE_2D,
			GL10.GL_TEXTURE_WRAP_T,
			GL10.GL_REPEAT);
		
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, mBitmap, 0);
	}
	
	private FloatBuffer initFloatBuffer(float[] arrays) {
		ByteBuffer buffer = ByteBuffer.allocateDirect(arrays.length * 4);
		buffer.order(ByteOrder.nativeOrder());
		FloatBuffer fBuffer = buffer.asFloatBuffer();
		fBuffer.put(arrays);
		fBuffer.position(0);
		return fBuffer;
	}
}
