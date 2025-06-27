package conversorcsvcargaxml;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class AbrirPdfSobre {
    
    public static void abrirSobre() {
        File arquivoPdf = new File("sobre.pdf");

        if (arquivoPdf.exists()) {
            try {
                Desktop.getDesktop().open(arquivoPdf);
            } catch (IOException e) {
                System.err.println("Erro ao tentar abrir o PDF: " + e.getMessage());
            }
        } else {
            System.err.println("Arquivo PDF de sobre não foi localizado: " + arquivoPdf.getAbsolutePath());
        }
    }
}
