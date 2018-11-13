/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro;

/**
 
 * @author Ricardo Fernandez
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.event.WindowAdapter;
import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureCoords;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import java.awt.Frame;
import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import javax.swing.JOptionPane;
import javax.media.opengl.GLCanvas;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class AlgebraMain extends JFrame implements GLEventListener, KeyListener, MouseListener, MouseMotionListener
{
    
    AudioStream audio;
    InputStream sounds;
    boolean jump = false;
    private float view_rotx = 0.01f;
    private float view_roty = 0.01f;
    private int oldMouseX;
    private int oldMouseY;
    boolean [] keys = new boolean [256]; //to know wich key is pressed
    private static final float X_POSITION = 0f;
    private static final float Y_POSITION = 0f;
    private static final float Z_POSITION = 0f;
    
        Texture texturaVestido;
    
    Textura textura = new Textura();
    Sonido sonido=new Sonido();
    final GLCanvas canvas = new GLCanvas();
    
    public static void main(String[] args)
    {
        Frame frame = new Frame("MRS.ALGEBRA (Press J to jump)");
        GLCanvas canvas = new GLCanvas();
        canvas.addGLEventListener(new AlgebraMain());
        frame.add(canvas);
        frame.setSize(600,600);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
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
    public void init(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        gl.setSwapInterval(1);
        //set up lighting
        float light_ambient[]= {0.9f, 0.9f, 0.9f, 1.0f};
        float light_diffuse[]= {0.3f, 0.3f, 0.3f, 1.0f};
        float ligth_specular[]= {1.0f, 1.0f, 1.0f, 1.0f};
        float ligth_position[]= {1.0f, 1.5f, 1.0f, 0.0f};
        
        try{
            texturaVestido = TextureIO.newTexture (new File("src/Personaje/Biblioteca-del-MAC-Barcelona_slider_big.jpg"), true);
        }catch (IOException e)
        {
            System.err.print("No se pudo cargar la textura." + e);
            System.exit(1);
        }
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_AMBIENT, light_ambient, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_DIFFUSE, light_diffuse, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_SPECULAR, ligth_specular, 0);
        gl.glLightfv(GL.GL_LIGHT0, GL.GL_POSITION, ligth_position, 0);
        gl.glEnable(GL.GL_LIGHTING);
        gl.glEnable(GL.GL_LIGHT0);
        gl.glEnable(GL.GL_DEPTH_TEST);
        
        gl.glClearColor(0.9f, 0.9f, 0.9f, 0.9f);
        gl.glShadeModel(GL.GL_SMOOTH);
        
        textura.agregaInit();

        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
    }

    public void display(GLAutoDrawable drawable)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        //Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        
        textura.agregadisplay(gl);
        
        glu.gluLookAt(0.1f, 0.0f, 4.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 0.0f, 1.0f
        );
        gl.glTranslatef(X_POSITION, Y_POSITION, Z_POSITION);
        gl.glRotatef(view_rotx, 1.0f, 0.0f, 0.0f);
        gl.glRotatef(view_roty, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(90, 0.0f, 0.0f, 1.0f);
        
        //we draw Stan in the window
        MrsAlgebra stan = new MrsAlgebra ();
        stan.draw_stan (gl, keys['J']);
        //Flush all drawing operations to the graphics card
        
        
        gl.glFlush();
        
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        if (height <= 0) 
        {
            //avoid a divide by zero error!
            height = 1;
        }
        
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
    {
        
    }
    
    public void mouseClicked(MouseEvent e){}
    
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void keyTyped(KeyEvent e){}
        /*
        if (e.getKeyCode() == 87 || e.getKeyCode() == 119) {
            sonido.reproducir(true, sound);
        }
        if (e.getKeyCode() == 72 || e.getKeyCode() == 104) {
            jump = false;
        }*/
        
    
    
    public void mousePressed(MouseEvent e)
    {
        oldMouseX = e.getX();
        oldMouseY = e.getY();
    }
    
    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        Dimension size = e.getComponent().getSize();
        float thetaX = 360.0f * ((float)(x-oldMouseX)/(float)size.width);
        float thetaY = 360.0f * ((float)(oldMouseY-y)/(float)size.height);
        oldMouseX = x;
        oldMouseY = y;
        view_rotx += thetaX;
        view_roty += thetaY;
    }
    
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode()<250 && keys[e.getKeyCode()]== false )
        {
              keys['W'] = false;
            keys[e.getKeyCode()] = true;
        } else {
            keys[e.getKeyCode()] = false;
        }
        System.out.println("key press " + e.getKeyChar());
    }
    @Override
     public void keyReleased(KeyEvent e) {      
        if (e.getKeyCode() == 74 || e.getKeyCode() == 104) {
            
            jump = false;
            repAudio("salta");
            if (e.getKeyCode()<250 && keys[e.getKeyCode()]== false )
        {
            keys ['W'] = false;
            if (e.getKeyChar() == 'W')
            {
                sonido.reproducir(true);
            }
            keys ['J'] = false;
            if (e.getKeyChar() == 'J')
            {
                sonido.reproducir(true);
            }
                
            keys [e.getKeyCode()] = true;
        } else 
            keys [e.getKeyCode()] = false;
        System.out.println("key press " + e.getKeyChar());
        }
    }
                    
        
        public void repAudio(String salta) {
        try {
            if (audio != null) {
                AudioPlayer.player.stop(audio);
            }
            sounds = new FileInputStream(new File("sound/" + salta + ".wav"));
            audio = new AudioStream(sounds);
            AudioPlayer.player.start(audio);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }

    public void detAudio() {
        if (audio != null) {
            AudioPlayer.player.stop(audio);
        }
    }
}


        
    
    

