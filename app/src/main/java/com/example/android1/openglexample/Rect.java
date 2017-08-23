package com.example.android1.openglexample;

/**
 * Created by Kamal Verma on 23-August-2017
 * Kamalverma1207@gmail.com
 * Precise Automation and Robotics
 **/


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Rect {

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float squareCoords[] = {
            -0.5f, 0.2f, 1.0f,   // top left
            -0.5f, -0.2f, 1.0f,   // bottom left
            0.5f, -0.2f, 1.0f,   // bottom right
            0.5f, 0.2f, 1.0f}; // top right
    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private final short drawOrder[] = {0, 1, 2, 0, 2, 3}; // order to draw vertices

    float color[] = {0.2f, 0.909803922f, 0.898039216f, 1.0f};


    public Rect() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
    }


    public void draw(GL10 gl) {
        // Since this shape uses vertex arrays, enable them
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // draw the shape
        gl.glColor4f(       // set color
                color[0], color[1],
                color[2], color[3]);
        gl.glVertexPointer( // point to vertex data:
                COORDS_PER_VERTEX,
                GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glDrawElements(  // draw shape:
                GL10.GL_TRIANGLES,
                drawOrder.length, GL10.GL_UNSIGNED_SHORT,
                drawListBuffer);

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
}