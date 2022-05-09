/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.SpriteMove;

/**
 *
 * @author jphmo
 */
public class FighterEnemy  extends SpriteMove{
    private Image image;
    private ArrayList<Bullets> balas;
    private static String pathImagen = "enemigos/e1.png";
    
    
    public FighterEnemy(int inc, Size s, Coordenada p, Rectangle board, Direction directionE){
        super(inc,s, p, true, true, board);
        this.balas = new ArrayList();
        this.image = new Image(getClass().getResourceAsStream("/" + this.pathImagen));
    }

    @Override
    public void draw(GraphicsContext gc) {
        int x = getPosicion().getX();
        int y = getPosicion().getY();
        gc.drawImage(this.image, 0, 0, 31, 14, x, y, 62, 28);
    }
    
    public void shoot() {
        Direction directionE = Direction.LEFT;
        if ((int) (Math.random() * 30) == 0) {
            Bullets b = new Bullets(5,new Size(12, 3), new Coordenada(this.getPosicion().getX(), this.getPosicion().getY()), this.getBoard(), directionE);
            this.balas.add(b);
        }
    }
    
    public void setBullets(ArrayList<Bullets> bala) {
        this.balas = bala;
    }
    
    public void TicTac() {
        this.move(Direction.LEFT);
        this.shoot();
        if( this.getPosicion().getX() - this.getInc() <= this.getBoard().getStart().getX()){
            this.setLive(false);
            
        }
    }
    
    
    
}
