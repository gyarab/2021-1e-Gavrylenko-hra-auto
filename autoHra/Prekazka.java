package autoHra;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

public class Prekazka {
    private HerniPanel panel;  
    private Image prekazkaObr;
    private int x;
    private int y;
    private int dy = 1;       
    private boolean viditelny; 
    private Random generator;   
    
   
    public Prekazka(HerniPanel panel) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("prekazka.png"));
        prekazkaObr = ii.getImage();
        
        this.generator = new Random(); 
        this.panel = panel;            
        this.x = generator.nextInt(panel.getWidth() - prekazkaObr.getWidth(null));
        this.y = -50;   
        
        viditelny = true;
    }
    
    
    public void vykresliSe(Graphics g) {
        
        g.drawImage(prekazkaObr, x, y, null);
    }
    

    public void provedPohyb() {
        y += dy;
        if (y > panel.getHeight()) {
            viditelny = false;
        }
    }
    
    
    public Rectangle getOkreje() {
        Rectangle r = new Rectangle(x, y, prekazkaObr.getWidth(panel), prekazkaObr.getHeight(panel));
        return r;
    }
   

    public boolean isViditelny() {
        return viditelny;
    }  
}
