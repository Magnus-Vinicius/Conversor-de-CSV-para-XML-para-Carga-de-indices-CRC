package conversorcsvcargaxml;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AbrirPdfAjuda {
    
    public static void abrirAjuda() {
        File arquivoPdf = new File("ajuda.pdf");

        if (arquivoPdf.exists()) {
            try {
                Desktop.getDesktop().open(arquivoPdf);
            } catch (IOException e) {
                System.err.println("Erro ao tentar abrir o PDF: " + e.getMessage());
            }
        } else {
            System.err.println("Arquivo PDF de ajuda não foi localizado: " + arquivoPdf.getAbsolutePath());
        }
    }
}
