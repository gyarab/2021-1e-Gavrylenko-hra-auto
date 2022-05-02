package rocnikovka;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Třída reprezentující překážku.
 * @author vita
 */
public class Prekazka {
    private HerniPanel panel;   // reference na panel
    private Image prekazkaObr;
    private int x;
    private int y;
    private int dy = 1;         // směr na ose y (dolů) a rychlost (1px za vykreslení)
    private boolean viditelny;  // překážka je na panelu true, mimo panel false
    private Random generator;   // generátor náhodných čísel

    /**
     * Konstruktor.
     * @param x x-ová souřadnice
     */
    public Prekazka(HerniPanel panel) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("prekazka.png"));
        prekazkaObr = ii.getImage();

        this.generator = new Random();  // vytvoření generátoru náhodných čísel
        this.panel = panel;             // reference na panel
        this.x = generator.nextInt(panel.getWidth() - prekazkaObr.getWidth(null));
        this.y = -50;   // umístění překážky na ose y při vytvoření

        viditelny = true;
    }

    /**
     * Vykreslí obrázek na aktuální souřadnice
     * @param g grafický kontext
     */
    public void vykresliSe(Graphics g) {

        g.drawImage(prekazkaObr, x, y, null);
    }

    /**
     * Změna y-ové souřadnice. Pokud dojede na konec panelu, změní se hodnota
     * viditelny na false.
     */
    public void provedPohyb() {
        y += dy;
        if (y > panel.getHeight()) {
            viditelny = false;
        }
    }

    /**
     * Vrací obrys obrázku ve formě obdélníka.
     * @return Rectangle ve velikosti obrázku
     */
    public Rectangle getOkreje() {
        Rectangle r = new Rectangle(x, y, prekazkaObr.getWidth(panel), prekazkaObr.getHeight(panel));
        return r;
    }

    /**
     * Vrací zda je objekt visible či nikoli.
     * @return visible
     */
    public boolean isViditelny() {
        return viditelny;
    }
}
