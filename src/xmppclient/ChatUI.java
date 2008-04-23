/*
 * ChatUI.java
 *
 * Created on 14 April 2008, 23:40
 */

package xmppclient;

import org.jivesoftware.smack.RosterEntry;

/**
 *
 * @author  Lee Boynton (323326)
 */
public class ChatUI extends javax.swing.JFrame 
{
    
    /** Creates new form ChatUI */
    public ChatUI() 
    {
        initComponents();
    }
    
    public void addChat(RosterEntry user)
    {
        if(getTab(user.getUser()) == -1) 
        {
            tabs.add( new ChatPanel(user) );
        }
        setVisible(true);
    }
    
    public int getTab(String user)
    {
        for(int i = 0; i < tabs.getTabCount(); i++)
        {
            ChatPanel tab = (ChatPanel)tabs.getComponentAt(i);
            
            // return the tab that contains the JID
            if(tab.getUser().equals(user)) return i;
        }
        
        // return -1 if tab is not present
        return -1;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat");
        setLocationByPlatform(true);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatUI().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
    
}
