/*
 * ListMessageRenderer.java
 *
 * Created on 06 May 2008, 22:00
 */

package xmppclient;

import xmppclient.formatter.Format;

/**
 *
 * @author  Lee Boynton (323326)
 */
public class ListMessageRenderer extends javax.swing.JPanel 
{
    private ListMessage listMessage;
    private Format format;
    
    public ListMessageRenderer(ListMessage message)
    {
        this.listMessage = message;
        format = (Format) message.getMessage().getProperty("format");
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        messageLabel = new javax.swing.JLabel();

        setOpaque(false);

        nameLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        nameLabel.setText(listMessage.getName() + ":");

        messageLabel.setFont(format.getFont());
        messageLabel.setForeground(format.getColor());
        messageLabel.setText("<html>" + listMessage.getMessage().getBody() + "</html>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(nameLabel))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel messageLabel;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
