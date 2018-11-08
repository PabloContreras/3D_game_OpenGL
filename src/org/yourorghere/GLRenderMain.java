package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel)
 * <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderMain implements GLEventListener
{
    
    Fondo fondotag = new Fondo();
    
    public void init(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);
        
        float light_ambient[] =
        {
            0.9f, 0.9f, 0.9f, 1.0f
        };
        float light_diffuse[] =
        {
            0.3f, 0.3f, 0.3f, 1.0f
        };
        float light_specular[] =
        {
            1.0f, 1.0f, 1.0f, 1.0f
        };
        float light_position[] =
        {
            1.0f, 1.5f, 1.0f, 0.0f
        };
        
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);
        
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glClearColor(0.9f, 0.9f, 0.9f, 0.9f);
        gl.glShadeModel(GL.GL_SMOOTH);
        
        fondotag.agregaInit("src/TAG/conocimiento.jpg");
    }
    
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0)
        { // avoid a divide by zero error!
            height = 1;
        }
        final float h = (float) width / (float) height;
//        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
//        gl.glLoadIdentity();
        glu.gluPerspective(48.0f, h, 0.1, 0.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
//        gl.glLoadIdentity();
    }
    
    public void display(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        
        gl.glLoadIdentity();
        fondotag.agregadispay(gl);
        
        glu.gluLookAt(1.0f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    }
    
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
    {
    }
}
