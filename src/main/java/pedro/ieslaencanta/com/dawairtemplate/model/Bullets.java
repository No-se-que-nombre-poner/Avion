/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pedro.ieslaencanta.com.dawairtemplate.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import pedro.ieslaencanta.com.dawairtemplate.IWarnClock;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.IKeyListener;
import pedro.ieslaencanta.com.dawairtemplate.model.sprites.SpriteMove;

/**
 *
 * @author jphmo
 */
public class Bullets extends SpriteMove{
    
    private Image image;
    private Direction direction = Direction.RIGHT;
    private static String pathImagenR = "bullets/bullet_rigth.png";
    
    public Bullets(int inc, Size s, Coordenada p, Rectangle board, Direction direction){
        super(inc, s, p, true, true, board);
        this.direction = direction;
        this.image = new Image(getClass().getResourceAsStream("/" + this.pathImagenR));
    }

    @Override
    public void draw(GraphicsContext gc) {
        int x = getPosicion().getX() + 9  ;
        int y = getPosicion().getY() + 10;
        gc.drawImage(this.image, 0, 0, 17, 3, x, y, 17, 3);
  
    }
    void move(){
        this.move(this.direction);
    }
    
    public void TicTac(){
        this.move();
        if(this.getPosicion().getX() - this.getInc() <= this.getBoard().getStart().getX()){
            this.setLive(false);
            
        }
        
       
    }
}
