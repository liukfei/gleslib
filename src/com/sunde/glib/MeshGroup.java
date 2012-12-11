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
			//��ʵ��������������eye, center, up;
			//1. Eye: ��ʾ���ǵ��۾����ĸ�λ�ã����������
			//2. ���ĵ㣺�����ڿ���������ʵӦ������Ұ��Χ�����ĵ㣩��
			//3. ��ʾ���������ĸ������ϣ����������������ǵ�������������ĳ���Ƕ��Ͽ�
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
