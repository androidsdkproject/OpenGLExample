package com.example.android1.openglexample;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle;
    private Square mSquare;
    private float mAngle;
    private Rect mRect;
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        mTriangle = new Triangle();
        mSquare = new Square();
        mRect = new Rect();
    }
    @Override
    public void onDrawFrame(GL10 gl) {


        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);


        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();


        GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);


        mSquare.draw(gl);


        gl.glRotatef(mAngle, 0.0f, 0.0f, 1.0f);


        mTriangle.draw(gl);



        gl.glRotatef(mAngle, 0.0f, 1.0f, 1.0f);


        mRect.draw(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);


        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION);        // set matrix to projection mode
        gl.glLoadIdentity();                        // reset the matrix to its default state
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7);  // apply the projection matrix
    }


    public float getAngle() {
        return mAngle;
    }


    public void setAngle(float angle) {
        mAngle = angle;
    }
}