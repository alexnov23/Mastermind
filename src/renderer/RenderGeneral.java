package renderer;

import IU.MasterMind;
import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.InputStream;

public class RenderGeneral implements GLEventListener,MouseListener, MouseMotionListener{
    private boolean res=false;
    private float cordResy;
    private float cordResx;
    public RenderGeneral() {
        // Utilitarios
        movibola[0][0]=0.7f;
        movibola[0][1]=0.2f;
        movibola[0][2]=0;
        movibola[1][0]=0.7f;
        movibola[1][1]=0.4f;
        movibola[1][2]=0;
        movibola[2][0]=0.7f;
        movibola[2][1]=0.6f;
        movibola[2][2]=0;
        movibola[3][0]=0.7f;
        movibola[3][1]=0.8f;
        movibola[3][2]=0;
        glu = new GLU();
        glut = new GLUT();
    }
    @Override
    public void init(GLAutoDrawable drawable) {
        // Crear una Interfaz con OPENGL 2.0        
        gl = drawable.getGL().getGL2();
        // Recuperar el ancho y alto de ventama de visualización
        int w = ((Component) drawable).getWidth();
        int h = ((Component) drawable).getHeight();
        // Establecer un visor en todo el area de la ventana de visualización
        gl.glViewport(0, 0, w, h);
        // Establecer el uso de matrices en Modelo-Vista
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glDrawBuffer(GL2.GL_FRONT_AND_BACK);
        glu.gluLookAt(
               0, 0, 50,
                0.7, 7, 0,
                0, 1, 0
        );
        gl.glMatrixMode(GL2.GL_PROJECTION);
        double aspect = w / h;
        glu.gluPerspective(60.0, aspect, 1, 100.0);
        gl.glLoadIdentity();
        try {
            InputStream stream = getClass().getResourceAsStream("madera.jpg");
            GLProfile profile = GLProfile.getDefault();
            data = TextureIO.newTextureData(profile, stream, false, "jpg");
            ladoTexture = TextureIO.newTexture(data);
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
        try {
            InputStream stream = getClass().getResourceAsStream("MADERA_1.jpg");
            GLProfile profile = GLProfile.getDefault();
            data = TextureIO.newTextureData(profile, stream, false, "jpg");
            frenteTexture = TextureIO.newTexture(data);
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }
    }
    @Override
    public void display(GLAutoDrawable drawable) {
        gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT
                | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glClearColor(0, 0, 0, 1);
        switch (NroJugada){
            case 0:
                corY=1.2f;
                corX1=-6f;
                corX2=-4.2f;
                corX3=-2.3f;
                corX4=-0.5f;
                break;
            case 1:
                corY=3f;
                corX1=-5.9f;
                corX2=-4.2f;
                corX3=-2.3f;
                corX4=-0.8f;
                break;
            case 2:
                corY=4.8f;
                corX1=-5.7f;
                corX2=-4.2f;
                corX3=-2.5f;
                corX4=-0.9f;
                break;
            case 3:
                corY=6.7f;
                corX1=-5.5f;
                corX2=-4f;
                corX3=-2.4f;
                corX4=-0.9f;
                break;
            case 4:
                corY=8.7f;
                corX1=-5.4f;
                corX2=-3.9f;
                corX3=-2.3f;
                corX4=-0.9f;
                break;
            case 5:
                corY=10.4f;
                corX1=-5.3f;
                corX2=-3.9f;
                corX3=-2.4f;
                corX4=-1f;
                break;
            case 6:
                corY=12.2f;
                corX1=-5.3f;
                corX2=-3.9f;
                corX3=-2.4f;
                corX4=-0.9f;
                break;
            case 7:
                corY=13.9f;
                corX1=-5.2f;
                corX2=-3.8f;
                corX3=-2.4f;
                corX4=-1.1f;
                break;
            case 8:
                corY=15.8f;
                corX1=-5f;
                corX2=-3.7f;
                corX3=-2.4f;
                corX4=-1.2f;
                break;
        }
        gl.glPushMatrix();
        gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_DECAL);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
        ladoTexture.enable(gl);
        ladoTexture.bind(gl);
        //tablero lateral
        gl.glScalef(1f, .1f, 0f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0,0);
        gl.glVertex3f(0f, 0f, 0f);
        gl.glTexCoord2f(1,0);
        gl.glVertex3f(1f, 0f, 0f);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(1f, 1f, 0f);
        gl.glTexCoord2f(0,1);
        gl.glVertex3f(0f, 1f, 0f);
        gl.glEnd();
        ladoTexture.disable(gl);
        gl.glPopMatrix();
        //tablero de encima
        gl.glPushMatrix();
        gl.glTexEnvi(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_DECAL);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2.GL_FILL);
        frenteTexture.enable(gl);
        frenteTexture.bind(gl);
        gl.glScalef(1f, .1f, 0f);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0,0);
        gl.glVertex3f(0f, 1f, 0f);
        gl.glTexCoord2f(1,0);
        gl.glVertex3f(1f, 1f, 0f);
        gl.glTexCoord2f(1,1);
        gl.glVertex3f(0.8f, 20f, 2f);
        gl.glTexCoord2f(0,1);
        gl.glVertex3f(0.1f, 20f, 2f);
        gl.glEnd();
        frenteTexture.disable(gl);        
        gl.glPopMatrix();
        //cuadro
        gl.glPushMatrix();
        gl.glScalef(.1f, .1f, 0f);
        gl.glColor3f(0, 0, 0);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex3f(-4.6f, 1f, 0f);
        gl.glVertex3f(-3.6f, 20f, 0f);
        gl.glVertex3f(-2.8f, 1f, 0f);
        gl.glVertex3f(-2.4f, 20f, 0f);
        gl.glVertex3f(-1f, 1f, 0f);
        gl.glVertex3f(-1.2f, 20f, 0f);
        gl.glVertex3f(0.8f, 1f, 0f);
        gl.glVertex3f(0f, 20f, 0f);
        gl.glEnd();
        gl.glPopMatrix();
        //maya para validar puntos      
        for (int i = -18; i <=0; i++) {
            gl.glPushMatrix();
            gl.glColor3f(1, 1, 1);
            gl.glScalef(.1f, .1f, 0f);
            gl.glBegin(GL2.GL_LINES);
            gl.glVertex3f(i, -10f, 0f);
            gl.glVertex3f(i, 20f, 0f);
            gl.glVertex3f(-10f, -i, 0f);
            gl.glVertex3f(20f, -i, 0f);
            gl.glEnd();
            gl.glPopMatrix();
        }
        lugarVacio ();
        //bola roja
            gl.glPushMatrix();
            gl.glTranslatef(movibola[0][0], movibola[0][1], movibola[0][2]);
            gl.glScalef(0.1f, 0.1f, 0f);
            gl.glColor3f(1, 0, 0);
            glut.glutSolidSphere(0.6f, 10, 10);
            gl.glPopMatrix();
        //bola verde 
            gl.glPushMatrix();
            gl.glTranslatef(movibola[1][0], movibola[1][1], movibola[1][2]);
            gl.glScalef(0.1f, 0.1f, 0f);
            gl.glColor3f(0, 1, 0);
            glut.glutSolidSphere(0.6f, 10, 10);
            gl.glPopMatrix();
        //bola azul
            gl.glPushMatrix();
            gl.glTranslatef(movibola[2][0], movibola[2][1], movibola[2][2]);
            gl.glScalef(0.1f, 0.1f, 0f);
            gl.glColor3f(0, 0, 1);
            glut.glutSolidSphere(0.6f, 10, 10);
            gl.glPopMatrix();
        //bola amarillo
            gl.glPushMatrix();
            gl.glTranslatef(movibola[3][0], movibola[3][1], movibola[3][2]);
            gl.glScalef(0.1f, 0.1f, 0f);
            gl.glColor3f(1, 1, 0);
            glut.glutSolidSphere(0.6f, 10, 10);
            gl.glPopMatrix();   
        if (fin[0] && fin[1] && fin[2] && fin[3]){
            if(
            color[NroJugada][0][0]==MasterMind.Solucion[0][0] &&
            color[NroJugada][0][1]==MasterMind.Solucion[0][1] &&
            color[NroJugada][0][2]==MasterMind.Solucion[0][2] &&
            color[NroJugada][1][0]==MasterMind.Solucion[1][0] &&
            color[NroJugada][1][1]==MasterMind.Solucion[1][1] &&
            color[NroJugada][1][2]==MasterMind.Solucion[1][2] &&
            color[NroJugada][2][0]==MasterMind.Solucion[2][0] &&
            color[NroJugada][2][1]==MasterMind.Solucion[2][1] &&
            color[NroJugada][2][2]==MasterMind.Solucion[2][2] &&
            color[NroJugada][3][0]==MasterMind.Solucion[3][0] &&
            color[NroJugada][3][1]==MasterMind.Solucion[3][1] &&
            color[NroJugada][3][2]==MasterMind.Solucion[3][2] ){
                 System.out.println("gane");
            }
            res=true;
            cordResy=corY;
            cordResx=corX4;
            NroJugada++; 
            fin[0]=false;
            fin[1]=false;
            fin[2]=false;
            fin[3]=false;
        }
        if(res){
            generarRespuestas();
        }
        
    }
    public void generarRespuestas(){ 
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(cordResx+1.7f, cordResy+0.6f, 0);
        if( color[NroJugada-1][0][0]==MasterMind.Solucion[0][0] &&
            color[NroJugada-1][0][1]==MasterMind.Solucion[0][1] &&
            color[NroJugada-1][0][2]==MasterMind.Solucion[0][2] ){
            gl.glColor3f(0,0,0);
        }else{
            gl.glColor3f(1,1,1);
        }
        glut.glutSolidSphere(0.2f, 10, 10);
        gl.glTranslatef(0.5f, 0, 0);
        if( color[NroJugada-1][1][0]==MasterMind.Solucion[1][0] &&
            color[NroJugada-1][1][1]==MasterMind.Solucion[1][1] &&
            color[NroJugada-1][1][2]==MasterMind.Solucion[1][2] ){
            gl.glColor3f(0,0,0);
        }else{
            gl.glColor3f(1,1,1);
        }
        glut.glutSolidSphere(.2f, 10, 10);
        gl.glTranslatef(0.5f, 0, 0);
        if( color[NroJugada-1][2][0]==MasterMind.Solucion[2][0] &&
            color[NroJugada-1][2][1]==MasterMind.Solucion[2][1] &&
            color[NroJugada-1][2][2]==MasterMind.Solucion[2][2] ){
            gl.glColor3f(0,0,0);
        }else{
            gl.glColor3f(1,1,1);
        }
        glut.glutSolidSphere(.2f, 10, 10);
        gl.glTranslatef(0.5f,0, 0);
        if( color[NroJugada-1][3][0]==MasterMind.Solucion[3][0] &&
            color[NroJugada-1][3][1]==MasterMind.Solucion[3][1] &&
            color[NroJugada-1][3][2]==MasterMind.Solucion[3][2] ){
            gl.glColor3f(0,0,0);
        }else{
            gl.glColor3f(1,1,1);
        };
        glut.glutSolidSphere(.2f, 10, 10);
        gl.glPopMatrix();
    }
    public void lugarVacio (){
        //primera jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-5.4f, 1.8f, 0);
        gl.glColor3f(color[0][0][0],color[0][0][1],color[0][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.8f, 0, 0);
        gl.glColor3f(color[0][1][0],color[0][1][1],color[0][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.8f, 0, 0);
        gl.glColor3f(color[0][2][0],color[0][2][1],color[0][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.8f,0, 0);
        gl.glColor3f(color[0][3][0],color[0][3][1],color[0][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
        //2da jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-5.3f, 3.6f, 0);
        gl.glColor3f(color[1][0][0],color[1][0][1],color[1][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.7f, 0, 0);
        gl.glColor3f(color[1][1][0],color[1][1][1],color[1][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.7f, 0, 0);
        gl.glColor3f(color[1][2][0],color[1][2][1],color[1][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.7f,0, 0);
        gl.glColor3f(color[1][3][0],color[1][3][1],color[1][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
        //3ra jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-5.2f, 5.4f, 0);
        gl.glColor3f(color[2][0][0],color[2][0][1],color[2][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.65f, 0, 0);
        gl.glColor3f(color[2][1][0],color[2][1][1],color[2][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.65f, 0, 0);
        gl.glColor3f(color[2][2][0],color[2][2][1],color[2][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.65f,0, 0);
        gl.glColor3f(color[2][3][0],color[2][3][1],color[2][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
        //4ta jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-5f, 7.2f, 0);
        gl.glColor3f(color[3][0][0],color[3][0][1],color[3][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.6f, 0, 0);
        gl.glColor3f(color[3][1][0],color[3][1][1],color[3][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.6f, 0, 0);
        gl.glColor3f(color[3][2][0],color[3][2][1],color[3][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.6f,0, 0);
        gl.glColor3f(color[3][3][0],color[3][3][1],color[3][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
        //5ta jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-4.9f, 9f, 0);
        gl.glColor3f(color[4][0][0],color[4][0][1],color[4][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.55f, 0, 0);
        gl.glColor3f(color[4][1][0],color[4][1][1],color[4][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.55f, 0, 0);
        gl.glColor3f(color[4][2][0],color[4][2][1],color[4][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.55f,0, 0);
        gl.glColor3f(color[4][3][0],color[4][3][1],color[4][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
        //6ta jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-4.75f, 10.8f, 0);
        gl.glColor3f(color[5][0][0],color[5][0][1],color[5][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.45f, 0, 0);
        gl.glColor3f(color[5][1][0],color[5][1][1],color[5][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.45f, 0, 0);
        gl.glColor3f(color[5][2][0],color[5][2][1],color[5][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.45f,0, 0);
        gl.glColor3f(color[5][3][0],color[5][3][1],color[5][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
        //7ma jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-4.7f, 12.6f, 0);
        gl.glColor3f(color[6][0][0],color[6][0][1],color[6][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.45f, 0, 0);
        gl.glColor3f(color[6][1][0],color[6][1][1],color[6][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.45f, 0, 0);
        gl.glColor3f(color[6][2][0],color[6][2][1],color[6][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.45f,0, 0);
        gl.glColor3f(color[6][3][0],color[6][3][1],color[6][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
        //8va jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-4.55f, 14.4f, 0);
        gl.glColor3f(color[7][0][0],color[7][0][1],color[7][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.35f, 0, 0);
        gl.glColor3f(color[7][1][0],color[7][1][1],color[7][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.35f, 0, 0);
        gl.glColor3f(color[7][2][0],color[7][2][1],color[7][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.35f,0, 0);
        gl.glColor3f(color[7][3][0],color[7][3][1],color[7][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
        //9na jugada
        gl.glPushMatrix();
        gl.glScalef(0.1f, 0.1f, 0f);
        gl.glTranslatef(-4.45f, 16.2f, 0);
        gl.glColor3f(color[8][0][0],color[8][0][1],color[8][0][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.3f, 0, 0);
        gl.glColor3f(color[8][1][0],color[8][1][1],color[8][1][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.3f, 0, 0);
        gl.glColor3f(color[8][2][0],color[8][2][1],color[8][2][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glTranslatef(1.3f,0, 0);
        gl.glColor3f(color[8][3][0],color[8][3][1],color[8][3][2]);
        glut.glutSolidSphere(0.6f, 10, 10);
        gl.glPopMatrix();
    }
    public int ElegirBola(double x,double y){
        if(x >= 6.3 && x<=7.5 && y>=1.5 && y<=2.5){
            return 1;
        }if(x >= 6.3 && x<=7.5 && y>=3.5 && y<=4.5){
            return 2;
        }if(x >= 6.3 && x<=7.5 && y>=5.5 && y<=6.5){
            return 3;
        }if(x >= 6.3 && x<=7.5 && y>=7.5 && y<=8.5){
            return 4;
        }return 5;
    }
    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }
    @Override
    public void dispose(GLAutoDrawable glad) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
         System.out.println("presed");
        Component c = e.getComponent();
        double height = c.getHeight();
        x = e.getX();
        y = (float)(height-e.getY()); 
        x=(x-390)/40;
        y=(y-130)/40;
        switch (ElegirBola(x,y)){
            case 1:
                act [0]=false;
                act [1]=true;
                act [2]=true;
                act [3]=true;
                break;
            case 2:
                act [0]=true;
                act [1]=false;
                act [2]=true;
                act [3]=true;
                break;
            case 3:
                act [0]=true;
                act [1]=true;
                act [2]=false;
                act [3]=true;
                break;
            case 4:
                act [0]=true;
                act [1]=true;
                act [2]=true;
                act [3]=false;
                break;
            case 5:
                act [0]=true;
                act [1]=true;
                act [2]=true;
                act [3]=true;
                break;
        }
        IU.MasterMind.glCanvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Component c = e.getComponent();
        double height = c.getHeight();
        x = e.getX();
        y = (float)(height-e.getY());    
        x=(x-390)/40;
        y=(y-130)/40;
        if(act[0]==false){
            if(x >= corX1 && x<=corX1+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][0][0]=1;
                color[NroJugada][0][1]=0;
                color[NroJugada][0][2]=0;
                fin[0] = true; 
            }if(x >= corX2 && x<=corX2+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][1][0]=1;
                color[NroJugada][1][1]=0;
                color[NroJugada][1][2]=0;
                fin[1]= true; 
            }if(x >= corX3 && x<=corX3+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][2][0]=1;
                color[NroJugada][2][1]=0;
                color[NroJugada][2][2]=0;
                fin[2] = true; 
            }if(x >= corX4 && x<=corX4+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][3][0]=1;
                color[NroJugada][3][1]=0;
                color[NroJugada][3][2]=0;
                fin[3] = true;
            }
        }if(act[1]==false){
            if(x >= corX1 && x<=corX1+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][0][0]=0;
                color[NroJugada][0][1]=1;
                color[NroJugada][0][2]=0;
                fin[0] = true; 
            }if(x >= corX2 && x<=corX2+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][1][0]=0;
                color[NroJugada][1][1]=1;
                color[NroJugada][1][2]=0;
                fin[1]= true; 
            }if(x >= corX3 && x<=corX3+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][2][0]=0;
                color[NroJugada][2][1]=1;
                color[NroJugada][2][2]=0;
                fin[2] = true; 
            }if(x >= corX4 && x<=corX4+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][3][0]=0;
                color[NroJugada][3][1]=1;
                color[NroJugada][3][2]=0;
                fin[3] = true;
            }
        }if(act[2]==false){
            if(x >= corX1 && x<=corX1+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][0][0]=0;
                color[NroJugada][0][1]=0;
                color[NroJugada][0][2]=1;
                fin[0] = true; 
            }if(x >= corX2 && x<=corX2+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][1][0]=0;
                color[NroJugada][1][1]=0;
                color[NroJugada][1][2]=1;
                fin[1]= true; 
            }if(x >= corX3 && x<=corX3+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][2][0]=0;
                color[NroJugada][2][1]=0;
                color[NroJugada][2][2]=1;
                fin[2] = true; 
            }if(x >= corX4 && x<=corX4+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][3][0]=0;
                color[NroJugada][3][1]=0;
                color[NroJugada][3][2]=1;
                fin[3] = true;
            }
        }if(act[3]==false){
            if(x >= corX1 && x<=corX1+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][0][0]=1;
                color[NroJugada][0][1]=1;
                color[NroJugada][0][2]=0;
                fin[0] = true; 
            }if(x >= corX2 && x<=corX2+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][1][0]=1;
                color[NroJugada][1][1]=1;
                color[NroJugada][1][2]=0;
                fin[1]= true; 
            }if(x >= corX3 && x<=corX3+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][2][0]=1;
                color[NroJugada][2][1]=1;
                color[NroJugada][2][2]=0;
                fin[2] = true; 
            }if(x >= corX4 && x<=corX4+1.2 && y>=corY && y<=corY+1.1){
                color[NroJugada][3][0]=1;
                color[NroJugada][3][1]=1;
                color[NroJugada][3][2]=0;
                fin[3] = true;
            }
        }
        movibola[0][0]=0.7f;
        movibola[0][1]=0.2f;
        movibola[0][2]=0;
        movibola[1][0]=0.7f;
        movibola[1][1]=0.4f;
        movibola[1][2]=0;
        movibola[2][0]=0.7f;
        movibola[2][1]=0.6f;
        movibola[2][2]=0;
        movibola[3][0]=0.7f;
        movibola[3][1]=0.8f;
        movibola[3][2]=0;
        IU.MasterMind.glCanvas.repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        Component c = e.getComponent();
        double height = c.getHeight();
        x = e.getX();
        y = (float)(height-e.getY());    
        x=(x-390)/400;
        y=(y-130)/400;
        if(act[0]==false){
            movibola[0][0]=x;
            movibola[0][1]=y;
            movibola[0][2]=0;
        }if(act[1]==false){
            movibola[1][0]=x;
            movibola[1][1]=y;
            movibola[1][2]=0;
        }if(act[2]==false){
            movibola[2][0]=x;
            movibola[2][1]=y;
            movibola[2][2]=0;
        }if(act[3]==false){
            movibola[3][0]=x;
            movibola[3][1]=y;
            movibola[3][2]=0;
        }
        IU.MasterMind.glCanvas.repaint();
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    private GL2 gl;
    private GLU glu;
    private GLUT glut;
    private Texture ladoTexture;
    private Texture frenteTexture;
    private TextureData data;
    private float x;
    private float y;
    private int NroJugada=0;
    private float corX1=0;
    private float corX2=0;
    private float corX3=0;
    private float corX4=0;
    private float corY=0;
    private boolean fin[]={false,false,false,false};
    private boolean act[]={true,true,true,true};
    private int color[][][]=new int [9][4][3];
    private float movibola[][]=new float [4][3];
}