/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import com.sun.opengl.util.Animator;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import cargaObj.*;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;

/**
 *
 * @author alanm
 */
public class PersonajeAlan extends JFrame implements GLEventListener,
        KeyListener, MouseListener, MouseMotionListener {

    private float view_rotx = 0.01f;
    private float view_roty = 0.01f;
    private int oldMouseX;
    private int oldMouseY;
    boolean[] keys = new boolean[256]; //to know which key is pressed

    //position of stan in the window
    private static final float X_POSITION = 0f;
    private static final float Y_POSITION = 0f;
    private static final float Z_POSITION = -2f;

    public static void main(String[] args) {
        Frame frame = new Frame("Alan : Stan (Press J to jump and press W to walk)");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new PersonajeAlan());
        frame.add(canvas);
        frame.setSize(600, 600);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();

    }

    public void init(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);
        // set up lighting
        float light_ambient[] = {0.9f, 0.9f, 0.9f, 1.0f};
        float light_diffuse[] = {0.3f, 0.3f, 0.3f, 1.0f};
        float light_specular[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float light_position[] = {1.0f, 1.5f, 1.0f, 0.0f};
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, light_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, light_position, 0);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        // Setup the drawing area and shading mode
        gl.glClearColor(0.9f, 0.9f, 0.9f, 0.9f);
        gl.glShadeModel(GL.GL_SMOOTH);
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0) { // avoid a divide by zero error!
            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    public void display(GLAutoDrawable drawable) {

        GL gl = drawable.getGL();
        GLU glu = new GLU();
        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();
//        glu.gluLookAt(0.1f, 0.0f, 4.0f,// eye
//                0.0f, 0.0f, 0.0f, // looking at
//                0.0f, 0.0f, 1.0f // is up
//        );
        glu.gluLookAt(0.1f, 0.0f, 4.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        // Move the whole scene

        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
        //we draw Stan in the window
//        Notepad note = new Notepad();
//        note.draw_stan(gl, keys['W'], keys['J']);
        try {
            Texture tex = TextureIO.newTexture(new File("fondoP.jpg"), true);
            tex.enable();
            tex.bind();
            gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);

            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(-5f, -3.5f, -1f);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(5f, -3.5f, -1f);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(5f, 3.5f, -1f);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(-5f, 3.5f, -1f);
            gl.glEnd();
            tex.disable();
        } catch (Exception e) {

        }

        DibujaPersonaje p = new DibujaPersonaje();
        p.posZ = 0.5f;
        p.posY = -1.5f;
        p.posX = 0.5f;
        p.dibujaPersonaje(gl, keys['W'], keys['J']);

// Flush all drawing operations to the graphics card
        gl.glFlush();

    }

    public void dibuja(Model m, GL gl) {
        gl.glBegin(GL.GL_TRIANGLES);
        for (Face face : m.faces) {
            Vector3f n1 = m.norms.get((int) face.norms.getX() - 1);
            gl.glNormal3f(n1.x, n1.y, n1.z);
            Vector3f v1 = m.verts.get((int) face.verts.getX() - 1);
            gl.glVertex3f(v1.x, v1.y, v1.z);

            Vector3f n2 = m.norms.get((int) face.norms.getY() - 1);
            gl.glNormal3f(n2.x, n2.y, n2.z);
            Vector3f v2 = m.verts.get((int) face.verts.getY() - 1);
            gl.glVertex3f(v2.x, v2.y, v2.z);

            Vector3f n3 = m.norms.get((int) face.norms.getZ() - 1);
            gl.glNormal3f(n3.x, n3.y, n3.z);
            Vector3f v3 = m.verts.get((int) face.verts.getZ() - 1);
            gl.glVertex3f(v3.x, v3.y, v3.z);
        }
        gl.glEnd();
    }

    public static float c(float c) {
        return (c / 1);
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
    }

    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaX = 360.0f * ((float) (x - oldMouseX) / (float) size.width);
        float thetaY = 360.0f * ((float) (oldMouseY - y) / (float) size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 250 && keys[e.getKeyCode()] == false) {
            keys['W'] = false;
            keys['J'] = false;
            keys[e.getKeyCode()] = true;
        } else {
            keys[e.getKeyCode()] = false;
        }
        System.out.println("key press " + e.getKeyChar());
    }

    public void set_material(GL gl, float r, float g, float b) {
        float mat_ambient[] = {r, g, b};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
    }

    public float color(int c) {
        return (float) ((float) c / (float) 255);
    }
}
