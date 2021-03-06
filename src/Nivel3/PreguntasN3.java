/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nivel3;

import Nivel4.PreguntasN4;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 */
public class PreguntasN3 extends JFrame implements ActionListener {

    JLabel esp, indic, ins, is, puntaje;
    JLabel pregunta = new JLabel();
    JButton A = new JButton(),
            B = new JButton(),
            C = new JButton();
    Container c;
    int a;
    int punt = 0, vidas = 3;
    Personaje pin;
    ArrayList<Integer> preguntas = new ArrayList<Integer>();
    int totalpreg = 0;
    PuntajeF pu = new PuntajeF();
        final Nivel4.Sonido sonido=new Nivel4.Sonido();
    AudioStream audio;
    InputStream sounds;
    String[][] preg
            = {
                {
                    "<html>Encapsulamiento</html>",
                    "<html>a.	Mecanismo que agrupa el c�digo en niveles de seguridad</html>",
                    "<html>b.	Define un m�todo</html>",
                    "<html>c.	Construye un objeto</html>",
                    "a",
                    ""
                },
                {
                    "<html>Polimorfismo</html>",
                    "<html>a.	Permite poder usar un nombre para especificar una clase general de acciones.</html>",
                    "<html>b.	Mecanismo que agrupa el c�digo en niveles de seguridad</html>",
                    "<html>c.	Define un m�todo</html>",
                    "a",
                    ""
                },
                {
                    "<html>Herencia</html>",
                    "<html>a.	Un objeto puede adquirir las propiedades de otro.</html>",
                    "<html>b.	Un objeto adquiere propiedades de m�s de un objeto.</html>",
                    "<html>c.	Construye un objeto</html>",
                    "a",
                    ""
                },
                {
                    "<html>�Qu� significa LIFO?</html>",
                    "<html>a.	Last in First out</html>",
                    "<html>b.	First in first out</html>",
                    "<html>c.	Cola</html>",
                    "a",
                    ""
                },
                {
                    "<html>�Qu� significa FIFO?</html>",
                    "<html>a.	First in first out</html>",
                    "<html>b.	Last in First out</html>",
                    "<html>c.	Cola</html>",
                    "a",
                    ""
                },
                {
                    "<html>Es un ejemplo de LIFO</html>",
                    "<html>a.	Cola</html>",
                    "<html>b.	Pila</html>",
                    "<html>c.	�rbol</html>",
                    "b",
                    ""
                },
                {
                    "<html>Es un ejemplo de FIFO</html>",
                    "<html>a.	Cola</html>",
                    "<html>b.	Pila</html>",
                    "<html>c.	�rbol </html>",
                    "a",
                    ""
                },
                {
                    "<html>Es una estructura de datos lineal </html>",
                    "<html>a.	Pila</html>",
                    "<html>b.	�rbol</html>",
                    "<html>c.	Grafo</html>",
                    "a",
                    ""
                },
                {
                    "<html>Es una estructura de datos no lineal</html>",
                    "<html>a.	Pila</html>",
                    "<html>b.	�rbol</html>",
                    "<html>c.	Cola</html>",
                    "b",
                    ""
                },};

    public PreguntasN3() {

        setTitle("Nivel 3 -Estructura de Datos");
        setSize(500, 400);
        c = getContentPane();
        c.setBackground(Color.cyan);
        colocarComp();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pin = new Personaje(pu);
        pin.mainBooky();
    }

    public void iniciarComp() {
        indic = new JLabel("Selecciona una respuesta");
        indic.setFont(new Font("Kristen ITC", 0, 20));
        pregunta = new JLabel();
        pregunta.setFont(new Font("Kristen ITC", Font.CENTER_BASELINE, 14));
        puntaje = new JLabel("Puntos = 0 Vidas = 3");
        pregunta.setForeground(Color.DARK_GRAY);
        A.setFont(new Font("Kristen ITC", 0, 12));
        B.setFont(new Font("Kristen ITC", 0, 12));
        C.setFont(new Font("Kristen ITC", 0, 12));

        A.addActionListener(this);
        B.addActionListener(this);
        C.addActionListener(this);
    }

    public void colocarComp() {
        iniciarComp();
        colocalpreguntas();
        c.setLayout(null);
        indic.setBounds(20, 20, 350, 20);
        c.add(indic);
        pregunta.setBounds(20, 40, 450, 80);
        c.add(pregunta);
        puntaje.setBounds(20, 300, 280, 50);
        c.add(puntaje);
        A.setBounds(20, 130, 450, 50);
        c.add(A);
        B.setBounds(20, 190, 450, 50);
        c.add(B);
        C.setBounds(20, 250, 450, 50);
        c.add(C);

    }

    public int RandomconRango(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public int numpregunta() {

        a = RandomconRango(0, 8);
        if (preguntas.contains(a)) {
            return numpregunta();
        } else {
            preguntas.add(a);
        }
        return a;

    }

    private void colocalpreguntas() {
        if (preguntas.size() >= preg.length) {
            this.dispose();
            pin.dispose();

        } else {
            a = numpregunta();
            pregunta.setText(preg[a][0]);
            A.setText(preg[a][1]);
            B.setText(preg[a][2]);
            C.setText(preg[a][3]);
        }

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == A) {
            checarespueta("a");
        }
        if (ae.getSource() == B) {
            checarespueta("b");
        }
        if (ae.getSource() == C) {
            checarespueta("c");
        }
    }

    public boolean checarespueta(String respuesta) {
//        lup.repaint();
        boolean acierto;
        if (preg[a][4].equals(respuesta)) {
            DibujaPersonaje.iniciaNo = false;
            punt++;
            pu.setRespuestacorrecta(true);
            repAudio("correcto");
            JOptionPane.showMessageDialog(null, "Respuesta Correcta...");
            pu.setRespuestacorrecta(false);
            acierto = true;

            if (punt == 7) {
                repAudio("winner");
                JOptionPane.showMessageDialog(null, "Has avanzado de nivel");
                pin.frame.dispose();
                this.dispose();
                PreguntasN4 p4 = new PreguntasN4();

            }
            colocalpreguntas();
        } else {
            vidas--;
            acierto = false;
            pu.setRespuestaincorrecta(true);
            if (vidas == 0) {
                repAudio("juego_terminado");
                JOptionPane.showMessageDialog(null, "Perdiste, suerte para la pr�xima");
                System.exit(vidas);

            } else {
                repAudio("error");
                DibujaPersonaje.iniciaNo = true;
                JOptionPane.showMessageDialog(null, "Respuesta Incorrecta.. Intentalo de nuevo", "Error",
                        JOptionPane.WARNING_MESSAGE);

            }
            pu.setRespuestaincorrecta(false);
        }
        puntaje.setText("Puntos = " + punt + " Vidas = " + vidas);
        pu.setTotal(punt);
        return acierto;
    }
    
     private void repAudio(String correcto) {
        try {
            if (audio != null) {
                AudioPlayer.player.stop(audio);
            }
            sounds = new FileInputStream(new File("src/audios/" + sonido + ".wav"));
            audio = new AudioStream(sounds);
            AudioPlayer.player.start(audio);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
    }


}
