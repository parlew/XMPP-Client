/*
 * FileTransferUI.java
 *
 * Created on 01 May 2008, 16:06
 */

package xmppclient;

import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileSystemView;
import org.jivesoftware.smackx.filetransfer.FileTransfer;
import org.jivesoftware.smackx.filetransfer.FileTransfer.Status;

/**
 *
 * @author  Lee
 */
public class FileTransferUI extends javax.swing.JFrame
{
    private FileTransfer transfer;
            
    /** Creates new form FileTransferUI */
    public FileTransferUI(final FileTransfer transfer) throws InterruptedException 
    {
        this.transfer = transfer;
        initComponents();
        setVisible(true);
        filenameLabel.setIcon(FileSystemView.getFileSystemView().getSystemIcon(new File(transfer.getFilePath())));
        SwingWorker sw = new SwingWorker() 
        {
            @Override
            protected Object doInBackground() throws Exception
            {
                while(true) 
                {
                    if(transfer.getStatus().equals(Status.error)) 
                    {
                        errorLabel.setText(transfer.getError().getMessage());
                        finish();
                        break;
                    }

                    statusLabel.setText(transfer.getStatus().toString());
                    sizeLabel.setText(new Long(transfer.getAmountWritten()).toString() + "/" + new Long(transfer.getFileSize()).toString() );
                    progressBar.setValue((int)transfer.getAmountWritten());
                    
                    if(transfer.getStatus()==Status.refused)
                    {
                        finish();
                        break;
                    }

                    if(transfer.getFileSize() == transfer.getAmountWritten()) 
                    {
                        finish();
                        break;
                    }
                    Thread.sleep(1000);
                }
                
                return null;
            }
        };
        sw.execute();
    }
    
    public void finish()
    {
        statusLabel.setText("Complete");
        cancelButton.setEnabled(false);
        closeButton.setEnabled(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        filenameLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        closeButton = new javax.swing.JButton();
        sizeLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(transfer.getFileName());
        setLocationByPlatform(true);

        jLabel1.setText("File:");

        filenameLabel.setText(transfer.getFileName());

        progressBar.setMaximum((int)transfer.getFileSize());
        progressBar.setStringPainted(true);

        closeButton.setText("Close");
        closeButton.setEnabled(false);
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        sizeLabel.setText(String.valueOf(transfer.getFileSize()));

        statusLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        statusLabel.setText(transfer.getStatus().toString());

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filenameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(sizeLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(statusLabel)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(errorLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(filenameLabel)
                    .addComponent(sizeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(statusLabel)
                    .addComponent(errorLabel)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
    this.dispose();
}//GEN-LAST:event_closeButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed

    if(JOptionPane.showConfirmDialog(this, 
            "Do you want to cancel the transfer?", 
            "Cancel Transfer", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
    {
        transfer.cancel();
        finish();
    }
}//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel filenameLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables

}
