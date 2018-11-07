/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenario;

import com.sun.opengl.util.GLUT;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLException;

public class Terreno {

    private static float[][] vertexArboles = new float[100][2];
    private static int[] rand = new int[vertexArboles.length];

    static Texture tex;

    public static void genCielo(GL gl, float translateZ) {
        Guias.set_material(gl, Escenario.color(68), Escenario.color(195), 223);

        gl.glPushMatrix();
        gl.glTranslatef(0, 0, Escenario.getCam().getPosC()[2]);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-20, 0, 0);
        gl.glVertex3f(-20, 20, 0);
        gl.glVertex3f(20, 20, 0);
        gl.glVertex3f(20, 0, 0);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public static void genPiso(GL gl) {
        try {
            tex = TextureIO.newTexture(new File("pasto.jpg"), true);
            tex.enable();
            tex.bind();
            gl.glTexEnvi(GL.GL_TEXTURE_ENV, GL.GL_TEXTURE_ENV_MODE, GL.GL_REPLACE);

            float[] pos = Escenario.getCam().getPosC();
            //Guias.set_material(gl, Escenario.color(161), Escenario.color(164), Escenario.color(165));

            gl.glPushMatrix();
            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0f, 1f);
            gl.glVertex3f(pos[0] - 20, 0, pos[2] - 20);
            gl.glTexCoord2f(1f, 1f);
            gl.glVertex3f(pos[0] + 20, 0, pos[2] - 20);
            gl.glTexCoord2f(1f, 0f);
            gl.glVertex3f(pos[0] + 20, 0, pos[2] + 20);
            gl.glTexCoord2f(0f, 0f);
            gl.glVertex3f(pos[0] - 20, 0, pos[2] + 20);
            gl.glEnd();
            tex.disable();
            gl.glPopMatrix();
        } catch (IOException ex) {
            Logger.getLogger(Terreno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GLException ex) {
            Logger.getLogger(Terreno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void genArboles(boolean nvo, GL gl) {
        int n;
        if (nvo) {
            float x = Escenario.getCam().getPosE()[0];
            float z = Escenario.getCam().getPosE()[2];
            for (int i = 0; i < vertexArboles.length; i++) {
                rand[i] = (int) (Math.random() * 2);
                n = ThreadLocalRandom.current().nextInt(-20, 20);
                if (n >= -1 && n <= 1) {
                    while (n >= -3 && n <= 3) {
                        n = ThreadLocalRandom.current().nextInt(-20, 20);
                    }
                }
                vertexArboles[i][0] = x + n;
                vertexArboles[i][1] = z + ThreadLocalRandom.current().nextInt(-20, 20);
            }
        }

        for (int i = 0; i < vertexArboles.length; i++) {
            if (rand[i] == 0) {
                Escenario.dibujaPino(gl, vertexArboles[i][0], 0, vertexArboles[i][1]);
            } else {
                Escenario.dibujaArbol(gl, vertexArboles[i][0], 0, vertexArboles[i][1]);
            }

        }
    }

    public static void dibujaSol(GLUT glut, GL gl, float traz) {
        gl.glPushMatrix();
        Guias.set_material(gl, Escenario.color(245), Escenario.color(210), Escenario.color(53));
        gl.glTranslatef(5.19999f, 3.60f, Escenario.getCam().getPosC()[2]);
        glut.glutSolidSphere(0.70, 12, 12);
        gl.glPopMatrix();
    }

}
