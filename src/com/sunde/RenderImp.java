package com.sunde;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.sunde.glib.Mesh;
import com.sunde.glib.MeshGroup;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class RenderImp implements Renderer {

	private MeshGroup mMeshGroup;
	
	public RenderImp() {
		mMeshGroup = new MeshGroup();
	}
	
	@Override
	public void onDrawFrame(GL10 gl) {
		// TODO Auto-generated method stub
		gl.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);
		gl.glLoadIdentity();
		mMeshGroup.draw(gl);
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		
		//1.��һ�������ǽ������˼��̫С��������ǳ�����̫����������ǳ�Զ����ʹ���Ƕ��ڹ۲�׵�巶Χ�ڵ����塣
		//2.�ڶ���������ָ����ȣ�ͶӰ��ı���
		GLU.gluPerspective(gl, 45f, ((float)width)/height, 0.1f, 100f);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		// TODO Auto-generated method stub
		//������Ӱƽ��
		gl.glShadeModel(GL10.GL_SMOOTH);
		//��ɫ����
		gl.glClearColor(0, 0, 0, 0);
		//������Ȼ���-->��ͬ�������
		gl.glClearDepthf(1f);
		//������Ȼ���
		gl.glEnable(GL10.GL_DEPTH_TEST);
		//������Ȼ�������
		gl.glDepthFunc(GL10.GL_LEQUAL);
		//͸������
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);	
	}
	
	public void addElements(Mesh child) {
		mMeshGroup.addElement(child);
	}

}
