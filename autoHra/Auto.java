package autoHra;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;


public class Auto<HerniPanel> implements KeyListener {
    private HerniPanel panel;   
    private Image autoObr;
    private int x;      
    private int y;    
    private int dx;     
    
   
    public Auto(HerniPanel panel) {
        
        ImageIcon ii = new ImageIcon(this.getClass().getResource("auto.png"));
        autoObr = ii.getImage();
        
        this.panel = panel;
        this.x = 185;  
        this.y = 558;   
        this.dx = 0;
    }
  
    public void vykresliSe(Graphics g) {
        
        g.drawImage(autoObr, x, y, null);
    }
    
   
    public void provedPohyb() {
        x += dx;
        if (x < 0) {
            x = 0;
        } else if (x > (((Rectangle) panel).getWidth() - autoObr.getWidth(null) - 1) && (((Rectangle) panel).getWidth() >0)) {
            x = (int) (((Rectangle) panel).getWidth() - autoObr.getWidth(null) - 1);
        }
    }
    
  
    public Rectangle getOkraje() {
        Rectangle r = new Rectangle(x, y, autoObr.getWidth(null), autoObr.getHeight(null));
        return r;
    }
    
   
    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;    
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;    
        }
    }
    
    
    @Override
    public void keyReleased(KeyEvent ke) {
        dx = 0;
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    } 
}
