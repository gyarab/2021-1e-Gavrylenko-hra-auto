package autoHra;

import javax.swing.JFrame;

public class AutoHra extends JFrame {
  
    public AutoHra() {
        this.setTitle("AutoHra");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        HerniPanel panel = new HerniPanel();
        this.add(panel);     
        
        this.setResizable(false);
        this.pack();
    }
   
    public static void main(String[] args) {
        AutoHra hlavniOkno = new AutoHra();
        hlavniOkno.setVisible(true);
    }
}
