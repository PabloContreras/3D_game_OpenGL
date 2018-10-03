/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.Animator;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Robot;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author luisg
 */
public class Cafe2 implements GLEventListener
{

    static final double pi=3.1416;
    @Override
    public void init(GLAutoDrawable drawable)
    {
        GL gl=drawable.getGL();
        gl.setSwapInterval(1);
        gl.glClearColor(0.0f, 0.0f, 1.0f, 0.0f);
        gl.glShadeModel(GL.GL_SMOOTH);
        
    
    }

    @Override
    public void display(GLAutoDrawable drawable)
    {
         GL gl = drawable.getGL();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
          
        
 //dibuja cuerpo taza
        gl.glBegin(gl.GL_POLYGON);
            for(int i=0; i<100; i++){
               float x = (float) Math.cos(i*pi/-100);
               float y = (float) Math.sin(i*pi/-100)+1;
               gl.glVertex2f(x/5, y/3); 
               gl.glColor3f(1.0f, 1.0f, 1.0f);
            }
        gl.glEnd();  

        
       
       
       
      
        gl.glFlush();
                
         
    }
    
    
    public static void main(String[] args)
    {
        Frame frame = new Frame("Luis Gerardo Porcayo");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Cafe2());
        frame.add(canvas);
        frame.setSize(640, 480);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter()
        {

            @Override
            public void windowClosing(WindowEvent e)
            {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable()
                {

                    public void run()
                    {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
    {
        
    }
    
}
