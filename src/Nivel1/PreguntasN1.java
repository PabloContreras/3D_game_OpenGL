/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nivel1;

import Nivel2.PreguntasN2;
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
import org.yourorghere.Main;

/**
 *
 */
public class PreguntasN1 extends JFrame implements ActionListener
{

    JLabel esp, indic, ins, is, puntaje;
    JLabel pregunta = new JLabel();
    JButton A = new JButton(),
            B = new JButton(),
            C = new JButton();
    Container c;
    int a;
    int punt = 0, vidas = 3;
    DibujaCafe cafe;
    ArrayList<Integer> preguntas = new ArrayList<Integer>();
    int totalpreg = 0;
    PuntajeF pu = new PuntajeF();
    String[][] preg =
    {
        {
            "Un computador es: ", 
            "a. Una máquina electrónica que procesa información",
            "b. Un equipo de oficina ",
            "c. Una organización numérica",
            "a", ""
        },
        {
            "Un sistema operativo es:",
            " a. Un procesador de texto ", 
            "b. El software básico del computador ",
            "b. El software básico del computador ","b", ""
        },
        {
            "Los dispositivos de entrada permiten: ", 
            "a. Almacenar datos en el computador ",
            "b. Desplegar información almacenada en el equipo",
            "c. Ingresar datos al computador ", "c", ""
        },
        {
            "Los dispositivos de salida permiten: ",
            "a. Guardar datos en el computador ",
            "b. Desplegar información almacenada en el equipo ",
            "c. Ingresar datos al computador ",
                "b", ""
        },
        {
            "Un algoritmo es: ", 
            "a. Un conjunto de operaciones que se usan para resolver un problema específico. ", 
            "b. Un conjunto de programas informáticos ",
            "c. Un conjunto de software libre ", "a", ""
        },
        {
            "La prueba de escritorios se usa para: ", 
            "a. Programar órdenes ",
            "b. Verificar si el algoritmo está correcto ",
            "c. Eliminar virus informáticos ", "b", ""
        },
        {
            "Una variable es:", 
            "a. Un lugar de almacenamiento, cuyo contenido podrá variar durante el proceso y finalmente se obtendrán los resultados con los datos contenidos en ellas. ",
            "b. Un lugar de almacenamiento, cuyo contenido no varía durante el proceso y finalmente se obtendrán los resultados con los datos contenidos en ellas. ",
            "c. Ninguna de las anteriores ","a",""

        },
        {
            "Un operador es: ",
            "a. Un lugar de almacenamiento de datos ",
            "b. Un símbolo especial que indica la compilador que debe efectuar una operación matemática o lógica ",
            "c. Una variable ","b",""
        },
        {
            "Los operadores relacionales se utilizan siempre en: ",
            "a. Operaciones de comparación ",
            "b. Operaciones de suma y resta ",
            "c. Operaciones de multiplicación y división ","a",""

        },
        {
            "Una estructura secuencial es aquella que ejecuta: ",
            "a. Una evaluación de una expresión y, dependiendo del resultado, se decide la siguiente sentencia a ejecutar. ",
            "b. Una sentencia detrás de otra ",
            "c. Una repetición de un bloque de sentencias, mientras sea verdadera una determinada condición ",
            "b",""

        }
    };

    public PreguntasN1()
    {

        setTitle("Nivel 1 -Fundamentos de Prpgramacion");
        setSize(500, 400);
        c = getContentPane();
        c.setBackground(Color.yellow);
        colocarComp();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cafe = new DibujaCafe(pu);
        cafe.mainCafe();
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
        B.setFont(new Font("Kristen ITC", 0,12));
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
        puntaje.setBounds(20, 250, 280, 50);
        c.add(puntaje);
        A.setBounds(20, 130, 450, 30);
        c.add(A);
        B.setBounds(20, 170, 450, 30);
        c.add(B);
        C.setBounds(20, 210, 450, 30);
        c.add(C);

    }

    public int RandomconRango(int min, int max)
    {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public int numpregunta()
    {

        a = RandomconRango(0, 9);
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
            cafe.jframe.dispose();

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
        boolean acierto;
        if (preg[a][4].equals(respuesta))
        {
            punt++;
            pu.setRespuestacorrecta(true);
            JOptionPane.showMessageDialog(null, "Respuesta Correcta...");
            pu.setRespuestacorrecta(false);
            acierto = true;
           // cafe.setAlwaysOnTop(true);
            if (punt == 7)
            {
                JOptionPane.showMessageDialog(null, "Has avanzado de nivel");
                this.dispose();
                cafe.jframe.dispose();
                PreguntasN2 p2 = new PreguntasN2();

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
                PreguntasN1 nuevo = new PreguntasN1();
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
