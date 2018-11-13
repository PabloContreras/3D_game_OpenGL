/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libro;

import java.io.FileInputStream;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Ricardo Fernandez
 */
public class Sonido {
 
    public void reproducir(boolean rep)
    {
           try{
               String sound = "src/mario-bros-jump.wav";
               if(rep == true)
               {
                    InputStream in = new FileInputStream(sound);
                    AudioStream audio = new AudioStream(in);
                    AudioPlayer.player.start(audio);
               }  
        }catch(Exception e){
            e.getMessage();
        }
    }
}

