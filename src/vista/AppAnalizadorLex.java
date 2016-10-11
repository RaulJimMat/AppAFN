/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import analexico.AFD;
import analexico.AnalizadorLex;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Raul
 */
public class AppAnalizadorLex extends javax.swing.JFrame {
    
    AnalizadorLex a ;

    /**
     * Creates new form AppAnalizador
     * @param afd
     */
    public AppAnalizadorLex(AFD afd) {
        a = new AnalizadorLex(afd);
        initComponents();
//        int[] ai = afd.getTransiciones().get(0);
//        Object[][] rowData = new Object[ai.length][afd.getTransiciones().size()];
//        String[] columnNames = new String[afd.getAlfabeto().length + 1];
//        for(int i = 0; i < afd.getAlfabeto().length; i++){
//            columnNames[i] = afd.getAlfabeto()[i].toString();
//        }
//        columnNames[afd.getAlfabeto().length + 1] = "Token";
//        jTable1 = new JTable(rowData, columnNames);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        CadTF = new javax.swing.JTextField();
        getTokenButton = new javax.swing.JButton();
        returnTokenButton = new javax.swing.JButton();
        getLexemaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Analizador");

        jLabel1.setText("Escribe la cadena a analizar:");

        CadTF.setText("Entra cadena");

        getTokenButton.setText("getToken");
        getTokenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTokenButtonActionPerformed(evt);
            }
        });

        returnTokenButton.setText("returnToken");
        returnTokenButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnTokenButtonActionPerformed(evt);
            }
        });

        getLexemaButton.setText("getLexema");
        getLexemaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getLexemaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(CadTF, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(180, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(getTokenButton)
                .addGap(18, 18, 18)
                .addComponent(returnTokenButton)
                .addGap(18, 18, 18)
                .addComponent(getLexemaButton)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CadTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getTokenButton)
                    .addComponent(returnTokenButton)
                    .addComponent(getLexemaButton))
                .addContainerGap(316, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void getTokenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTokenButtonActionPerformed
        if(a.getCadena() == null){
            a.setCadena(CadTF.getText());
        }
        int tokens = a.getToken();
        JOptionPane.showMessageDialog(this, "La cadena de Tokens es: \n" + tokens);
    }//GEN-LAST:event_getTokenButtonActionPerformed

    private void getLexemaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getLexemaButtonActionPerformed
        JOptionPane.showMessageDialog(this, "La cadena de Tokens es: \n" + a.getLexema());
    }//GEN-LAST:event_getLexemaButtonActionPerformed

    private void returnTokenButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnTokenButtonActionPerformed
        a.returnToken();
    }//GEN-LAST:event_returnTokenButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CadTF;
    private javax.swing.JButton getLexemaButton;
    private javax.swing.JButton getTokenButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton returnTokenButton;
    // End of variables declaration//GEN-END:variables
}
