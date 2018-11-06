/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenario;

import javax.media.opengl.glu.GLU;

/**
 *
 * @author Alan
 */
public class Camara {

    private float[] e;
    private float[] c;
    private float[] u;

    public Camara(float ex, float ey, float ez, float cx, float cy, float cz, float ux, float uy, float uz, GLU glu) {
        glu.gluLookAt(ex, ey, ez, cx, cy, cz, ux, uy, uz);
        e = new float[3];
        c = new float[3];
        u = new float[3];

        e[0] = ex;
        e[1] = ey;
        e[2] = ez;
        c[0] = cx;
        c[1] = cy;
        c[2] = cz;
        u[0] = ux;
        u[1] = uy;
        u[2] = uz;

    }

    public void setPosE(float ex, float ey, float ez) {
        e[0] = ex;
        e[1] = ey;
        e[2] = ez;
    }

    public void setPosC(float cx, float cy, float cz) {
        c[0] = cx;
        c[1] = cy;
        c[2] = cz;
    }

    public void setPosU(float ux, float uy, float uz) {
        u[0] = ux;
        u[1] = uy;
        u[2] = uz;
    }

    public float[] getPosE() {
        return e;
    }

    public float[] getPosC() {
        return c;
    }

    public float[] getPosU() {
        return u;
    }

}
