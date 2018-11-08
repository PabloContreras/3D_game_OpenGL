package Nivel2;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Sonido1
{

    static AudioStream audio;
    static AudioStream audio1;
    static InputStream sounds;

    public static void repAudio(String sound, int i)
    {
        try
        {
            if (i == 0)
            {
                sounds = new FileInputStream(new File(sound));
                audio = new AudioStream(sounds);
                AudioPlayer.player.start(audio);
            } else
            {
                sounds = new FileInputStream(new File(sound));
                audio1 = new AudioStream(sounds);
                AudioPlayer.player.start(audio1);
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }
//public void soundJump(String sound,int rep){
//        try{
//            if(rep==0){
//         sounds = new FileInputStream(new File("sounds/" + sound));
//                audio = new AudioStream(sounds);
//                AudioPlayer.player.start(audio);
//                AudioPlayer.player.stop();
//            }
//        }catch(Exception e){
//            e.getMessage();
//        }
//    }
//
//public void detAudio(int i) {
//        if (i == 0) {
//            AudioPlayer.player.stop(audio);
//        } else {
//            AudioPlayer.player.stop(audio1);
//        }
//    }
}
