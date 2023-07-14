import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import com.jogamp.opengl.util.gl2.GLUT;



public class MainFrame implements GLEventListener, KeyListener { ;
    public static DisplayMode dm, dm_old;
    private GLU glu = new GLU();
    private float x_rotation, y_rotation, z_rotation;
    private int texture;

    private boolean rotateX = false;
    private boolean rotateY = false;
    private boolean rotateZ = false;
    private int index;

    private JFrame frame;
    private List<String> stringList = new ArrayList<>();
    private GLUT glut;


    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        gl.glScalef(2.0f, 2.0f, 1.0f);
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        gl.glTranslatef(0f, 0f, -5.0f);
        gl.glColor3f(1.0f, 1.0f, 1.0f);
        gl.glRasterPos2f(0.0f, 2f);

        String text = stringList.get(index);
        String result = text.replaceAll("\\.jpg$", "");
        text = result.substring(0, 1).toUpperCase() + result.substring(1);
        text = "Texture name: " + text;
        glut.glutBitmapString(GLUT.BITMAP_TIMES_ROMAN_24, text); // Render the text
        gl.glScalef(1.0f, 1.0f, 1.0f);
        gl.glRotatef(x_rotation, 1.0f, 1.0f, 1.0f);
        gl.glRotatef(y_rotation, 0.0f, 1.0f, 0.0f);
        gl.glRotatef(z_rotation, 0.0f, 0.0f, 1.0f);
        gl.glBindTexture(GL2.GL_TEXTURE_2D, texture);
        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);

        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);

        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);

        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);

        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f( 1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f( 1.0f, -1.0f,  1.0f);

        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f(-1.0f, -1.0f,  1.0f);
        gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f,  1.0f);
        gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f,  1.0f, -1.0f);
        gl.glEnd();
        gl.glFlush();


            try {
                File im = new File("D:\\GIU\\Proiect\\Textures\\" + stringList.get(index));
                Texture t = TextureIO.newTexture(im, true);
                texture = t.getTextureObject(gl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (rotateX) {
            x_rotation += .1f;
        }
        if (rotateY) {
            y_rotation += .1f;
        }
        if (rotateZ) {
            z_rotation += .1f;
        }
    }

    private void drawCube(GL2 gl) {
        float vertices[] = {
                1, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0,
                1, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0, 1
        };
        int indices[] = {
                0, 1, 2, 3,
                0, 3, 4, 5,
                0, 5, 6, 1,
                1, 6, 7, 2,
                7, 4, 3, 2,
                4, 7, 6, 5
        };

        FloatBuffer bVertices = Buffers.newDirectFloatBuffer(vertices.length);
        bVertices.put(vertices).rewind();
        IntBuffer bIndices = Buffers.newDirectIntBuffer(indices.length);
        bIndices.put(indices).rewind();
        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL2.GL_FLOAT, 0, bVertices);
        gl.glDrawElements(GL2.GL_QUADS, 24, GL2.GL_UNSIGNED_INT, bIndices);
        gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }
    @Override
    public void init(GLAutoDrawable drawable) {
        stringList.add("stone.jpg");
        stringList.add("cobblestone.jpg");
        stringList.add("dirt.jpg");
        stringList.add("gold.jpg");
        stringList.add("diamond.jpg");
        final GL2 gl = drawable.getGL().getGL2();
        gl.glShadeModel(GL2.GL_SMOOTH);
        float r = 255 / 255f;
        float g = 255 / 255f;
        float b = 204 / 255f;
        gl.glClearColor(r, g, b, 0f);
        gl.glClearDepth(1.0f);
        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
        gl.glEnable(GL2.GL_TEXTURE_2D);
        glut = new GLUT();
        frame.addKeyListener(this);
    }
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        final GL2 gl = drawable.getGL().getGL2();
        if (height <= 0)
            height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 20.0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public static void main(String[] args) {
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        MainFrame r = new MainFrame();
        glcanvas.addGLEventListener(r);
        glcanvas.setSize(800, 800);
        final JFrame frame = new JFrame("Proiect GIU");
        r.frame = frame;
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
        frame.setResizable(false);
        final FPSAnimator animator = new FPSAnimator(glcanvas, 300, true);
        animator.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            x_rotation -= 5.0f;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            x_rotation += 5.0f;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            y_rotation -= 5.0f;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            y_rotation += 5.0f;
        }
        else if (e.getKeyCode() == KeyEvent.VK_X) {
            if(index + 1 >= stringList.size())
                index = 0;
            else
                index = index + 1;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_X) {
            rotateX = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_Y) {
            rotateY = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_Z) {
            rotateZ = false;
        }
    }
}