package br.com.agenda.Frames;

/*Imports de class*/
import br.com.agenda.BancodeDados.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public final class ListaContatos extends javax.swing.JFrame {

    Conexao c;
    public ListaContatos() {
        initComponents();
        c = new Conexao();
        jTAListaContatos.setEditable(false);
        this.setLocationRelativeTo(null);
    }
    
    public void Relatorios() {
        String sql = "SELECT * FROM usuario ORDER BY id,nome DESC";
        try {
            if (this.c.getConectMySQl() != null) {
                Statement smt = this.c.getConectMySQl().createStatement();
                ResultSet rs = smt.executeQuery(sql);
                while (rs.next()) {
                    this.jTAListaContatos.append(" Id: " + rs.getInt("id") + " "
                            + "\n Nome: " + rs.getString("nome").toUpperCase() 
                            + "\n Endereco: " + rs.getString("endereco").toUpperCase() 
                            + "\n E-mail: " + rs.getString("email").toUpperCase()
                            + "\n Telefone Residencial: " + rs.getString("residencial")
                            + "\n Telefone: " + rs.getString("telefone")
                            + "\n Cpf: " + rs.getString("cpf") 
                            + "\n Sobre: \n" + rs.getString("sobre").toUpperCase()
                            + "\n___________________________________________________\n");
                }
            } else {
                JOptionPane.showMessageDialog(null, " Problemas na comunicão com o Banco de dados ");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(ListaContatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTAListaContatos = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("LISTA DE CONTATOS");

        jTAListaContatos.setColumns(20);
        jTAListaContatos.setRows(5);
        jScrollPane1.setViewportView(jTAListaContatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(jLabel1)
                .addContainerGap(112, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListaContatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaContatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaContatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaContatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new ListaContatos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAListaContatos;
    // End of variables declaration//GEN-END:variables
}
