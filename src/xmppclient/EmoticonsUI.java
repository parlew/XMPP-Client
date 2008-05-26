/*
 * EmoticonsUI.java
 *
 * Created on 07 May 2008, 21:50
 */
package xmppclient;

import java.awt.Component;
import java.awt.Point;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import xmppclient.emoticons.Emoticon;
import xmppclient.emoticons.Emoticons;
import xmppclient.images.Icons;

/**
 *
 * @author  Lee Boynton (323326)
 */
public class EmoticonsUI extends javax.swing.JDialog
{
    private JTextArea textArea;
    private CustomEmoticonDialog customEmoticonDialog = new CustomEmoticonDialog((JFrame)null, true);
    
    /** 
     * Creates new form EmoticonsUI
     * @param owner The JFrame owner
     * @param location The location on screen this dialog should display at
     * @param textArea The textarea which the emoticon key sequence should be
     * inserted into.
     */
    public EmoticonsUI(JFrame owner, JTextArea textArea, Point location)
    {
        super(owner);
        this.textArea = textArea;
        initComponents();
        setVisible(true);
        setLocation(location);
        pack();
    }
    
    /**
     * Updates the list of emoticons
     */
    public void updateEmoticons()
    {
        DefaultListModel model = new DefaultListModel();
        
        model.addElement(new Emoticon("Add emoticon", Icons.add, ""));
        
        for(Emoticon e:Emoticons.getEmoticons())
        {
            model.addElement(e);
        }
        
        emoticonsList.setModel(model);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emoticonsScrollPane = new javax.swing.JScrollPane();
        emoticonsList = new javax.swing.JList();

        setUndecorated(true);

        emoticonsScrollPane.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        updateEmoticons();
        emoticonsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        emoticonsList.setCellRenderer(new EmoticonsListRenderer());
        emoticonsList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        emoticonsList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emoticonsListMouseClicked(evt);
            }
        });
        emoticonsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                emoticonsListValueChanged(evt);
            }
        });
        emoticonsList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                emoticonsListFocusLost(evt);
            }
        });
        emoticonsScrollPane.setViewportView(emoticonsList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emoticonsScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(emoticonsScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void emoticonsListFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emoticonsListFocusLost
    if(!customEmoticonDialog.isVisible()) dispose();
}//GEN-LAST:event_emoticonsListFocusLost

private void emoticonsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_emoticonsListValueChanged
    if(evt.getValueIsAdjusting()) return;
}//GEN-LAST:event_emoticonsListValueChanged

private void emoticonsListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emoticonsListMouseClicked
    
    Emoticon emoticon = (Emoticon) emoticonsList.getSelectedValue();
    
    if(emoticon.getName().equals("Add emoticon"))
    {
        customEmoticonDialog.setVisible(true);
        updateEmoticons();
        return;
    }
    
    textArea.append(emoticon.getSequence());
}//GEN-LAST:event_emoticonsListMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList emoticonsList;
    private javax.swing.JScrollPane emoticonsScrollPane;
    // End of variables declaration//GEN-END:variables
    
    private class EmoticonsListRenderer extends DefaultListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList list, 
                Object object, 
                int index, 
                boolean isSelected, 
                boolean cellHasFocus)
        {  
            Emoticon emoticon = (Emoticon) object;
            JLabel lbl = (JLabel) super.getListCellRendererComponent(list, object, index, isSelected, cellHasFocus);
            lbl.setIcon(emoticon.getIcon());
            return lbl;
        }
    }
}
