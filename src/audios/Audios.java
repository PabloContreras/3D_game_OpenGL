/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audios;

import java.applet.AudioClip;
/**
 *
 * @author artur
 */
public class Audios {

    AudioClip cancion;
   
    public void reproducirAudio(String audio)
    {
   
        cancion = java.applet.Applet.newAudioClip(getClass().getResource("/audios/"+audio+".wav"));
        cancion.play();

    }
    public void reproducirAudioLoop(String audio)
    {
   
        cancion = java.applet.Applet.newAudioClip(getClass().getResource("/audios/"+audio+".wav"));
        cancion.loop();

    }
    public void pararAudio()
    {
        
       cancion.stop();

    }
}
