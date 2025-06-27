package conversorcsvcargaxml;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AppMain {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.out.println("Não foi possível inicializar o FlatLaf");
        }
        new FramePrincipal();
    }
    
}
