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

    private static int mvt = 0;
    private static final float posOjo1[] = {c(2.5f), c(8), 0};
    private static final float posOjo2[] = {c(10f), c(10f), c(-1.5)};
    private static final float radioOjos[] = {c(5), c(4f), c(1), c(0.8f)};
    double matC[][] = {
        {-9.0, 0, 0},
        {-8.0, 5, 0},
        {-7.0, 7, 0},
        {-6.0, 9, 0},
        {-5.0, 9.5, 0},
        {-4.0, 10, 0},
        {-3.0, 10.2, 0},
        {-2.0, 9.7, 0},
        {-1.0, 9, 0},
        {0, 8, 0},
        {1, 6, 0},
        {2, 6, 0},
        {3, 6, 0},
        {4, 6, 0},
        {5, 6, 0},
        {6, 5.5, 0},
        {7, 5, 0},
        {8, 4.6, 0},
        {9, 2, 0},
        {8, 1, 0},
        {7, 0, 0},
        {6, -0.2, 0},
        {5, -0.2, 0},
        {4, 0, 0},
        {3, 0.2, 0},
        {2, -1, 0},
        {1, -0.8, 0},
        {0, -0.52, 0},
        {-1, -1, 0},
        {-2, -0.6, 0},
        {-3, -0.2, 0},
        {-4, 0.2, 0},
        {-5, 0, 0},
        {-6, 0.8, 0},
        {-7, 0.8, 0},
        {-8, 0.8, 0},
        {-9, 0.8, 0}
    };
    public double[][] c = {
        {-8, 0, 0},
        {-8.5, .5, 0},
        {-9, -1, 0},
        {-8.5, -5, 0},
        {-8, -9, 0},
        {-7.5, -9.5, 0},
        {-7, -10.5, 0},
        {-6.5, -11.5, 0},
        {-6, -12.5, 0},
        {-5.5, -13, 0},
        {-5, -13.5, 0},
        {-4.5, -13.5, 0},
        {-4, -13.5, 0},
        {-3, -13.5, 0},
        {-2, -13, 0},
        {-1.5, -12.5, 0},
        {-1, -12, 0},
        {-0.8, -9, 0},
        {-0.5, -8, 0},
        {-1, -8, 0},
        {-2, -7, 0},
        {-3, -6.5, 0},
        {-4, -8, 0},
        {-5, -9, 0},
        {-4, -10, 0},
        {-3, -9, 0}
    };

    private GLUquadric qu;

    public DibujaPersonaje() {
    }

    public void dibujaPersonaje(GL gl, boolean walk, boolean jump) {
        GLU glu = new GLU();
        qu = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(qu, GLU.GLU_FILL);
        glu.gluQuadricOrientation(qu, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(qu, GLU.GLU_SMOOTH);
        Model m = null;
//        dibujaOjos(gl, glu);
//        dibujaCuerpo(gl, glu);
//        dibujaCla(gl, glu);

        gl.glPushMatrix();
        //Caminando
        if (walk && mvt % 20 + 10 > 20) {
            try {
                dibujaPatas(m, gl, 'W', false);
                dibujaPatas(m, gl, ' ', true);
            } catch (IOException ex) {

            }
        } else if (walk && mvt % 20 + 10 <= 20) {
            try {
                dibujaPatas(m, gl, ' ', false);
                dibujaPatas(m, gl, 'W', true);
            } catch (IOException ex) {

            }
            //Saltando
        } else if (jump && mvt % 20 + 10 > 20) {
            try {
                dibujaPatas(m, gl, 'J', false);
                dibujaPatas(m, gl, 'J', true);
            } catch (IOException ex) {

            }
            //Normal
        } else {
            try {
                dibujaPatas(m, gl, ' ', false);
                dibujaPatas(m, gl, ' ', true);
            } catch (IOException ex) {

            }
        }
        
        try {
            dibujaManos(m, gl);
            dibujaPico(m, gl);
            dibujaOjos(m, gl);
            dibujaCuerpo(m, gl);
            dibujaCirculo(m, gl);
        } catch (IOException ex) {
            
        }
        gl.glPopMatrix();
        mvt++;

    }

    public void dibujaManos(Model m, GL gl) throws IOException {
        //Manos
        m = cargaObj.OBJLoader.loadModel(new File(rta+"manos.obj"));

        set_material(gl, color(0), color(0), color(0));
        dibuja(m, gl);

    }

    public void dibujaPico(Model m, GL gl) throws IOException {
        m = cargaObj.OBJLoader.loadModel(new File(rta+"pico.obj"));
        set_material(gl, color(255), color(220), color(77));
        dibuja(m, gl);
    }

    public void dibujaCuerpo(Model m, GL gl) throws IOException {
        //Cuerpo
        m = cargaObj.OBJLoader.loadModel(new File(rta+"cuerpo.obj"));
        set_material(gl, color(0), color(0), color(0));
        dibuja(m, gl);
    }

    public void dibujaOjos(Model m, GL gl) throws IOException {
        //Ojos
        m = cargaObj.OBJLoader.loadModel(new File(rta+"ojos.obj"));
        set_material(gl, color(220), color(220), color(220));
        dibuja(m, gl);
        //Ojos 2
        m = cargaObj.OBJLoader.loadModel(new File(rta+"ojos2.obj"));
        set_material(gl, color(0), color(0), color(0));
        dibuja(m, gl);
    }

    public void dibujaCirculo(Model m, GL gl) throws IOException {
        //circulo
        m = cargaObj.OBJLoader.loadModel(new File(rta+"circulo.obj"));
        set_material(gl, color(255), color(255), color(255));
        dibuja(m, gl);
    }

    public void dibujaPatas(Model m, GL gl, char c, boolean left) throws IOException {
        //Patas
        m = cargaObj.OBJLoader.loadModel(new File(rta+"patas.obj"));
        set_material(gl, color(255), color(220), color(77));

        if (c == 'W') {
            gl.glTranslatef(0f, -0.1f, -0.2f);
            gl.glRotatef(30, -100f, 0f, 0f);
        }
        if (c == 'J') {
            gl.glTranslatef(0f, -0.05f, -0.1f);
            if (left) {
                gl.glRotatef(30, -100f, -100f, 0f);
            } else {
                gl.glRotatef(30, -100f, 100f, 0f);
            }
        }

        dibuja(m, gl);

    }

    public void dibuja(Model m, GL gl) {
        gl.glBegin(GL.GL_TRIANGLES);
        for (Face face : m.faces) {
            Vector3f n1 = m.norms.get((int) face.norms.getX() - 1);
            gl.glNormal3f(n1.x+posX, n1.y+posY, n1.z+posZ);
            Vector3f v1 = m.verts.get((int) face.verts.getX() - 1);
            gl.glVertex3f(v1.x+posX, v1.y+posY, v1.z+posZ);

            Vector3f n2 = m.norms.get((int) face.norms.getY() - 1);
            gl.glNormal3f(n2.x+posX, n2.y+posY, n2.z+posZ);
            Vector3f v2 = m.verts.get((int) face.verts.getY() - 1);
            gl.glVertex3f(v2.x+posX, v2.y+posY, v2.z+posZ);

            Vector3f n3 = m.norms.get((int) face.norms.getZ() - 1);
            gl.glNormal3f(n3.x+posX, n3.y+posY, n3.z+posZ);
            Vector3f v3 = m.verts.get((int) face.verts.getZ() - 1);
            gl.glVertex3f(v3.x+posX, v3.y+posY, v3.z+posZ);
        }
        gl.glEnd();
    }

//    public void dibujaOjos(GL gl, GLU glu) {
//
//        //OJOS
//        set_eyes_material(gl);
//        //OJO 1
//        gl.glPushMatrix();
//        gl.glTranslatef(posOjo1[0], posOjo1[1], posOjo1[2]);
//        glu.gluSphere(qu, radioOjos[0], 20, 20);
//        gl.glPopMatrix();
//
//        //OJO 2
//        gl.glPushMatrix();
//        gl.glTranslatef(posOjo2[0], posOjo2[1], posOjo2[2]);
//        glu.gluSphere(qu, radioOjos[1], 20, 20);
//        gl.glPopMatrix();
//
//        set_material(gl, 0, 0, 0);
//        gl.glPushMatrix();
//        gl.glTranslatef(posOjo1[0], posOjo1[1], posOjo1[2] + 0.15f);
//        glu.gluSphere(qu, radioOjos[2], 20, 20);
//        gl.glPopMatrix();
//
//        gl.glPushMatrix();
//        gl.glTranslatef(posOjo2[0], posOjo2[1], posOjo2[2] + 0.13f);
//        glu.gluSphere(qu, radioOjos[3], 20, 20);
//        gl.glPopMatrix();
//
//    }
//
//    public void dibujaCuerpo(GL gl, GLU glu) {
//        set_material(gl, color(139), color(200), color(94));
//
//        gl.glBegin(GL.GL_POLYGON);
//        for (int i = 0; i < matC.length; i++) {
//            gl.glVertex3d(c(matC[i][0] * 2), c(matC[i][1]), c(matC[i][2]));
//        }
//        gl.glEnd();
//
//        gl.glBegin(GL.GL_POLYGON);
//        for (int i = 0; i < matC.length; i++) {
//            gl.glVertex3d(c(matC[i][0] * 2), c(matC[i][1]), -0.2);
//        }
//        gl.glEnd();
//
//        //LINEAS
//        set_material(gl, 0, 0, 0);
//        gl.glColor3f(0, 0, 0);
//        gl.glLineWidth(7);
//
//        gl.glBegin(GL.GL_LINE_STRIP);
//
//        for (int i = 0; i < matC.length; i++) {
//            gl.glVertex3d(c(matC[i][0] * 2), c(matC[i][1]), -0.2);
//        }
//        gl.glEnd();
//
//        gl.glBegin(GL.GL_LINE_LOOP);
//
//        for (int i = 0; i < matC.length; i++) {
//            gl.glVertex3d(c(matC[i][0] * 2), c(matC[i][1]), 0);
//        }
//        gl.glEnd();
//
//    }
//
//    public void dibujaCla(GL gl, GLU glu) {
//        set_material(gl, color(139), color(200), color(94));
//        gl.glLineWidth(10);
//        gl.glBegin(GL.GL_LINE_STRIP);
//
//        for (int i = 0; i < c.length; i++) {
//            gl.glVertex3d(c(c[i][0] * 2), c(c[i][1]), -0.1);
//        }
//        gl.glEnd();
//    }
//    
//    
//    public void dibujaPata(GL gl, GLU glu){
//        
//    }
    public double[][] pata = {
        {6.3, 0.5, 0},
        {6, -1, 0},
        {5.82, -2, 0},
        {5.7, 0, 0},
        {6, 0, 0}
    };

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
