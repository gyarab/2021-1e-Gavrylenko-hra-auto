package rocnikovka;
import javax.swing.JFrame;

/**
 * Třída obsahující hlavní okno programu a metodu main.
 * @author vita
 */
public class AutoHra extends JFrame {

    /**
     * Konstruktor hlavního okna programu.
     */
    public AutoHra() {
        this.setTitle("AutoHra");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        HerniPanel panel = new HerniPanel();
        this.add(panel);

        this.setResizable(false);
        this.pack();
    }

    /**
     * Vstupní bod programu.
     * Vytvoří hlavní okno s programem.
     * @param args - v tomto programu nepoužívám
     */
    public static void main(String[] args) {
        AutoHra hlavniOkno = new AutoHra();
        hlavniOkno.setVisible(true);
    }
}
