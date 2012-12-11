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
		
		//1.第一个参数是焦距的意思，太小则离物体非常近，太大则离物体非常远。其使用是对在观察椎体范围内的物体。
		//2.第二个参数是指长宽比，投影面的比例
		GLU.gluPerspective(gl, 45f, ((float)width)/height, 0.1f, 100f);
		
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
		// TODO Auto-generated method stub
		//启用阴影平滑
		gl.glShadeModel(GL10.GL_SMOOTH);
		//黑色清屏
		gl.glClearColor(0, 0, 0, 0);
		//设置深度缓存-->不同层的物体
		gl.glClearDepthf(1f);
		//启用深度缓存
		gl.glEnable(GL10.GL_DEPTH_TEST);
		//设置深度缓存类型
		gl.glDepthFunc(GL10.GL_LEQUAL);
		//透视修正
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);	
	}
	
	public void addElements(Mesh child) {
		mMeshGroup.addElement(child);
	}

}
