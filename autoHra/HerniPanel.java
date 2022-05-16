package autoHra;

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


public class HerniPanel extends JPanel implements ActionListener {
    private int sirkaPanelu = 400; 
    private int vyskaPanelu = 600;  
    private Timer casovac;    
    private Auto auto;
    private boolean hrajeSe;   
    private int citac;         
    private int prodlevaMeziPrekazkami; 
    private List<Prekazka> prekazky;   
    
  
    public HerniPanel() {
        init();
        start();    
    }
    
    
    private void init() {
        this.setPreferredSize(new Dimension(sirkaPanelu, vyskaPanelu));
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        this.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        this.setFocusable(true);
    }
    
  
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
    
   
    @Override
    public void paintComponent(Graphics g) {
        if (hrajeSe) {
            super.paintComponent(g);
            
            vypisCitac(g); 
            auto.vykresliSe(g);
            
            for (int i = 0; i < prekazky.size(); i++) {
                Prekazka prek = prekazky.get(i);
                prek.vykresliSe(g);
            }
        }
        else {
            vypisKonec(g);
        }
    }
    
  
    private void vypisCitac(Graphics g) {
        g.drawString(String.valueOf(citac), 10, 30);
    }

    private void vypisKonec(Graphics g) {
        String textKonec = "G A M E   O V E R"; 
        Font pismo = new Font(Font.MONOSPACED, Font.BOLD, 28);
        g.setFont(pismo);
        FontMetrics fm = g.getFontMetrics(pismo);
        int sirkaTextu = fm.stringWidth(textKonec);
        
        g.setColor(Color.red);  
        g.drawString(textKonec, (this.getWidth() - sirkaTextu) / 2, this.getHeight() / 2);
    }
    
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        citac++;       
        
        auto.provedPohyb();       
        pridejPrekazku();
        pohniPrekazkami();
        
        if (isSrazka()) {
            hrajeSe = false;
            casovac.stop();
        }
        
        odstranPrekazkyKtereJsouMimo();
          
        this.repaint(); 
    }
   
    private void odstranPrekazkyKtereJsouMimo() {
        for (int i = 0; i < prekazky.size(); i++) {
            Prekazka prek = prekazky.get(i);
            if (prek.isViditelny() == false) {
                prekazky.remove(prek);
            }
        }
    }
    
  
    private boolean isSrazka() {
        for (int i = 0; i < prekazky.size(); i++) {
            Prekazka prek = prekazky.get(i);
            if (auto.getOkraje().intersects(prek.getOkreje())) {
                return true;
            }
        }
        return false;
    }
    
   
    private void pohniPrekazkami() {
        for (int i = 0; i < prekazky.size(); i++) {
            Prekazka prek = prekazky.get(i);
            prek.provedPohyb();
        }
    }
    
  
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
        
        
        if ((citac % prodlevaMeziPrekazkami) == 0) {
            Prekazka prek = new Prekazka(this);
            prekazky.add(prek);
        }
    }   
}
