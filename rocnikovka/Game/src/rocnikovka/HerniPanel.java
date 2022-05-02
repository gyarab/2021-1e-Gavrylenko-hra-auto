package rocnikovka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Třída představující herní panel. Dědí z JPanelu a implementuje rozhraní
 * pro naslouchání událostem ActionEvent
 * @author vita
 */
public class HerniPanel extends JPanel implements ActionListener {
    private int sirkaPanelu = 400;  // preferovaná šířka panelu
    private int vyskaPanelu = 600;  // preferovaná výška panelu
    private Timer casovac;
    private Auto auto;
    private boolean hrajeSe;    // true když se hraje, false když hra skončila
    private int citac;          // počítá body, čím déle se hraje, tím více
    private int prodlevaMeziPrekazkami; // jaká je prodleva, než se přidá další překážka
    private List<Prekazka> prekazky;    // seznam překážek

    /**
     * Konstruktor HernihoPanelu.
     */
    public HerniPanel() {
        init();
        start();
    }

    /**
     * Slouží k inicializaci HernihoPanelu
     */
    private void init() {
        this.setPreferredSize(new Dimension(sirkaPanelu, vyskaPanelu));
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        this.setFocusable(true);
    }

    /**
     * Provede inicializaci proměnných nutných ke hře a spustí timer.
     */
    private void start() {
        hrajeSe = true;
        auto = new Auto(this);
        citac = 0;
        prodlevaMeziPrekazkami = 100;
        prekazky = new ArrayList<Prekazka>();
        this.addKeyListener(auto);

        casovac = new Timer(10, this);
        casovac.start();
    }

    /**
     * Zde definujeme, co se má dít při vykreslování HernihoPanelu.
     * @param g grafický kontext
     */
    @Override
    public void paintComponent(Graphics g) {
        // pokud se hraje
        if (hrajeSe) {
            super.paintComponent(g);

            vypisCitac(g);
            auto.vykresliSe(g);

            for (int i = 0; i < prekazky.size(); i++) {
                Prekazka prek = prekazky.get(i);
                prek.vykresliSe(g);
            }
        }
        // pokud hra skončila
        else {
            vypisKonec(g);
        }
    }

    /**
     * Vypíše na panel stav čítače
     * @param g grafický kontext
     */
    private void vypisCitac(Graphics g) {
        g.drawString(String.valueOf(citac), 10, 30);
    }

    /**
     * V případě ukončení hry vypíše závěrečný text.
     * @param g grafický kontext
     */
    private void vypisKonec(Graphics g) {
        String textKonec = "G A M E   O V E R"; // text, který se vypíše při skončení hry
        Font pismo = new Font(Font.MONOSPACED, Font.BOLD, 28);
        g.setFont(pismo);
        FontMetrics fm = g.getFontMetrics(pismo);
        int sirkaTextu = fm.stringWidth(textKonec);

        g.setColor(Color.red);  // tento text bude červeně
        g.drawString(textKonec, (this.getWidth() - sirkaTextu) / 2, this.getHeight() / 2);
    }

    /**
     * Definuje, co se má stát při vzniku události ActionEvent, kterou generuje Timer.
     * @param ae událost ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {

        citac++;        // zvýší čítač o jedničku

        auto.provedPohyb();
        pridejPrekazku();
        pohniPrekazkami();

        if (isSrazka()) {
            hrajeSe = false;
            casovac.stop();
        }

        odstranPrekazkyKtereJsouMimo();

        this.repaint(); // metoda, která překreslí panel
    }

    /**
     * Odstraní překážky, které již nejsou viditelné.
     */
    private void odstranPrekazkyKtereJsouMimo() {
        for (int i = 0; i < prekazky.size(); i++) {
            Prekazka prek = prekazky.get(i);
            if (prek.isViditelny() == false) {
                prekazky.remove(prek);
            }
        }
    }

    /**
     * Zjistí, zda došlo ke srážce.
     * @return true pokud ano, fale pokud ne
     */
    private boolean isSrazka() {
        for (int i = 0; i < prekazky.size(); i++) {
            Prekazka prek = prekazky.get(i);
            if (auto.getOkraje().intersects(prek.getOkreje())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Provede pohyb všech překážek, které jsou v seznamu překazky.
     */
    private void pohniPrekazkami() {
        for (int i = 0; i < prekazky.size(); i++) {
            Prekazka prek = prekazky.get(i);
            prek.provedPohyb();
        }
    }

    /**
     * Zkontroluje prodlevu a v případě, že je čas, tak přidá další překážku.
     * S pokročilostí hry se prodleva zmenšuje a překážek přibývá.
     */
    private void pridejPrekazku() {
        if (citac == 1000) {
            prodlevaMeziPrekazkami = 70;
        }
        if (citac == 2000) {
            prodlevaMeziPrekazkami = 50;
        }
        if (citac == 3000) {
            prodlevaMeziPrekazkami = 40;
        }
        if (citac == 5000) {
            prodlevaMeziPrekazkami = 30;
        }
        if (citac == 7500) {
            prodlevaMeziPrekazkami = 20;
        }
        if (citac == 10000) {
            prodlevaMeziPrekazkami = 10;
        }

        // pokud je zbytek po dělení 0, přidá další překážku
        if ((citac % prodlevaMeziPrekazkami) == 0) {
            Prekazka prek = new Prekazka(this);
            prekazky.add(prek);
        }
    }
}