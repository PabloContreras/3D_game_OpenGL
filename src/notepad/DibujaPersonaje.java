/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import cargaObj.Face;
import cargaObj.Model;
import cargaObj.Vector3f;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author Alan
 */
public class DibujaPersonaje {

    String rta = "personajes\\pinguino\\";

    public float posX = 0;
    public float posY = 0;
    public float posZ = 0;
    boolean arriba = true;
    float alto = 0.0f;
    Thread t;
    boolean pataI;
    boolean x = false;
    boolean volar = true;

    private GLUquadric qu;

    public DibujaPersonaje() {
    }

    public void dibujaPersonaje(GL gl, boolean walk, boolean jump, boolean vuela) {

        GLU glu = new GLU();
        qu = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(qu, GLU.GLU_FILL);
        glu.gluQuadricOrientation(qu, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(qu, GLU.GLU_SMOOTH);
        Model m = null;
        try {
            if (jump) {
                if (arriba && alto <= 1) {
                    alto += 0.2f;
                    if (alto >= 1) {
                        arriba = false;
                    }
                } else if ((!arriba) && alto != 0) {
                    alto -= 0.2f;
                    if (alto == 0 || alto < 0) {
                        arriba = true;
                    }
                }

                gl.glTranslatef(0, alto, 0);
                dibujaPataIz(m, gl);
                dibujaPataDe(m, gl);
            } else if (walk) {
                if (pataI) {
                    gl.glPushMatrix();
                    gl.glTranslatef(0f, 0.1f, 0.2f);
                    gl.glRotatef(30, -100f, 0f, 0f);
                    dibujaPataIz(m, gl);
                    gl.glPopMatrix();
                    pataI = !pataI;
                    if (x) {
                        gl.glPushMatrix();
                        gl.glTranslatef(0f, -0.1f, -0.2f);
                        gl.glRotatef(-30, -100f, 0f, 0f);
                        dibujaPataDe(m, gl);
                        gl.glPopMatrix();
                    } else {
                        gl.glPushMatrix();
                        gl.glTranslatef(0f, 0.1f, 0.2f);
                        gl.glRotatef(30, -100f, 0f, 0f);
                        dibujaPataDe(m, gl);
                        gl.glPopMatrix();
                    }
                } else {
                    gl.glPushMatrix();
                    gl.glTranslatef(0f, -0.1f, -0.2f);
                    gl.glRotatef(-30, -100f, 0f, 0f);
                    dibujaPataIz(m, gl);
                    gl.glPopMatrix();

                    gl.glPushMatrix();
                    gl.glTranslatef(0f, 0.1f, 0.2f);
                    gl.glRotatef(30, -100f, 0f, 0f);
                    dibujaPataDe(m, gl);
                    gl.glPopMatrix();
                    pataI = !pataI;
                    x = true;
                }
            } else {
                alto = 0;
                arriba = true;
                dibujaPataIz(m, gl);
                dibujaPataDe(m, gl);
            }

            gl.glPushMatrix();

            if (vuela) {
                if (volar) {
                    gl.glPushMatrix();
                    gl.glRotatef(90, 0, 0, 1f);
                    gl.glTranslated(0.25, -1.33, 0);
                    dibujaManoDe(m, gl);
                    gl.glPopMatrix();

                    gl.glPushMatrix();
                    gl.glRotatef(-90, 0, 0, 1f);
                    gl.glTranslated(-0.37, -1.15, 0);
                    dibujaManoIz(m, gl);
                    gl.glPopMatrix();
                    volar = !volar;
                } else {
                    dibujaManoDe(m, gl);
                    dibujaManoIz(m, gl);
                    volar = !volar;
                }
            } else {
                dibujaManoDe(m, gl);
                dibujaManoIz(m, gl);
            }

            dibujaPico(m, gl);
            dibujaOjos(m, gl);
            dibujaCuerpo(m, gl);
            dibujaCirculo(m, gl);
            gl.glPopMatrix();
            Thread.sleep(150);
        } catch (Exception e) {
        }

    }

    public void dibujaManoIz(Model m, GL gl) throws IOException {
        //Manos
        m = cargaObj.OBJLoader.loadModel(new File(rta + "aleta_iz.obj"));

        set_material(gl, color(0), color(0), color(0));
        dibuja(m, gl);

    }

    public void dibujaManoDe(Model m, GL gl) throws IOException {
        //Manos
        m = cargaObj.OBJLoader.loadModel(new File(rta + "aleta_de.obj"));

        set_material(gl, color(0), color(0), color(0));
        dibuja(m, gl);

    }

    public void dibujaPico(Model m, GL gl) throws IOException {
        m = cargaObj.OBJLoader.loadModel(new File(rta + "pico.obj"));
        set_material(gl, color(255), color(220), color(77));
        dibuja(m, gl);
    }

    public void dibujaCuerpo(Model m, GL gl) throws IOException {
        //Cuerpo
        m = cargaObj.OBJLoader.loadModel(new File(rta + "cuerpo.obj"));
        set_material(gl, color(0), color(0), color(0));
        dibuja(m, gl);
    }

    public void dibujaOjos(Model m, GL gl) throws IOException {
        //Ojos
        m = cargaObj.OBJLoader.loadModel(new File(rta + "ojos.obj"));
        set_material(gl, color(220), color(220), color(220));
        dibuja(m, gl);
        //Ojos 2
        m = cargaObj.OBJLoader.loadModel(new File(rta + "ojos2.obj"));
        set_material(gl, color(0), color(0), color(0));
        dibuja(m, gl);
    }

    public void dibujaCirculo(Model m, GL gl) throws IOException {
        //circulo
        m = cargaObj.OBJLoader.loadModel(new File(rta + "circulo.obj"));
        set_material(gl, color(255), color(255), color(255));
        dibuja(m, gl);
    }

    public void dibujaPataDe(Model m, GL gl) throws IOException {
        //Patas
        m = cargaObj.OBJLoader.loadModel(new File(rta + "pata_de.obj"));
        set_material(gl, color(255), color(220), color(77));
        dibuja(m, gl);
    }

    public void dibujaPataIz(Model m, GL gl) throws IOException {
        //Patas

        m = cargaObj.OBJLoader.loadModel(new File(rta + "pata_iz.obj"));
        set_material(gl, color(255), color(220), color(77));
        dibuja(m, gl);

    }

    public void dibuja(Model m, GL gl) {
        gl.glBegin(GL.GL_TRIANGLES);
        for (Face face : m.faces) {
            Vector3f n1 = m.norms.get((int) face.norms.getX() - 1);
            gl.glNormal3f(n1.x + posX, n1.y + posY, n1.z + posZ);
            Vector3f v1 = m.verts.get((int) face.verts.getX() - 1);
            gl.glVertex3f(v1.x + posX, v1.y + posY, v1.z + posZ);

            Vector3f n2 = m.norms.get((int) face.norms.getY() - 1);
            gl.glNormal3f(n2.x + posX, n2.y + posY, n2.z + posZ);
            Vector3f v2 = m.verts.get((int) face.verts.getY() - 1);
            gl.glVertex3f(v2.x + posX, v2.y + posY, v2.z + posZ);

            Vector3f n3 = m.norms.get((int) face.norms.getZ() - 1);
            gl.glNormal3f(n3.x + posX, n3.y + posY, n3.z + posZ);
            Vector3f v3 = m.verts.get((int) face.verts.getZ() - 1);
            gl.glVertex3f(v3.x + posX, v3.y + posY, v3.z + posZ);
        }
        gl.glEnd();
    }

    public void set_eyes_material(GL gl) {
        float mat_ambient[] = {color(149), color(149), color(149)};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
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

    public static float c(double c) {
        return (float) (c / (double) 30);
    }

    public float color(int c) {
        return (float) ((float) c / (float) 255);
    }
}
