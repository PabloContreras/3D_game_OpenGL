/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nivel4;

import Nivel3.*;
import Nivel2.*;
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
public class PreguntasN4 extends JFrame implements ActionListener
{

    JLabel esp, indic, ins, is, puntaje;
    JLabel pregunta = new JLabel();
    JButton A = new JButton(),
            B = new JButton(),
            C = new JButton();
    Container c;
    int a;
    int punt = 0, vidas = 3;
    AlgebraMain alge;
    ArrayList<Integer> preguntas = new ArrayList<Integer>();
    int totalpreg = 0;
    PuntajeF pu = new PuntajeF();
    String[][] preg =
    {
        {
            "<html>El sistema numérico que utiliza los dígitos del 0 al 7.  En informática a veces se Tiene la ventaja de que no requiere utilizar otros símbolos diferentes de los dígitos</html>", 
            "a) DECIMAL ",
            "b)	OCTAL",
            "c)	BINARIO",
            "b",
            ""
        },
        {
            "<html>El sistema numérico que sus números están representados por los 10 primeros dígitos de la numeración decimal,y el intervalo que va del número 10 al 15 están representados por las letras del alfabeto de la A a la F.</html>",
            "a)	OCTAL",
            "b)	DECIMAL",
            "c)	HEXADECIMAL",
            "c",
            ""
        },
        {
            "<html>El sistema de numeración solo tiene dos dígitos. El sistema numérico con sus dos dígitos es un sistema en base dos. Los dígitos son 0 y 1.</html>",
            "a)	HEXADECIMAL ",
            "b)	BINARIO",
            "c)	OCTAL",
            "b",
            ""
        },
        {
            "<html>El sistema de numeración es un sistema de numeración posicional en el que las cantidades se representan utilizando como base aritmética las potencias del número diez.</html>",
            "a)	BINARIO",
            "b)	HEXADECIMAL ",
            "c)	DECIMAL",
            "c",
            ""
        },
        {
            "<html>El conjunto A ? B es el conjunto que contiene todos los elementos que pertenecen o bien a A o bien a B. </html>",
            "a)	UNION",
            "b)	INTERSECCION",
            "c)	COMPLEMENTO",
            "a",
            ""
        },
        {
            "<html>Sean A un conjunto se escribe ~A, es el conjunto de todos los elementos que no pertenecen a A. </html>",
            "a)	INTERSECCION ",
            "b)	DIFERENCIA ",
            "c)	COMPLEMENTO",
            "c",
            ""
        },
        {
            "<html>Sean A y B dos conjuntos, es el conjunto de todos los elementos de A que no pertenecen a B</html>",
            "a)	COMPLEMENTO",
            "b)	DIFERENCIA ",
            "c)	INTERSECCION",
            "b",
            ""
        },
        {
            "<html>Sean A y B dos conjuntos, es el conjunto que contiene todos los elementos comunes a ambos A y B</html>",
            "a)	DIFERENCIA",
            "b)	UNION",
            "c)	INTERSECCION",
            "c",
            ""
        }
    };

    public PreguntasN4()
    {

        setTitle("Nivel 4 -Matematicas Discretas");
        setSize(500, 400);
        c = getContentPane();
        c.setBackground(Color.cyan);
        colocarComp();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //pin = new Personaje(pu);
        alge = new AlgebraMain(pu);
        alge.mainAlgebra();
    }

    public void iniciarComp()
    {
        indic = new JLabel("Selecciona una respuesta");
        indic.setFont(new Font("Kristen ITC", 0, 20));
        pregunta = new JLabel();
        pregunta.setFont(new Font("Kristen ITC", Font.CENTER_BASELINE, 12));
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
            alge.dispose();

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
                JOptionPane.showMessageDialog(null, "JUEGO COMPLETADO, FELICIDADES!!!!");
                //pin.frame.dispose();
                alge.jframe.dispose();
                this.dispose();

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
