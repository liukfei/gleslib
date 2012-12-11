package com.sunde.glib;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;


public class MeshGroup extends Mesh {
	
	private List<Mesh> mChildren;
	
	public MeshGroup() {
		mChildren = new ArrayList<Mesh>();
	}
	
	public void addElement(Mesh mesh) {
		mChildren.add(mesh);
	}
	
	public void removeElement(Mesh mesh) {
		mChildren.remove(mesh);
	}
	
	public int getSize() {
		return mChildren.size();
	}
	
	@Override
	public void draw(GL10 gl) {
		// TODO Auto-generated method stub
		for (Mesh child : mChildren) {
			//其实就是三个参数：eye, center, up;
			//1. Eye: 表示我们的眼睛在哪个位置（照相机）；
			//2. 中心点：我们在看哪里（这个其实应该是视野范围的重心点）；
			//3. 表示我们是在哪个方向上，即是正立看，还是倒立看，还是在某个角度上看
			GLU.gluLookAt(gl, 0, 0, 10, 0, 0, 0, 0, 1, 0);
			child.draw(gl);
			gl.glLoadIdentity();
		}
	}

	@Override
	protected void glModelViewChange(GL10 gl) {
		// TODO Auto-generated method stub
	}
}
