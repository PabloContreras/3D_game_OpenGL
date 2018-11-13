/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nivel4;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author Ricardo Fernandez
 */
public class MrsAlgebra {
    
  private static final int SLICES = 40;
    private static final int STACKS = 40;
    private GLUquadric q = null;
    private static int mvt = 0;
    //heigth and widht of each components
    private static final float HEIGHT_BODY = 0.8f;
    private static final float TOP_BODY = 0.15f;
    private static final float BOTTOM_BODY = 0.5f;
    private static final float HEIGHT_LEGS = 0.15f;
    private static final float WIDTH_LEGS = 0.165f;
    private static final float HEIGHT_ARMS = 0.26f;
    private static final float WIDTH_ARMS = 0.12f;
    private static final float WIDTH_HEAD = 0.5f;
    private static final float WIDTH_EYES = 0.3f;
    private static final float WIDTH_HANDS = 0.11f;
    private static final float SHOES = 0.15f;
    private static final float SHOES1 = 0.15f;
    private static final float WIDTH_OPEN_MOUTH = 0.2f;
   
    private static final float WIDTH_PUPILS = 0.05f;
    final Sonido sonido=new Sonido();
    int x=0;
    PuntajeF pu;

    public MrsAlgebra(PuntajeF pu)
    {
        this.pu = pu;
    }
    
    

    
    public void draw_stan(GL gl, boolean jump)
    {
        GLU glu = new GLU();
        q = glu.gluNewQuadric();
        glu.gluQuadricDrawStyle(q, GLU.GLU_FILL);
        glu.gluQuadricOrientation(q, GLU.GLU_OUTSIDE);
        glu.gluQuadricNormals(q, GLU.GLU_SMOOTH);
        
        
      
        if(jump && mvt%20+10>20  || pu.isRespuestacorrecta()){
            gl.glTranslatef(0f, 0.35f, 0f);
           
            draw_arm_left(gl, glu, 'J');
            draw_arm_right(gl, glu, 'J');
            draw_eyes(glu,gl,true);
            draw_mouth(glu,gl);
            draw_cordon(gl);
         
        }
        
        //stan is normal
        else{
            draw_body (gl, glu);
            draw_arm_left(gl, glu, ' ');
            draw_arm_right(gl, glu, ' ');
            draw_body1(gl);
            draw_cordon(gl);
            draw_eyes(glu,gl,false);
            draw_mouth(glu,gl);
        }   
        
        mvt++;
        draw_body (gl, glu);
       draw_body1(gl);
        
    }
    
    public void box(GL gl)
    {
        gl.glBegin(GL.GL_POLYGON); /* f1: front */
        gl.glNormal3f(-1.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        
        gl.glBegin(GL.GL_POLYGON); /* f3: back */
        gl.glNormal3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON); /* f4: top */
        gl.glNormal3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON); /* f5: left */
        gl.glNormal3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 0.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 0.0f);
        gl.glVertex3f(0.0f, 1.0f, 1.0f);
        gl.glVertex3f(0.0f, 0.0f, 1.0f);
        gl.glEnd();
        
        gl.glBegin(GL.GL_POLYGON); /* f6: right */
        gl.glNormal3f(0.0f, -1.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 0.0f);
        gl.glVertex3f(1.0f, 0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 0.0f);
        gl.glEnd();
        
    }
    
    public void set_black_material(GL gl) //negro
    {
        float mat_ambient[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_diffuse[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float shine = 125.2f;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        
    }
    
    public void set_brown_material(GL gl) //negro
    {
        float mat_ambient[] = {0.6f, 0.4f, 0.2f};
        float mat_diffuse[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float mat_specular[] = {0.0f, 0.0f, 0.0f, 1.0f};
        float shine = 125.2f;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        
    }
    public void set_grey_material(GL gl) //gris
    {
        float mat_ambient[] = {0.07f, 0.07f, 0.07f, 0.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 125.2f;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        
    }
    
    public void set_purple_material(GL gl) {
        float mat_ambient[] = {0.7725f, 0.0274f, 0.2039f};
        float mat_diffuse[] = {0.4f, 0.4f, 0.4f, 1.0f};
        float mat_specular[] = {0.5f, 0.4f, 0.4f, 1.0f};
        float shine = 15.0f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }
     public void set_blue_material(GL gl) //azul
    {
        float mat_ambient[] = {0.2f, 0.2f, 0.6f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 125.2f;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        
    }
     
     public void set_eyes_material(GL gl) //ojos
    {
        float mat_ambient[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_diffuse[] = {1.0f, 1.0f, 1.0f, 1.0f};
        float mat_specular[] = {0.8f, 0.8f, 0.8f, 1.0f};
        float shine = 51.2f;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        
    }
     
     public void set_red_material(GL gl) //rojo
    {
        float mat_ambient[] = {0.8f, 0.05f, 0.15f, 0.2f};
        float mat_diffuse[] = {0.4f, 0.4f, 0.4f, 1.0f};
        float mat_specular[] = {0.7f, 0.6f, 0.6f, 1.0f};
        float shine = 15.0f;
        
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);
        
    }
     
        public void set_yellow(GL gl)
    {

        float mat_ambient[] ={ 1.0f, 1.0f, 0f, 0f};
        float mat_diffuse[]= {1.0f, 1.0f, 0.0f, 0f};
        float mat_specular[] = {1.0f, 1.0f, 0.0f, 0f};
        float shine = 125.2f;

        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_AMBIENT, mat_ambient, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_DIFFUSE, mat_diffuse, 0);
        gl.glMaterialfv(GL.GL_FRONT_AND_BACK, GL.GL_SPECULAR, mat_specular, 0);
        gl.glMaterialf(GL.GL_FRONT_AND_BACK, GL.GL_SHININESS, shine);

    }
     
        public void draw_eyes(GLU glu, GL gl,boolean jump)
    {
        set_eyes_material(gl);
        
        gl.glPushMatrix();
        gl.glTranslatef(0.125f, 0.150f, 0.320f);        
        glu.gluSphere(q, WIDTH_EYES, SLICES, STACKS);
        gl.glTranslatef(0.16f, 0.0f, 0.0f);        
        glu.gluSphere(q, WIDTH_EYES, SLICES, STACKS);
        gl.glPopMatrix();
        
        set_blue_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.125f, 0.150f, 0.613f);        
        glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
        gl.glTranslatef(0.24f, 0f, 0f);        
        glu.gluSphere(q, WIDTH_PUPILS, SLICES, STACKS);
        gl.glPopMatrix();
      
       
    }
        
    public void draw_mouth(GLU glu,GL gl)
    {
        gl.glPushMatrix();
        set_red_material(gl);
        
        gl.glTranslatef(0.290f, -0.19f, 0.450f);   
        gl.glRotatef(90f, 1f, 0f, 0f);
        gl.glScalef(-0.2f, 0.03f, 0.1f);
        box(gl);
        
        
    }    

     
   
     public void draw_body (GL gl, GLU glu){   
        //we create stan body
  
        set_brown_material(gl);
        gl.glPushMatrix();
       
        gl.glTranslatef(-0.2f, 0.5f, 0.0f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        gl.glScalef(0.80f, 0.05f, 1.0f);
        box(gl);
        gl.glPopMatrix();
         set_brown_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.2f, 0.5f, 0.4f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        gl.glScalef(0.80f, 0.05f, 1.0f);
        box(gl);
        gl.glPopMatrix();
    }
     public void draw_body1(GL gl)
    {
        set_eyes_material(gl);
        gl.glPushMatrix();
        
        gl.glTranslatef(-0.2f, 0.42f, 0.03f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        gl.glScalef(0.70f, 0.40f, 0.9f);
        box(gl);
        gl.glPopMatrix();
        
        
       
    }
      public void draw_cordon(GL gl)
    {
        gl.glPushMatrix();
        set_yellow(gl);
        gl.glTranslatef(0.4f, 0.5f, 0.45f);
        gl.glRotatef(90f, 1f, 0f, 0f);
        gl.glScalef(0.10f, 0.025f, 0.1f);
        box(gl);
        gl.glPopMatrix();
        
        
    }
    
  
     

     
     
     public void draw_arm_left (GL gl, GLU glu, char c){
        set_brown_material(gl); 
        gl.glPushMatrix();
        //we orientate axes if stan is walking or is jumping
        if (c=='J'){
            gl.glTranslatef(-0.1f, 0.15f, -0.01f);  
            glu.gluSphere(q, WIDTH_HANDS, SLICES, STACKS);
            
        }
        if (c=='W'){  
            gl.glTranslatef(-0.45f, -0.38f, 0.1f);  
            glu.gluSphere(q, WIDTH_HANDS, SLICES, STACKS);
            
            
        }
        if (c==' '){
            gl.glTranslatef(-0.45f, -0.42f, 0.3f);  
            glu.gluSphere(q, WIDTH_HANDS, SLICES, STACKS);
        }    
        
        gl.glPopMatrix();
        
        
        //we create left arm
        set_red_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(-0.3f, -0.2f, 0.3f);        
        gl.glRotatef(90f, 0.7f, -0.5f, 0f);
        if (c=='J')
            gl.glRotatef(150, 0f, -100f, 0f);
        if (c=='W')
            gl.glRotatef(20, -1f, 0f, 0f);
        glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
        glu.gluSphere(q, WIDTH_ARMS, SLICES, STACKS); 
        gl.glRotatef(90f, -1f, 0.20f, 0f);   
        gl.glTranslatef(0f, -HEIGHT_ARMS, 0f);     
        gl.glRotatef(90f, 1f, -0.20f, 0f);
        if (c!='J')
            glu.gluDisk(q, 0f, WIDTH_ARMS, SLICES, STACKS);
        gl.glPopMatrix();
     }
     
     
     public void draw_arm_right (GL gl, GLU glu, char c){
       
           set_brown_material(gl); 
        gl.glPushMatrix();
        
        //we orientate axes if stan is walking or is jumping
        if (c=='J'){
            gl.glTranslatef(0.3f, 0.15f, -0.01f);  
            glu.gluSphere(q, WIDTH_HANDS, SLICES, STACKS);
        }
        if (c=='W'){  
            gl.glTranslatef(0.45f, -0.38f, 0.1f);  
            glu.gluSphere(q, WIDTH_HANDS, SLICES, STACKS);
        }
        if (c==' '){
            gl.glTranslatef(0.85f, -0.42f, 0.3f);  
            glu.gluSphere(q, WIDTH_HANDS, SLICES, STACKS);
        }       
     
        gl.glPopMatrix();
        //we create right arm
        set_red_material(gl);
        gl.glPushMatrix();
        gl.glTranslatef(0.7f, -0.2f, 0.3f);        
        gl.glRotatef(90f, 0.7f, 0.5f, 0f);
        if (c=='J')
            gl.glRotatef(150, 0f, 100f, 0f);
        if (c=='W')
            gl.glRotatef(20, -1f, 0f, 0f);
        glu.gluCylinder(q, WIDTH_ARMS, WIDTH_ARMS, HEIGHT_ARMS, SLICES, STACKS);
        glu.gluSphere(q, WIDTH_ARMS, SLICES, STACKS); 
        gl.glRotatef(90f, -1f, -0.20f, 0f);    
        gl.glTranslatef(0f, -HEIGHT_ARMS, 0f);     
        gl.glRotatef(90f, 1f, 0.20f, 0f);
        if (c==' ')
            glu.gluDisk(q, 0f, WIDTH_ARMS, SLICES, STACKS);
        gl.glPopMatrix();
     }
     
     
     public void draw_torus (GL gl, float R, float r, int N, int n){
        int maxn= 1000;
        n=Math.min(n,maxn-1);
        N=Math.min(N,maxn-1);
        float rr=1.5f*r;
        double dv=2*Math.PI/n;
        double dw=2*Math.PI/N;
        double v=0.0f;
        double w=0.0f;
        while(w<2*Math.PI+dw)
        {
            v=0.0f;
            gl.glBegin(GL.GL_TRIANGLE_STRIP);
            while(v<2*Math.PI+dv)
            {
                gl.glNormal3d(
                        (R+rr*Math.cos(v))*Math.cos(w)-(R+r*Math.cos(v))*Math.cos(w),
                        (R+rr*Math.cos(v))*Math.sin(w)-(R+r*Math.cos(v))*Math.sin(w),
                        (rr*Math.sin(v)-r*Math.sin(v)));
                gl.glVertex3d((R+r*Math.cos(v))*Math.cos(w),
                           (R+r*Math.cos(v))*Math.sin(w),
                            r*Math.sin(v));
                gl.glNormal3d(
                        (R+rr*Math.cos(v+dv))*Math.cos(w+dw)-(R+r*Math.cos(v+dv))*Math.cos(w+dw),
                        (R+rr*Math.cos(v+dv))*Math.sin(w+dw)-(R+r*Math.cos(v+dv))*Math.sin(w+dw),
                        rr*Math.sin(v+dv)-r*Math.sin(v+dv));
                gl.glVertex3d((R+r*Math.cos(v+dv))*Math.cos(w+dw),
                           (R+r*Math.cos(v+dv))*Math.sin(w+dw),
                            r*Math.sin(v+dv));
                v+=dv;
            }
            gl.glEnd();
            w+=dw;
        }
    }

   
    
}

