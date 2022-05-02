package rocnikovka;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

/**
 * Třída reprezentující auto.
 * @author vita
 */
public class Auto implements KeyListener {
    private HerniPanel panel;   // reference na panel
    private Image autoObr;
    private int x;      // x-ová souřadnice auta
    private int y;      // y-ová souřadnice auta
    private int dx;     // směr auta po ose x (+ doprava, - doleva)

    public Auto(HerniPanel panel) {

        ImageIcon ii = new ImageIcon(this.getClass().getResource("auto.png"));
        autoObr = ii.getImage();

        this.panel = panel;
        this.x = 185;   // počáteční souřadnice x
        this.y = 558;   // souřadnice y
        this.dx = 0;
    }

    /**
     * Vykreslí obrázek na aktuální souřadnice
     * @param g grafický kontext
     */
    public void vykresliSe(Graphics g) {

        g.drawImage(autoObr, x, y, null);
    }

    /**
     * Změna x-ové souřadnice v daném směru.
     */
    public void provedPohyb() {
        x += dx;
        if (x < 0) {
            x = 0;
        } else if (x > (panel.getWidth() - autoObr.getWidth(null) - 1) && (panel.getWidth() >0)) {
            x = panel.getWidth() - autoObr.getWidth(null) - 1;
        }
    }

    /**
     * Vrací obrys obrázku ve formě obdélníka.
     * @return Rectangle ve velikosti obrázku
     */
    public Rectangle getOkraje() {
        Rectangle r = new Rectangle(x, y, autoObr.getWidth(null), autoObr.getHeight(null));
        return r;
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;    // směr doleva, rychlost 2px za vykreslení
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;     // směr doprava, rychlost 2px za vykreslení
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
