/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureCoords;
import com.sun.opengl.util.texture.TextureIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.media.opengl.GL;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Fernandez
 */
public class Textura {

    private File file;
    private Texture texture;
    private boolean newTexture = true;
    

    public void agregaInit()
    {
        this.file = new File("src/Personaje/Biblioteca-del-MAC-Barcelona_slider_big.jpg");
        
    }

    public void agregadisplay(GL gl)
    {
        if (newTexture)
        {
            newTexture = false;

            if (texture != null)
            {
                texture.dispose();
                texture = null;
            }

            try
            {
                System.err.println("Loading texture...");
                texture = TextureIO.newTexture(file, true);
                
                System.err.println("Texture estimated memory size = " + texture.getEstimatedMemorySize());
            } catch (IOException e)
            {
                e.printStackTrace();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                e.printStackTrace(new PrintStream(bos));
                JOptionPane.showMessageDialog(null, bos.toString(), "Error loading texture", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (texture != null)
        {
            texture.enable();
            texture.bind();
            gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);
            TextureCoords coords = texture.getImageTexCoords();

            /*gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(coords.left(), coords.bottom());
            gl.glVertex3f(5f, -2.5f, -4.5f);
            gl.glTexCoord2f(coords.right(), coords.bottom());
            gl.glVertex3f(-5f, -2.5f, -4.5f);
            gl.glTexCoord2f(coords.right(), coords.top());
            gl.glVertex3f(-5f, 4, -4.5f);
            gl.glTexCoord2f(coords.left(), coords.top());
            gl.glVertex3f(5f, 4, -4.5f);
            gl.glEnd();*/
            
//            gl.glBegin(GL.GL_QUADS);
//            gl.glTexCoord2f(coords.left(), coords.bottom());
//            gl.glVertex3f(3f, -2.5f, -7f);
//            gl.glTexCoord2f(coords.right(), coords.bottom());
//            gl.glVertex3f(-3f, -2.5f, -7f);
//            gl.glTexCoord2f(coords.right(), coords.top());
//            gl.glVertex3f(-3f, 2.5f, -7f);
//            gl.glTexCoord2f(coords.left(), coords.top());
//            gl.glVertex3f(3f, 2.5f, -7f);
//            gl.glEnd();
            
                   
//            gl.glBegin(GL.GL_QUADS);
//            gl.glTexCoord2f(coords.left(), coords.bottom());
//            gl.glVertex3f(7f, -2.5f, -10.0f);
//            gl.glTexCoord2f(coords.right(), coords.bottom());
//            gl.glVertex3f(-7f, -2.5f, -10.0f);
//            gl.glTexCoord2f(coords.right(), coords.top());
//            gl.glVertex3f(-7f, 2.5f, -10.0f);
//            gl.glTexCoord2f(coords.left(), coords.top());
//            gl.glVertex3f(7f, 2.5f, -10.0f);
//            gl.glEnd();
            
            
            
            
            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(coords.left(), coords.bottom());
            gl.glVertex3f(3f, -2.5f, -5f);
            gl.glTexCoord2f(coords.right(), coords.bottom());
            gl.glVertex3f(-3f, -2.5f, -5f);
            gl.glTexCoord2f(coords.right(), coords.top());
            gl.glVertex3f(-3f, 2.5f, -5f);
            gl.glTexCoord2f(coords.left(), coords.top());
            gl.glVertex3f(3f, 2.5f, -5f);
            gl.glEnd();
            texture.disable();

        }
    }
}

