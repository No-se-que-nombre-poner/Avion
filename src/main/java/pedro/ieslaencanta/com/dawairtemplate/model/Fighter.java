/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import pedro.ieslaencanta.com.dawairtemplate.IWarnClock;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IKeyListener;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IMove;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.SpriteMove;

/**
 *
 * @author jphmo
 */
public class Fighter extends SpriteMove implements IKeyListener, IWarnClock{
    private boolean[] keys_presed;
    private Image img;
    //path para la imagen
    private static String pathurl="avion.png";
    //para la animación
    private int original_height;
    private ArrayList<Bullets> bala;
    /**
     * 
     * @param inc incremento del movimiento
     * @param s tamaño del avión
     * @param p coordenadas iniciales
     * @param board rectangulo con las dimensiones del juego para no salirse
     */
    public Fighter(int inc, Size s, Coordenada p, Rectangle board) {
        super(inc, s, p, true, true, board);
        this.keys_presed = new boolean[5];
        this.img = new Image(getClass().getResourceAsStream("/" + Fighter.pathurl));
        //cambia al mover arriba y abajo
        this.original_height=s.getHeight();
        this.bala = new ArrayList();
    }
    /**
     * acciones al pulsar las teclas
     * @param code 
     */
    @Override
    public void onKeyPressed(KeyCode code) {

       if (code == KeyCode.RIGHT) {
            this.keys_presed[0] = true;
        }
        if (code == KeyCode.LEFT) {
            this.keys_presed[1] = true;
        }
        if (code == KeyCode.UP) {
            this.keys_presed[2] = true;
            this.getSize().setHeight(40);
        }
        if (code == KeyCode.DOWN) {
            this.keys_presed[3] = true;
            this.getSize().setHeight(40);
        }

    }
    /**
     * acciones al soltar el teclado
     * @param code 
     */
    @Override
    public void onKeyReleased(KeyCode code) {
       
        if (code == KeyCode.SPACE) {
         //crear una bala y añadirla
         shootB();
        }
        if (code == KeyCode.RIGHT) {
            this.keys_presed[0] = false;
        }
        if (code == KeyCode.LEFT) {
            this.keys_presed[1] = false;
        }
        if (code == KeyCode.UP) {
            this.keys_presed[2] = false;
            this.getSize().setHeight(original_height);
        }
        if (code == KeyCode.DOWN) {
            this.keys_presed[3] = false;
            this.getSize().setHeight(original_height);
        }
    }
    
    private void shootB() {
        Direction direction = Direction.RIGHT;
        Bullets tempo = new Bullets(5, new Size(12, 3), new Coordenada(this.getPosicion().getX() + 70, this.getPosicion().getY() + 16), this.getBoard(), direction);
        this.bala.add(tempo);
    }
    /**
     * dibujar, es algo más complejo al moverse las alas
     * @param gc 
     */
    @Override
    public void draw(GraphicsContext gc) {
        if (keys_presed[2]) {
            gc.drawImage(img, 163, 7, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                    this.getPosicion().getX(), this.getPosicion().getY(),
                    this.getSize().getWidth(), this.getSize().getHeight());
        } else {
            if (keys_presed[3]) {
                gc.drawImage(img, 54, 7, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                        this.getPosicion().getX(), this.getPosicion().getY(),
                        this.getSize().getWidth(), this.getSize().getHeight());
            } else {
                gc.drawImage(img, 105, 8, this.getSize().getWidth() / 2, this.getSize().getHeight() / 2,
                        this.getPosicion().getX(), this.getPosicion().getY(),
                        this.getSize().getWidth(), this.getSize().getHeight());
            }
        }
        this.bala.forEach(b -> b.draw(gc));
    }
    //movimiento del avión
    private void move() {

        if (this.keys_presed[0]) {
            this.move(IMove.Direction.RIGHT);
        }
        if (this.keys_presed[1]) {
            this.move(IMove.Direction.LEFT);
        }
        if (this.keys_presed[2]) {
            this.move(IMove.Direction.UP);
        }
        if (this.keys_presed[3]) {
            this.move(IMove.Direction.DOWN);
        }
    }
    /** 
     * cada vez que se recibe un tictac se mueve, faltan las balas del fighter
     */
    @Override
    public void TicTac() {
        this.move();
       //mover las balas 
       this.bala.forEach(b -> b.move());
       // Con el forEach hacemos que las balas se muevan.
       this.bala.removeIf(b -> b.getPosicion().getX() + b.getSize().getWidth() + b.getInc() >= b.getBoard().getEnd().getX());
       //con el removeIf hacemos que las balas se borren una vez llegar al final.
    }  

    public ArrayList<Bullets> getBala() {
        return bala;
    }
    
}
