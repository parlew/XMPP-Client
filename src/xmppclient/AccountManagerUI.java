/*
 * AccountManager.java
 *
 * Created on 04 May 2008, 21:19
 */
package xmppclient;

import java.awt.Color;
import java.awt.Component;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Displays a window for managing accounts
 * @author  Lee Boynton (323326)
 */
public class AccountManagerUI extends javax.swing.JFrame
{
    /** Creates new form AccountManager */
    public AccountManagerUI()
    {
        initComponents();
        initTable();
        initColumnSizes();
    }

    /**
     * Loads values into the table
     */
    private void initTable()
    {
        for (Connection connection:Utils.getConnections())
        {
            Object[] row = new Object[]
            {
                connection.getName(),
                connection.getUsername(),
                connection.getResource(),
                connection.getHost(),
                connection.getPort(),
                new Boolean(false)
            };

            ((DefaultTableModel) accountsTable.getModel()).addRow(row);
        }

        // change the background colour of the table
        scrollPane.getViewport().setBackground(Color.white);
    }

    /**
     * Resizes table column widths to accomodate contents
     */
    private void initColumnSizes()
    {
        DefaultTableModel model = (DefaultTableModel) accountsTable.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;

        Object[] longValues = new Object[model.getColumnCount()];

        for (int row = 0; row < model.getRowCount(); row++)
        {
            for (int col = 0; col < model.getColumnCount(); col++)
            {
                if (model.getValueAt(row, col) instanceof String)
                {
                    if (longValues[col] == null || ((String) model.getValueAt(row, col)).length() > ((String) longValues[col]).length())
                    {
                        longValues[col] = model.getValueAt(row, col);
                    }
                }
                else
                {
                    longValues[col] = model.getValueAt(row, col);
                }
            }
        }

        TableCellRenderer headerRenderer = accountsTable.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 5; i++)
        {
            column = accountsTable.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                    null, column.getHeaderValue(),
                    false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = accountsTable.getDefaultRenderer(model.getColumnClass(i)).
                    getTableCellRendererComponent(
                    accountsTable, longValues[i],
                    false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        scrollPane = new javax.swing.JScrollPane();
        accountsTable = new javax.swing.JTable();
        cancelButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        titleLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Account Manager");
        setLocationByPlatform(true);

        accountsTable.setAutoCreateRowSorter(true);
        accountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Username", "Resource", "Host", "Port", "Delete"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        accountsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        accountsTable.setFocusable(false);
        accountsTable.setIntercellSpacing(new java.awt.Dimension(15, 5));
        accountsTable.setOpaque(false);
        accountsTable.setRowHeight(20);
        accountsTable.setRowSelectionAllowed(false);
        accountsTable.setShowHorizontalLines(false);
        accountsTable.setShowVerticalLines(false);
        scrollPane.setViewportView(accountsTable);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        applyButton.setText("Apply");
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        titleLabel.setText("Accounts");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                    .addComponent(titleLabel)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 411, Short.MAX_VALUE)
                        .addComponent(applyButton))
                    .addComponent(separator, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(applyButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    dispose();
}//GEN-LAST:event_cancelButtonActionPerformed

/**
 * Gets any rows which should be deleted, and deletes them
 */
private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
    DefaultTableModel model = (DefaultTableModel) accountsTable.getModel();

    for (int row = 0; row < model.getRowCount(); row++)
    {
        if (((Boolean) model.getValueAt(row, 5)) == true)
        {
            Utils.deleteConnection((String) model.getValueAt(row, 0));
        }
    }
}//GEN-LAST:event_applyButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountsTable;
    private javax.swing.JButton applyButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
