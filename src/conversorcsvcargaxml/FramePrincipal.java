package conversorcsvcargaxml;

import static conversorcsvcargaxml.CargaAlteracaoCasamento.cargaAlteracaoCasamento;
import static conversorcsvcargaxml.CargaAlteracaoNascimento.cargaAlteracaoNascimento;
import static conversorcsvcargaxml.CargaAlteracaoObito.cargaAlteracaoObito;
import static conversorcsvcargaxml.CargaInclusaoCasamento.cargaInclusaoCasamento;
import static conversorcsvcargaxml.CargaInclusaoNascimento.cargaInclusaoNascimento;
import static conversorcsvcargaxml.CargaIncusaoObito.cargaInclusaoObito;
import java.awt.Image;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import jnafilechooser.api.JnaFileChooser;

public class FramePrincipal extends javax.swing.JFrame {

    public FramePrincipal() {
        initComponents();
        PrintStream printStream = new PrintStream(new TextAreaOutputStream(txtLog));
        System.setOut(printStream);
        System.setErr(printStream);

        try {
            Image icon = ImageIO.read(getClass().getResource("/conversorcsvcargaxml/icone.png"));
            this.setIconImage(icon);
        } catch (IOException e) {
        }

        this.setVisible(true);
    }

    private void processar() throws ParserConfigurationException, TransformerException {
        switch (cmbTipoCarga.getSelectedIndex()) {
            case 0 -> {
                switch (cmbTipoRegistro.getSelectedIndex()) {
                    case 0 -> {
                        if (cargaInclusaoNascimento(txtCns.getText(), txtCaminhoEntrada.getText(), txtCaminhoSaida.getText())) {
                            JOptionPane.showMessageDialog(this, "Registros processados com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Não foi possível processar os registros.", "Falha", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 1 -> {
                        if (cargaInclusaoCasamento(txtCns.getText(), txtCaminhoEntrada.getText(), txtCaminhoSaida.getText())) {
                            JOptionPane.showMessageDialog(this, "Registros processados com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Não foi possível processar os registros.", "Falha", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 2 -> {
                        if (cargaInclusaoObito(txtCns.getText(), txtCaminhoEntrada.getText(), txtCaminhoSaida.getText())) {
                            JOptionPane.showMessageDialog(this, "Registros processados com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Não foi possível processar os registros.", "Falha", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
            case 1 -> {
                switch (cmbTipoRegistro.getSelectedIndex()) {
                    case 0 -> {
                        if (cargaAlteracaoNascimento(txtCns.getText(), txtCaminhoEntrada.getText(), txtCaminhoSaida.getText())) {
                            JOptionPane.showMessageDialog(this, "Registros processados com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Não foi possível processar os registros.", "Falha", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 1 -> {
                        if (cargaAlteracaoCasamento(txtCns.getText(), txtCaminhoEntrada.getText(), txtCaminhoSaida.getText())) {
                            JOptionPane.showMessageDialog(this, "Registros processados com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Não foi possível processar os registros.", "Falha", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    case 2 -> {
                        if (cargaAlteracaoObito(txtCns.getText(), txtCaminhoEntrada.getText(), txtCaminhoSaida.getText())) {
                            JOptionPane.showMessageDialog(this, "Registros processados com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Não foi possível processar os registros.", "Falha", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flatLabel1 = new com.formdev.flatlaf.extras.components.FlatLabel();
        cmbTipoCarga = new com.formdev.flatlaf.extras.components.FlatComboBox();
        flatLabel2 = new com.formdev.flatlaf.extras.components.FlatLabel();
        cmbTipoRegistro = new com.formdev.flatlaf.extras.components.FlatComboBox();
        flatLabel3 = new com.formdev.flatlaf.extras.components.FlatLabel();
        txtCaminhoEntrada = new com.formdev.flatlaf.extras.components.FlatTextField();
        btnEntrada = new com.formdev.flatlaf.extras.components.FlatButton();
        flatLabel4 = new com.formdev.flatlaf.extras.components.FlatLabel();
        txtCaminhoSaida = new com.formdev.flatlaf.extras.components.FlatTextField();
        btnSaida = new com.formdev.flatlaf.extras.components.FlatButton();
        flatLabel6 = new com.formdev.flatlaf.extras.components.FlatLabel();
        txtCns = new com.formdev.flatlaf.extras.components.FlatTextField();
        btnProcessar = new com.formdev.flatlaf.extras.components.FlatButton();
        jSeparator1 = new javax.swing.JSeparator();
        flatScrollPane1 = new com.formdev.flatlaf.extras.components.FlatScrollPane();
        txtLog = new com.formdev.flatlaf.extras.components.FlatTextArea();
        flatLabel5 = new com.formdev.flatlaf.extras.components.FlatLabel();
        btnAjuda = new com.formdev.flatlaf.extras.components.FlatButton();
        btnAjuda1 = new com.formdev.flatlaf.extras.components.FlatButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Conversor CSV em XML para carga de índices no CRC");
        setMaximumSize(new java.awt.Dimension(823, 468));
        setMinimumSize(new java.awt.Dimension(823, 468));
        setResizable(false);

        flatLabel1.setText("Tipo de carga:");

        cmbTipoCarga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Inclusão", "Alteração" }));

        flatLabel2.setText("Tipo de registro:");

        cmbTipoRegistro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nascimento", "Casamento", "Óbito" }));

        flatLabel3.setText("Arquivo de entrada (CSV):");

        txtCaminhoEntrada.setToolTipText("Use o botão ao lado para abrir o navegador de arquivos");

        btnEntrada.setText("...");
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });

        flatLabel4.setText("Arquivo de saída (XML):");

        txtCaminhoSaida.setToolTipText("Use o botão ao lado para abrir o navegador de arquivos");

        btnSaida.setText("...");
        btnSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaidaActionPerformed(evt);
            }
        });

        flatLabel6.setText("CNS:");

        txtCns.setToolTipText("Digite todos os 6 dígitos do CNS da Serventia");
        txtCns.setPlaceholderText("Somente números");

        btnProcessar.setText("Processar");
        btnProcessar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessarActionPerformed(evt);
            }
        });

        txtLog.setEditable(false);
        txtLog.setColumns(20);
        txtLog.setRows(5);
        flatScrollPane1.setViewportView(txtLog);

        flatLabel5.setText("Logs");

        btnAjuda.setText("?");
        btnAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjudaActionPerformed(evt);
            }
        });

        btnAjuda1.setText("Sobre");
        btnAjuda1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjuda1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnProcessar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(flatScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(flatLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(flatLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(flatLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(flatLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(flatLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(flatLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCaminhoEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCaminhoSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cmbTipoCarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbTipoRegistro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(txtCns, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 436, Short.MAX_VALUE)
                                .addComponent(btnAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAjuda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flatLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoCarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjuda1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flatLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTipoRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flatLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flatLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaminhoEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flatLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCaminhoSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnProcessar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(flatLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(flatScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Arquivo CSV", "csv");
        fileChooser.setMode(JnaFileChooser.Mode.Files);
        fileChooser.showOpenDialog(this);
        if (fileChooser.getSelectedFile() != null) {
            txtCaminhoEntrada.setText(fileChooser.getSelectedFile().getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(this, "Não foi selecionado arquivo de entrada.");
        }
        //JOptionPane.showMessageDialog(this, "Operação cancelada!\nO registro não foi salvo.", "Atenção!", JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_btnEntradaActionPerformed

    private void btnSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaidaActionPerformed
        JnaFileChooser fileChooser = new JnaFileChooser();
        fileChooser.addFilter("Arquivo XML", "xml");
        fileChooser.setMode(JnaFileChooser.Mode.Files);
        fileChooser.showSaveDialog(this);
        if (fileChooser.getSelectedFile() != null) {
            txtCaminhoSaida.setText(fileChooser.getSelectedFile().getAbsolutePath() + ".xml");
        } else {
            JOptionPane.showMessageDialog(this, "Não foi selecionado arquivo de saída.");
        }
    }//GEN-LAST:event_btnSaidaActionPerformed

    private void btnProcessarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessarActionPerformed
        StringBuilder mensagem = new StringBuilder("");
        if (txtCns.getText().isBlank()) {
            mensagem.append("Informe o CNS da Serventia");
        }
        if (txtCaminhoEntrada.getText().isBlank()) {
            mensagem.append("\nInforme o arquivo de entrada");
        } 
        if (txtCaminhoSaida.getText().isBlank()) {
            mensagem.append("\nInforme o arquivo de saida");
        } 
        
        if(mensagem.isEmpty()) {
            try {
                processar();
            } catch (ParserConfigurationException | TransformerException ex) {
                System.getLogger(FramePrincipal.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, mensagem);
        }
        
            
        
    }//GEN-LAST:event_btnProcessarActionPerformed

    private void btnAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjudaActionPerformed
        AbrirPdfAjuda.abrirAjuda();
    }//GEN-LAST:event_btnAjudaActionPerformed

    private void btnAjuda1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjuda1ActionPerformed
        AbrirPdfSobre.abrirSobre();
    }//GEN-LAST:event_btnAjuda1ActionPerformed

    public static class TextAreaOutputStream extends OutputStream {

        private final JTextArea textArea;
        private final StringBuilder buffer = new StringBuilder();

        public TextAreaOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            if (b == '\r') {
                return; // ignora carriage return
            }
            if (b == '\n') {
                final String text = buffer.toString() + "\n";
                SwingUtilities.invokeLater(() -> textArea.append(text));
                buffer.setLength(0);
            } else {
                buffer.append((char) b);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.formdev.flatlaf.extras.components.FlatButton btnAjuda;
    private com.formdev.flatlaf.extras.components.FlatButton btnAjuda1;
    private com.formdev.flatlaf.extras.components.FlatButton btnEntrada;
    private com.formdev.flatlaf.extras.components.FlatButton btnProcessar;
    private com.formdev.flatlaf.extras.components.FlatButton btnSaida;
    private com.formdev.flatlaf.extras.components.FlatComboBox cmbTipoCarga;
    private com.formdev.flatlaf.extras.components.FlatComboBox cmbTipoRegistro;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel1;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel2;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel3;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel4;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel5;
    private com.formdev.flatlaf.extras.components.FlatLabel flatLabel6;
    private com.formdev.flatlaf.extras.components.FlatScrollPane flatScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private com.formdev.flatlaf.extras.components.FlatTextField txtCaminhoEntrada;
    private com.formdev.flatlaf.extras.components.FlatTextField txtCaminhoSaida;
    private com.formdev.flatlaf.extras.components.FlatTextField txtCns;
    private com.formdev.flatlaf.extras.components.FlatTextArea txtLog;
    // End of variables declaration//GEN-END:variables
}
