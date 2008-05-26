/*
 * VCardEditor.java
 *
 * Created on 03 May 2008, 20:08
 */
package xmppclient.vcard;

import xmppclient.*;
import java.awt.Component;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.VCard;
import xmppclient.images.tango.TangoIcons;
import xmppclient.tabs.VerticalRenderer;

/**
 *
 * @author Lee Boynton (323326)
 */
public class VCardEditor extends javax.swing.JFrame
{
    private VCard vCard;
    private boolean editable;
    private PersonalPanel personal;
    private HomePanel home;
    private WorkPanel work;

    /**
     * Creates a new VCard viewer/editor
     * @param vCard The VCard to view/edit
     * @param editable If the VCard infomation should be editable, i.e if it's the local user
     */
    public VCardEditor(VCard vCard, boolean editable)
    {
        this.vCard = vCard;
        this.editable = editable;
        personal = new PersonalPanel(vCard);
        home = new HomePanel(vCard);
        work = new WorkPanel(vCard);
        initComponents();
        itemsList.setSelectedIndex(0);
        setEditable(editable);
    }

    private void setEditable(boolean editable)
    {
        for (Component c : home.getComponents())
        {
            if (c instanceof JTextField)
            {
                JTextField textfield = (JTextField) c;
                textfield.setEditable(editable);
            }
        }
        for (Component c : work.getComponents())
        {
            if (c instanceof JTextField)
            {
                JTextField textfield = (JTextField) c;
                textfield.setEditable(editable);
            }
        }
        for (Component c : personal.getComponents())
        {
            if (c instanceof JTextField)
            {
                JTextField textfield = (JTextField) c;
                textfield.setEditable(editable);
            }
        }
        
        saveButton.setEnabled(editable);
    }

    private ListModel getModel()
    {
        DefaultListModel listModel = listModel = new DefaultListModel();
        listModel.addElement(new JLabel("Personal", TangoIcons.users32x32, SwingConstants.CENTER));
        listModel.addElement(new JLabel("Home", TangoIcons.home32x32, SwingConstants.CENTER));
        listModel.addElement(new JLabel("Work", TangoIcons.shirtTie32x32, SwingConstants.CENTER));
        return listModel;
    }

    private void showPanel(JPanel panel)
    {
        home.setVisible(false);
        work.setVisible(false);
        personal.setVisible(false);
        panel.setVisible(true);
        centrePanel.revalidate();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bottomPanel = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        cancelButton = new javax.swing.JButton();
        leftPanel = new javax.swing.JPanel();
        itemsScrollPane = new javax.swing.JScrollPane();
        itemsList = new javax.swing.JList();
        centrePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("VCard Information");
        setLocationByPlatform(true);
        setResizable(false);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addGap(333, 333, 333)
                        .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                    .addComponent(separator, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        getContentPane().add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        itemsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemsScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        itemsScrollPane.setMaximumSize(new java.awt.Dimension(75, 180));
        itemsScrollPane.setPreferredSize(new java.awt.Dimension(75, 180));

        itemsList.setModel(getModel());
        itemsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        itemsList.setCellRenderer(new VerticalRenderer());
        itemsList.setMaximumSize(new java.awt.Dimension(75, 180));
        itemsList.setMinimumSize(new java.awt.Dimension(75, 180));
        itemsList.setPreferredSize(new java.awt.Dimension(75, 180));
        itemsList.setSelectedIndex(0);
        itemsList.setVisibleRowCount(1);
        itemsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                itemsListValueChanged(evt);
            }
        });
        itemsScrollPane.setViewportView(itemsList);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
            .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftPanelLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(itemsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
            .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(leftPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(itemsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
        );

        getContentPane().add(leftPanel, java.awt.BorderLayout.LINE_START);

        showPanel(personal);
        centrePanel.add(personal);
        centrePanel.add(work);
        centrePanel.add(home);
        getContentPane().add(centrePanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void itemsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_itemsListValueChanged
    JLabel lbl = (JLabel) itemsList.getSelectedValue();
    
    if (lbl.getText().equals("Personal"))
    {
        showPanel(personal);
    }
    if (lbl.getText().equals("Work"))
    {
        showPanel(work);
    }
    if (lbl.getText().equals("Home"))
    {
        showPanel(home);
    }
}//GEN-LAST:event_itemsListValueChanged

private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

    //personal
    vCard.setField("TITLE", personal.titleTextField.getText());
    vCard.setFirstName(personal.firstNameTextField.getText());
    vCard.setLastName(personal.secondNameTextField.getText());
    vCard.setMiddleName(personal.middleNameTextField.getText());
    vCard.setNickName(personal.nicknameTextField.getText());
    vCard.setField("URL", personal.websiteTextField.getText());
    vCard.setJabberId(personal.jabberIDTextField.getText());
    
    //home
    vCard.setAddressFieldHome("STREET", home.streetHomeTextField.getText());
    vCard.setAddressFieldHome("TOWN", home.cityHomeTextField.getText());
    vCard.setAddressFieldHome("REGION", home.countyHomeTextField.getText());
    vCard.setAddressFieldHome("CTRY", home.countryHomeTextField.getText());
    vCard.setAddressFieldHome("PCODE", home.postCodeHomeTextField.getText());
    vCard.setEmailHome(home.emailHomeTextField.getText());
    vCard.setPhoneHome("VOICE", home.telephoneHomeTextField.getText());
    vCard.setPhoneHome("FAX", home.faxHomeTextField.getText());
    vCard.setPhoneHome("CELL", home.mobileHomeTextField.getText());
    vCard.setPhoneHome("PAGER", home.pagerHomeTextField.getText());
    
    //work
    vCard.setOrganization(work.companyTextField.getText());
    vCard.setOrganizationUnit(work.departmentTextField.getText());
    vCard.setAddressFieldWork("STREET", work.streetWorkTextField.getText());
    vCard.setAddressFieldWork("TOWN", work.cityWorkTextField.getText());
    vCard.setAddressFieldWork("REGION", work.countyWorkTextField.getText());
    vCard.setAddressFieldWork("CTRY", work.countryWorkTextField.getText());
    vCard.setAddressFieldWork("PCODE", work.postCodeWorkTextField.getText());
    vCard.setEmailWork(work.emailWorkTextField.getText());
    vCard.setPhoneWork("VOICE", work.telephoneWorkTextField.getText());
    vCard.setPhoneWork("FAX", work.faxWorkTextField.getText());
    vCard.setPhoneWork("CELL", work.mobileWorkTextField.getText());
    vCard.setPhoneWork("PAGER", work.pagerWorkTextField.getText());

    try
    {
        vCard.save(ContactListUI.connection);
    }
    catch (XMPPException ex)
    {
        JOptionPane.showMessageDialog(this,
                "Could not save VCard",
                "Error saving VCard",
                JOptionPane.ERROR_MESSAGE);
    }

    dispose();
}//GEN-LAST:event_saveButtonActionPerformed

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    dispose();
}//GEN-LAST:event_cancelButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel centrePanel;
    private javax.swing.JList itemsList;
    private javax.swing.JScrollPane itemsScrollPane;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JSeparator separator;
    // End of variables declaration//GEN-END:variables
}
