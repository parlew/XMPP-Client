/*
 * FileTransferChooser.java
 *
 * Created on 30 April 2008, 20:41
 */

package xmppclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.OutgoingFileTransfer;

/**
 *
 * @author  Lee
 */
public class FileTransferChooser extends javax.swing.JDialog 
{
    private String JID;
    
    /** Creates new form FileTransferChooser */
    public FileTransferChooser(java.awt.Frame parent, boolean modal, String JID) 
    {
        super(parent, modal);
        this.JID = JID;
        initComponents();
    }
    
    public int showFileChooser()
    {
        return fileChooser.showDialog(this, "Send file");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Select file");

        fileChooser.setApproveButtonText("Send to " + JID);
        fileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void fileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileChooserActionPerformed

}//GEN-LAST:event_fileChooserActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser fileChooser;
    // End of variables declaration//GEN-END:variables

}
