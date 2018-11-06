/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenario;

import cargaObj.Face;
import cargaObj.Model;
import cargaObj.OBJLoader;
import cargaObj.Vector3f;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 *
 * @author Alan
 */
public class Escenario extends JFrame implements GLEventListener,
        KeyListener, MouseListener, MouseMotionListener {

    private float[] e = {0.0f, 3f, -12.0f};
    private float[] c = {0.0f, 0.0f, 0.0f};
    private float[] u = {0.0f, 0.0f, 3.0f};
    private static Camara cam;

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
        Frame frame = new Frame("Escenario");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new Escenario());
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
    GLU glu = new GLU();

    static Model pino1;
    static Model pino2;

    public void init(GLAutoDrawable drawable) {

        try {
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

            pino1 = OBJLoader.loadModel(new File("personajes\\pino\\pinoa.obj"));
            pino2 = OBJLoader.loadModel(new File("personajes\\pino\\tronco.obj"));
        } catch (IOException ex) {
            Logger.getLogger(Escenario.class.getName()).log(Level.SEVERE, null, ex);
        }

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
    GL gl;

    public void display(GLAutoDrawable drawable) {

        gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        cam = new Camara(e[0], e[1], e[2], c[0], c[1], c[2], u[0], u[1], u[2], glu);
        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
//        gl.glRotatef(90, 0.0f, 0.0f, 1.0f);

        // Move the whole scene
        Guias.ejes(gl);
        dibujaPino(gl, 2, 0, 0);
        dibujaPino(gl, -2, 0, -2.5);

        Terreno.genArboles(pinos, gl);
        pinos = false;
        Terreno.genPiso(gl);
        gl.glFlush();

    }

    int i = 0;
    boolean pinos = true;

    public static void dibujaPino(GL gl, double tx, double ty, double tz) {

        try {
            gl.glPushMatrix();
            gl.glTranslated(tx, ty, tz);
            Guias.set_material(gl, color(7), color(111), color(10));
            dibuja(pino1, gl);
            Guias.set_material(gl, color(111), color(59), color(7));
            dibuja(pino2, gl);
            gl.glPopMatrix();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dibuja(Model m, GL gl) {
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
        if (i==100) {
            pinos=true;
            i=0;
        }
        if (e.getKeyChar() == 'w') {
            this.e[2] += 0.2;
            this.c[2] += 0.2;
        } else if (e.getKeyChar() == 's') {
            this.e[2] -= 0.2;
            this.c[2] -= 0.2;
        } else if (e.getKeyChar() == 'a') {
            this.e[0] += 0.2;
            this.c[0] += 0.2;
        } else if (e.getKeyChar() == 'd') {
            this.e[0] -= 0.2;
            this.c[0] -= 0.2;
        }
        i++;
        
    }

    public static float color(int c) {
        return (float) ((float) c / (float) 255);
    }

    public static Camara getCam() {
        return cam;
    }

}
