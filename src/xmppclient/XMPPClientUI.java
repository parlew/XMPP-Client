/*
 * XMPPClientUI.java
 *
 * Created on 10 April 2008, 17:25
 */

package xmppclient;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.filetransfer.FileTransferListener;
import org.jivesoftware.smackx.filetransfer.FileTransferManager;
import org.jivesoftware.smackx.filetransfer.FileTransferRequest;
import org.jivesoftware.smackx.filetransfer.IncomingFileTransfer;
import org.jivesoftware.smackx.packet.VCard;
import xmppclient.images.tango.Icons;

/**
 *
 * @author  Lee Boynton (323326)
 */
public class XMPPClientUI extends javax.swing.JFrame implements FileTransferListener
{
    public static XMPPConnection connection;
    private TrayIcon trayIcon;
    private Image appIcon = new ImageIcon(this.getClass().getResource(
                "/xmppclient/images/user.png")).getImage();
    public static ChatUI chatUI;
    
    /** Creates new form XMPPClientUI */
    public XMPPClientUI(XMPPConnection connection) 
    {
        XMPPClientUI.connection = connection;
        chatUI = new ChatUI();
        initComponents();
        initSystemTray();
        initStatusComboBox();
        contentPanel.setVisible(false);
        connection.getChatManager().addChatListener(chatUI);
        FileTransferManager manager = new FileTransferManager(connection);
        manager.addFileTransferListener(this);
        
        // toggle the sign in/out menu items
        signOutMenuItem.setEnabled(true);
              
        // set the contact list
        contactList.setListData(connection.getRoster().getEntries().toArray());
        connection.getRoster().addRosterListener(new ContactListListener(this));
        contactList.setCellRenderer(new ContactListRenderer());
        
        nicknameTextField.setText(getUserNickname(connection.getUser()));
        setAvatar();
                
        // show the content panel
        contentPanel.setVisible(true);
    }
    
    private void initStatusComboBox()
    {
        statusComboBox.removeAllItems();
        statusComboBox.addItem(new OnlinePresence());
        statusComboBox.addItem(new AwayPresence());
        statusComboBox.addItem(new BusyPresence());
        statusComboBox.setSelectedIndex(0);
    }
    
    private void initSystemTray()
    {
        if (SystemTray.isSupported()) 
        {
            SystemTray tray = SystemTray.getSystemTray();
            //Image image = Toolkit.getDefaultToolkit().getImage("images/user.png");
            
            PopupMenu popup = new PopupMenu();
            
            trayIcon = new TrayIcon(appIcon, "XMPPClient", popup);
            trayIcon.setImageAutoSize(true);
            MenuItem exitMenuItem = new MenuItem("Exit");
            MenuItem showMenuItem = new MenuItem("Show/hide");
            exitMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt)
                {
                    exit();
                }
            });
            showMenuItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt)
                {
                    toggleWindowVisibility();
                }
            });
            
            popup.add(showMenuItem);
            popup.addSeparator();
            popup.add(exitMenuItem);
            
            trayIcon.addMouseListener(new MouseAdapter () 
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    // check the correct button was pressed
                    if(e.getButton() != MouseEvent.BUTTON1) return;
                    
                    // toggle only once on double click
                    if(e.getClickCount() == 2) return;

                    toggleWindowVisibility();
                }
            });
            
            try 
            {
                tray.add(trayIcon);
            } 
            catch (AWTException e) 
            {
                System.err.println("TrayIcon could not be added.");
            }
        }
    }
    
    public void toggleWindowVisibility()
    {
        if(XMPPClientUI.this.isVisible())
        {
            XMPPClientUI.this.setVisible(false);
        }
        else
        {
            XMPPClientUI.this.setVisible(true);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        contentPanel = new javax.swing.JPanel();
        contactListScrollPane = new javax.swing.JScrollPane();
        contactList = new javax.swing.JList();
        toolBar = new javax.swing.JToolBar();
        addContactButton = new javax.swing.JButton();
        vCardButton = new javax.swing.JButton();
        avatarButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        hoverTextLabel = new javax.swing.JLabel();
        nicknameTextField = new javax.swing.JTextField();
        statusComboBox = new javax.swing.JComboBox();
        avatarLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        signOutMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        sendFileMenuItem = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem1.setText("Item");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("XMPPClient");
        setIconImage(appIcon);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(300, 400));
        setName("XMPPClient"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        contactList.setModel(new DefaultListModel());
        contactList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        contactList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactListMouseClicked(evt);
            }
        });
        contactList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                contactListValueChanged(evt);
            }
        });
        contactListScrollPane.setViewportView(contactList);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        addContactButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/xmppclient/images/user_add.png"))); // NOI18N
        addContactButton.setToolTipText("Add contact");
        addContactButton.setFocusable(false);
        addContactButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addContactButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addContactButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addContactButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addContactButtonMouseExited(evt);
            }
        });
        addContactButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContactButtonActionPerformed(evt);
            }
        });
        toolBar.add(addContactButton);

        vCardButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/xmppclient/images/vcard_edit.png"))); // NOI18N
        vCardButton.setToolTipText("Edit VCard");
        vCardButton.setFocusable(false);
        vCardButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        vCardButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        vCardButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vCardButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                vCardButtonMouseExited(evt);
            }
        });
        vCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vCardButtonActionPerformed(evt);
            }
        });
        toolBar.add(vCardButton);

        avatarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/xmppclient/images/image.png"))); // NOI18N
        avatarButton.setToolTipText("Change avatar");
        avatarButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                avatarButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                avatarButtonMouseExited(evt);
            }
        });
        avatarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avatarButtonActionPerformed(evt);
            }
        });
        toolBar.add(avatarButton);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/xmppclient/images/cog.png"))); // NOI18N
        jButton1.setToolTipText("Edit preferences");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1MouseExited(evt);
            }
        });
        toolBar.add(jButton1);

        hoverTextLabel.setFont(new java.awt.Font("Tahoma", 0, 10));
        hoverTextLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 3, 0, 0));
        toolBar.add(hoverTextLabel);

        nicknameTextField.setToolTipText("Press enter to set the nickname");
        nicknameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nicknameTextFieldActionPerformed(evt);
            }
        });

        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });

        avatarLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(160, 160, 160), 1, true));

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contactListScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusComboBox, 0, 297, Short.MAX_VALUE)
                            .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nicknameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(avatarLabel)))
                .addContainerGap())
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(contentPanelLayout.createSequentialGroup()
                        .addComponent(nicknameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(avatarLabel))
                .addGap(11, 11, 11)
                .addComponent(contactListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );

        fileMenu.setText("File");

        signOutMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/xmppclient/images/tango/log-out-22x22.png"))); // NOI18N
        signOutMenuItem.setText("Sign out");
        signOutMenuItem.setEnabled(false);
        signOutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(signOutMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/xmppclient/images/tango/system-shutdown-22x22.png"))); // NOI18N
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        sendFileMenuItem.setText("Tools");
        sendFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendFileMenuItemActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Send file...");
        jMenuItem2.setEnabled(false);
        sendFileMenuItem.add(jMenuItem2);

        menuBar.add(sendFileMenuItem);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        exit();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void signOutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signOutMenuItemActionPerformed
        signOut();
}//GEN-LAST:event_signOutMenuItemActionPerformed

    private void contactListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactListMouseClicked
        if(evt.getClickCount() == 2)
        {
            RosterEntry rosterEntry = (RosterEntry)contactList.getSelectedValue();
            
            connection.getChatManager().createChat(rosterEntry.getUser(), chatUI);
        }
        JComponent c = (JComponent)contactList.getParent();
        c.revalidate();
    }//GEN-LAST:event_contactListMouseClicked

    private void nicknameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicknameTextFieldActionPerformed
        VCard vCard = new VCard();
        try
        {
            // first try to get stored VCard
            vCard.load(connection);
            System.out.println("Loaded nickname");
        }
        catch(XMPPException e) { } // no vcard
        
        vCard.setNickName(nicknameTextField.getText());
        
        if(nicknameTextField.getText().equals("")) vCard.setNickName(connection.getUser());
        
        try
        {
            // send the new nickname
            vCard.save(connection);
            System.out.println("Saved nickname");
        }
        catch(XMPPException e)
        {
            e.printStackTrace();
        }
        
        connection.sendPacket((Presence)statusComboBox.getSelectedItem());
        
        if(nicknameTextField.getText().equals("")) nicknameTextField.setText(connection.getUser());
    }//GEN-LAST:event_nicknameTextFieldActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exit();
    }//GEN-LAST:event_formWindowClosing

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // check the connection has been made
        if(connection != null && connection.isConnected())
            connection.sendPacket((Presence)statusComboBox.getSelectedItem());
    }//GEN-LAST:event_statusComboBoxActionPerformed

private void avatarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avatarButtonActionPerformed
    new AvatarChooser(this, false);
}//GEN-LAST:event_avatarButtonActionPerformed

private void contactListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_contactListValueChanged
    sendFileMenuItem.setEnabled(true);
}//GEN-LAST:event_contactListValueChanged

private void sendFileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendFileMenuItemActionPerformed
    contactList.getSelectedValue();
}//GEN-LAST:event_sendFileMenuItemActionPerformed

private void avatarButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_avatarButtonMouseEntered
    setHoverText(evt);
}//GEN-LAST:event_avatarButtonMouseEntered

private void addContactButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addContactButtonMouseEntered
    setHoverText(evt);
}//GEN-LAST:event_addContactButtonMouseEntered

private void vCardButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCardButtonMouseEntered
    setHoverText(evt);
}//GEN-LAST:event_vCardButtonMouseEntered

private void avatarButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_avatarButtonMouseExited
    clearHoverText();
}//GEN-LAST:event_avatarButtonMouseExited

private void addContactButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addContactButtonMouseExited
    clearHoverText();
}//GEN-LAST:event_addContactButtonMouseExited

private void vCardButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vCardButtonMouseExited
    clearHoverText();
}//GEN-LAST:event_vCardButtonMouseExited

private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseEntered
    setHoverText(evt);
}//GEN-LAST:event_jButton1MouseEntered

private void jButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseExited
    clearHoverText();
}//GEN-LAST:event_jButton1MouseExited

private void vCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vCardButtonActionPerformed
    new VCardEditor().setVisible(true);
}//GEN-LAST:event_vCardButtonActionPerformed

private void addContactButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContactButtonActionPerformed
    showAddContactDialog();
}//GEN-LAST:event_addContactButtonActionPerformed
 
    private void setHoverText(java.awt.event.MouseEvent evt)
    {
        hoverTextLabel.setText(((JButton)evt.getSource()).getToolTipText());
    }
    
    private void clearHoverText()
    {
        hoverTextLabel.setText("");
    }
    
    private void showAddContactDialog()
    {
        JOptionPane.showInputDialog(this, "Enter the JID of the contact you wish to add", "Add user", JOptionPane.PLAIN_MESSAGE, Icons.users32x32, null, null);
    }
    
    private void exit()
    {
        requestFocus();
        
        // if the user is signed in then sign them out before exiting
        if(connection != null) 
        {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Do you wish to sign out and close XMPPClient?",
                    "Close XMPPClient",
                    JOptionPane.OK_CANCEL_OPTION);
            
            if(confirm == JOptionPane.OK_OPTION) signOut();
            else return;
        }
        
        System.exit(0);
    }
    
    private void signIn()
    {
        new XMPPClientSignInUI(this).setVisible(true);
        
        // check if user clicked cancel
        if(connection == null || !connection.isConnected() || !connection.isAuthenticated()) return;
        
        
    }
    
    public void setAvatar()
    {
        avatarLabel.setIcon(Utils.getAvatar(85));
        if(avatarLabel.getIcon() == null) avatarLabel.setVisible(false);
    }
    
    public void setAvatar(ImageIcon icon)
    {
        avatarLabel.setIcon(Utils.resizeImage(icon, 85));
        if(avatarLabel.getIcon() == null) avatarLabel.setVisible(false);
    }
    
    public String getUserNickname(String user)
    {
        VCard VCard = new VCard();
        
        try 
        { 
            VCard.load(connection);
            user = VCard.getNickName();
        }
        catch(XMPPException e) {}
        
        return user;
    }
    
    public XMPPConnection getConnection()
    {
        return connection;
    }
    
    public void updateContactList()
    {
        SwingUtilities.invokeLater( new Runnable() {
            @Override
                public void run()
                {
                    try
                    {
                        contactList.setListData(connection.getRoster().getEntries().toArray());
                        contactList.updateUI();
                    }
                    catch (Exception e) {}
                }
        });
    }
    
    private void signOut()
    {
        contactList.setModel(new DefaultListModel());
        connection.disconnect();
        connection = null;
        signOutMenuItem.setEnabled(false);
        contentPanel.setVisible(false);
        new XMPPClient().setVisible(true);
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addContactButton;
    private javax.swing.JButton avatarButton;
    private javax.swing.JLabel avatarLabel;
    private javax.swing.JList contactList;
    private javax.swing.JScrollPane contactListScrollPane;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JLabel hoverTextLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField nicknameTextField;
    private javax.swing.JMenu sendFileMenuItem;
    private javax.swing.JMenuItem signOutMenuItem;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton vCardButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void fileTransferRequest(FileTransferRequest request)
    {
        Object[] options = {"Accept", "Reject"};
        String desc = request.getDescription();
        if(desc == null) desc = "None entered";
        
        int option = JOptionPane.showOptionDialog(this, 
                "File transfer request received from " + request.getRequestor()
                + "\nFilename: " + request.getFileName()
                + "\nDescription: " + request.getDescription()
                + "\nWould you like to accept or reject it?", 
                "File Transfer Request", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                options, 
                options[0]);
        
        if(option == JOptionPane.YES_OPTION) 
        {
            IncomingFileTransfer transfer = request.accept();
            (new File("received")).mkdir();
            
            try
            {
                transfer.recieveFile(new File("received/"+request.getFileName()));
                new FileTransferUI(transfer);
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }            
            catch (XMPPException ex)
            {
                ex.printStackTrace();
            }
        }
        else request.reject();
    }
}
