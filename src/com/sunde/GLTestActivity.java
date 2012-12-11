package com.sunde;

import com.sunde.lesson_space.Pyramid;
import com.sunde.lesson_texture.Square;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Menu;

public class GLTestActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        RenderImp imp = new RenderImp();
        glSurfaceView.setRenderer(imp);
        imp.addElements(new Pyramid());
        Square square = new Square();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jay);
        square.loadTexture(bitmap);
        imp.addElements(square);
        
        setContentView(glSurfaceView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
