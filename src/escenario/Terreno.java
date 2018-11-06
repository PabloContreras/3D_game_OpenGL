/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escenario;

import java.util.concurrent.ThreadLocalRandom;
import javax.media.opengl.GL;

public class Terreno {

    private static float[][] vertexArboles = new float[100][2];

    public static void genPiso(GL gl) {
        float[] pos = Escenario.getCam().getPosC();
        gl.glBindTexture(0, 0);
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(pos[0] - 20, 0, pos[2] - 20);
        gl.glVertex3f(pos[0] + 20, 0, pos[2] - 20);
        gl.glVertex3f(pos[0] + 20, 0, pos[2] + 20);
        gl.glVertex3f(pos[0] - 20, 0, pos[2] + 20);
        gl.glEnd();
    }

    public static void genArboles(boolean nvo, GL gl) {

        if (nvo) {
            float x = Escenario.getCam().getPosE()[0];
            float z = Escenario.getCam().getPosE()[2];
            for (int i = 0; i < vertexArboles.length; i++) {
                vertexArboles[i][0] = x + ThreadLocalRandom.current().nextInt(-40, 40);
                vertexArboles[i][1] = z + ThreadLocalRandom.current().nextInt(-40, 40);
            }
        }

        for (int i = 0; i < vertexArboles.length; i++) {
            Escenario.dibujaPino(gl, vertexArboles[i][0], 0, vertexArboles[i][1]);
        }
    }

}
