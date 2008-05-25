/*
 * AudioLibraryUI.java
 *
 * Created on 25 May 2008, 18:13
 */
package xmppclient.audio.ui;

import xmppclient.audio.*;
import xmppclient.audio.ui.AudioLibraryPanel;
import java.awt.BorderLayout;
import org.jivesoftware.smack.RosterEntry;

/**
 *
 * @author  Lee Boynton (323326)
 */
public class AudioLibraryUI extends javax.swing.JFrame
{
    private AudioManager manager;
    private RosterEntry entry;
    
    /** Creates new form AudioLibraryUI */
    public AudioLibraryUI(AudioManager manager, RosterEntry entry)
    {
        this.manager = manager;
        this.entry = entry;
        AudioLibraryPanel panel = new AudioLibraryPanel(manager, entry);
        initComponents();
        add(panel, BorderLayout.CENTER);
        pack();
        panel.refresh();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Audio Library");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
