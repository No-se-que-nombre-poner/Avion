/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.dawairtemplate.model.sprites;

import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.dawairtemplate.model.Coordenada;
import pedro.ieslaencanta.com.dawairtemplate.model.Rectangle;
import pedro.ieslaencanta.com.dawairtemplate.model.Size;



/**
 *
 * @author Pedro
 */
/**
 *
 * @author Pedro
 */
public abstract class SpriteMove extends Sprite implements IMove, ICollision{

    private int inc;
    private Rectangle board;
    private boolean colli;
   
    public SpriteMove(){
        super();
    }
    protected void init(int inc,Size s,Coordenada p,boolean visible, boolean live, Rectangle board){
        super.init(s,p,visible,live);
        this.setInc(inc);
        this.setBoard(board);
    }
    public SpriteMove(int inc,Size s,Coordenada p,boolean visible, boolean live, Rectangle board ){
        super(s,p,visible,live);
        this.inc=inc;
        this.board=board;
}
   
    public void move( Direction d) {
        //se mueve hacia abajo
        if(d==Direction.DOWN && this.getPosicion().getY()+this.getSize().getHeight()+this.getInc()<getBoard().getEnd().getY()){
            this.getPosicion().setY(this.getPosicion().getY()+this.getInc());
        }
        //se mueve hacia arriba
        if(d==Direction.UP && this.getPosicion().getY()-this.getInc()>getBoard().getStart().getY()){
            this.getPosicion().setY(this.getPosicion().getY()-this.getInc());
        }
        //se mueve hacia izquierda
        if(d==Direction.LEFT && this.getPosicion().getX()>=this.getInc()){//board.getStart().getX()){
            this.getPosicion().setX(this.getPosicion().getX()-this.getInc());
        }
        //se mueve hacia derecha
        if(d==Direction.RIGHT && this.getPosicion().getX()+this.getSize().getWidth()+this.getInc()<getBoard().getEnd().getX()){
            this.getPosicion().setX(this.getPosicion().getX()+this.getInc());
           
        }
    }
    public int getInc(){
        return this.inc;
    }

    /**
     * @param inc the inc to set
     */
    public void setInc(int inc) {
        this.inc = inc;
    }

    /**
     * @return the board
     */
    public Rectangle getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Rectangle board) {
        this.board = board;
    }
    
    @Override
    public boolean hascollided() {
        return this.colli;
    }

    @Override
    public void setColision() {
        this.colli = true;
        this.live = false;
    }
    
    @Override
    public int getX() {
        return this.getPosicion().getX();
    }

    @Override
    public int getY() {
        return this.getPosicion().getY();
    }

    @Override
    public int getHeight() {
       return this.size.getHeight();
    }

    @Override
    public int getWidht() {
        return this.size.getWidth();
    }

    @Override
    public void setFree() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
