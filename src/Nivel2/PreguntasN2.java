/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nivel2;
import Nivel3.*;
import Nivel1.*;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 */
public class PreguntasN2 extends JFrame implements ActionListener
{

    JLabel esp, indic, ins, is, puntaje;
    JLabel pregunta = new JLabel();
    JButton A = new JButton(),
            B = new JButton(),
            C = new JButton();
    Container c;
    int a;
    int punt = 0, vidas = 3;
    ninja ninja;
    ArrayList<Integer> preguntas = new ArrayList<Integer>();
    int totalpreg = 0;
    PuntajeF pu = new PuntajeF();
    String[][] preg =
    {
        {
            "<html>¿Está permitido que el nombre de una clase comience con un número?</html>",
            "<html>a)	No</html>",
            "<html>b)	Tal vez</html>",
            "<html>c)	Definitivamente sí</html>",
            "a",
            ""
        },
        {
            "<html>¿Cuál de las siguientes es una palabra reservada en Java?:</html>",
            "<html>a)  NULL</html>",
            "<html>b)  new</html>",
            "<html>c) instanceOf</html>",
            "b",
            ""
        },
        {
            "<html>¿Cuál de las siguientes instrucciones compila sin provocar un warning o un error?  </html>",
            "<html>a) char c = ”a”; </html>",
            "<html>b) byte b = 257;</html>",
            "<html>c) int i = 10;</html>",
            "c",
            "" 
        },
        {
            "<html>¿Cuál de los siguientes no es un identificador válido en Java?</html>",
            "<html>a)	$variable</html>",
            "<html>b)	#variable</html>",
            "<html>c)	variable</html>",
            "b",
            "" 
        },
        {
            "<html>Dada la declaración de las siguientes variables, indicar cuáles de ellas son correctas.\n" +
"1)	float foo = -1;<br>" +
"2)	float foo1 = 1.0; <br>" +
"3)	float foo2 = 42e1; <br>" +
"4)	float foo3 = 2.02f; <br>" +
"5)	float foo4 = 3.03d; <br>" +
"6)	float foo5 = 0x0123;</html>",
            "<html>a)	1 y 2</html>",
            "<html>b)	4 y 6</html>",
            "<html>c)	2 y 5</html>",
            "b",
            "" 
        },
        {
            "<html>Respecto a los bucles, indique cuál de las siguientes afirmaciones es falsa:</html>",
            "<html>a)	El cuerpo de un bucle for-each puede repetirse 0 o más veces.</html>",
            "<html>b)	Un bucle for-each puede aplicarse sobre cualquier clase que implemente la interfaz Iterable.</html>",
            "<html>c)	El cuerpo de un bucle while siempre se ejecuta, como mínimo, una vez.</html>",
            "c",
            "" 
        },
        {
            "<html>7. ¿Qué elementos cree que definen a un objeto?</html>",
            "<html>a)	La forma en que establece comunicación e intercambia mensajes</html>",
            "<html>b)	Sus atributos y sus métodos</html>",
            "<html>c)	Sus cardinalidad y su tipo </html>",
            "b",
            "" 
        },
        {
            "<html>Según  el  texto  de  la  bibliografía  básica  de  la  asignatura,  indique  cuál  de  las  siguientes  afirmaciones  es  correcta:  	</html>",
            "<html>a)	Los campos se conocen como variables de objeto.</html>",
            "<html>b)	El alcance de una variable define la sección de código desde donde la variable puede ser declarada.</html>",
            "<html>c)	Los constructores permiten que cada objeto sea preparado adecuadamente cuando es creado.</html>",
            "b",
            "" 
        },
     
    };

    public PreguntasN2()
    {

        setTitle("Nivel 2 -Programación Orientada a Objetos");
        setSize(500, 400);
        c = getContentPane();
        c.setBackground(Color.cyan);
        colocarComp();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ninja = new ninja(pu);
        ninja.mainNinja();
    }

    public void iniciarComp()
    {
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

    public void colocarComp()
    {
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

    public int RandomconRango(int min, int max)
    {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public int numpregunta()
    {

        a = RandomconRango(0, 7);
        if (preguntas.contains(a))
        {
            return numpregunta();
        } else
        {
            preguntas.add(a);
        }
        return a;

    }

    private void colocalpreguntas()
    {
        if (preguntas.size() >= preg.length)
        {
            this.dispose();
            ninja.dispose();

        } else
        {
            a = numpregunta();
            pregunta.setText(preg[a][0]);
            A.setText(preg[a][1]);
            B.setText(preg[a][2]);
            C.setText(preg[a][3]);
        }

    }

    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == A)
        {
            checarespueta("a");
        }
        if (ae.getSource() == B)
        {
            checarespueta("b");
        }
        if (ae.getSource() == C)
        {
            checarespueta("c");
        }
    }

    public boolean checarespueta(String respuesta)
    {
//        lup.repaint();
        boolean acierto;
        if (preg[a][4].equals(respuesta))
        {
            punt++;
            pu.setRespuestacorrecta(true);
            JOptionPane.showMessageDialog(null, "Respuesta Correcta...");
            pu.setRespuestacorrecta(false);
            acierto = true;

            if (punt == 7)
            {
                JOptionPane.showMessageDialog(null, "Has avanzado de nivel");
                ninja.frame.dispose();
                this.dispose();
                PreguntasN3 p3 = new PreguntasN3();
            }
            colocalpreguntas();
        } else
        {
            vidas--;
            acierto = false;
            pu.setRespuestaincorrecta(true);
            if (vidas == 0)
            {
                JOptionPane.showMessageDialog(null, "Perdiste, suerte para la próxima");
                System.exit(vidas);

            } else
            {

                JOptionPane.showMessageDialog(null, "Respuesta Incorrecta.. Intentalo de nuevo", "Error",
                        JOptionPane.WARNING_MESSAGE);

            }
            pu.setRespuestaincorrecta(false);
        }
        puntaje.setText("Puntos = " + punt + " Vidas = " + vidas);
        pu.setTotal(punt);
        return acierto;
    }

}
