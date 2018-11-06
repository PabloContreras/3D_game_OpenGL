/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenario;

import javax.media.opengl.GL;

/**
 *
 * @author Alan
 */
public class Guias {

    public static void ejes(GL gl) {
        // EJES X, Y, Z
        gl.glBegin(GL.GL_LINES);
        set_material(gl, 1, 0, 0);
        gl.glVertex3d(-10, 0, 0);
        gl.glVertex3d(10, 0, 0);
        set_material(gl, 0, 1, 0);
        gl.glVertex3d(0, -10, 0);
        gl.glVertex3d(0, 10, 0);
        set_material(gl, 0, 0, 1);
        gl.glVertex3d(0, 0, -10);
        gl.glVertex3d(0, 0, 10);
        gl.glEnd();
    }
    
    public static void set_material(GL gl, float r, float g, float b) {
        float mat_ambient[] = {r, g, b};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
    }

}
