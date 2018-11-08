/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nivel2;

import Nivel1.*;

/**
 *
 * @author Omarb
 */
public class PuntajeF {

    static private int puntaje;

    static private int total;
     boolean respuestacorrecta;
    boolean respuestaincorrecta;

    public int getTotal() {
        return total;
    }

    public boolean isRespuestacorrecta()
    {
        return respuestacorrecta;
    }

    public void setRespuestacorrecta(boolean respuestacorrecta)
    {
        this.respuestacorrecta = respuestacorrecta;
    }

    public boolean isRespuestaincorrecta()
    {
        return respuestaincorrecta;
    }

    public void setRespuestaincorrecta(boolean respuestaincorrecta)
    {
        this.respuestaincorrecta = respuestaincorrecta;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPuntaje() {
        return puntaje;
    }

    /**
     * @param puntaje the puntaje to set
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

}
