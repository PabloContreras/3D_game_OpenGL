/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargaObj;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Alan
 */
public class Coordenadas {

    public static void main(String[] args) throws IOException {
         Model m = OBJLoader.loadModel(new File("C:\\Users\\Alan\\Desktop\\personaje\\manos.obj"));
         coordenadas(m);
    }

    public static void coordenadas(Model m) {
        String norm = "";
        String vert = "";
        for (Face face : m.faces) {
            Vector3f n1 = m.norms.get((int) face.norms.getX() - 1);
            norm += "{" + n1.x + "," + n1.y + "," + n1.z + "},";

            Vector3f v1 = m.verts.get((int) face.verts.getX() - 1);
            vert += "{" + v1.x + "," + v1.y + "," + v1.z + "},";

            Vector3f n2 = m.norms.get((int) face.norms.getY() - 1);
            norm += "{" + n2.x + "," + n2.y + "," + n2.z + "},";
            Vector3f v2 = m.verts.get((int) face.verts.getY() - 1);
            vert += "{" + v2.x + "," + v2.y + "," + v2.z + "},";

            Vector3f n3 = m.norms.get((int) face.norms.getZ() - 1);
            norm += "{" + n3.x + "," + n3.y + "," + n3.z + "},\n";
            Vector3f v3 = m.verts.get((int) face.verts.getZ() - 1);
            vert += "{" + v3.x + "," + v3.y + "," + v3.z + "},\n";
        }
        System.out.println(vert);
    }
}
