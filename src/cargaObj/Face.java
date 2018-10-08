/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargaObj;

/**
 *
 * @author Alan
 */
public class Face {

    public Vector3f norms = new Vector3f();
    public Vector3f verts = new Vector3f();

    public Face(Vector3f vertex, Vector3f normal) {
        norms = normal;
        verts = vertex;
    }
}
