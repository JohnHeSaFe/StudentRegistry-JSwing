/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package JFrames;

import javastudent.Student;
import javastudent.StudentRegistry;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author henar
 */
public class RemoveStudentDialog extends javax.swing.JDialog {

    /**
     * Creates new form RemoveStudentDialog
     */
    public RemoveStudentDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        System.out.println("Opened RemoveStudentDialog");
        
        // Change the size of the columns
        studentsTable.getColumnModel().getColumn(0).setPreferredWidth(100); 
        studentsTable.getColumnModel().getColumn(1).setPreferredWidth(100); 
        studentsTable.getColumnModel().getColumn(2).setPreferredWidth(30);  
        studentsTable.getColumnModel().getColumn(3).setPreferredWidth(200); 
        studentsTable.getColumnModel().getColumn(4).setPreferredWidth(100); 
        
        // Get the model to edit data
        DefaultTableModel model = (DefaultTableModel) studentsTable.getModel();

        // Add students data from the file to the table
        // Add NID to the combo box
        for (Student student : StudentRegistry.getStudentsFromFile()) {
            model.addRow(new Object[]{
                student.getFirstName(), student.getLastName(), student.getAge(), student.getCourse(), student.getNid()
            });
            nidComboBox.addItem(student.getNid());
        }
        
        // Default value of the combo box none
        nidComboBox.setSelectedIndex(-1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelTitle1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        studentsTable = new javax.swing.JTable();
        nidComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        ConfirmButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabelTitle1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        jLabelTitle1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle1.setText("Remove student");

        studentsTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        studentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Surname", "Age", "Course", "NID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentsTable.setToolTipText("");
        studentsTable.setCellSelectionEnabled(true);
        jScrollPane2.setViewportView(studentsTable);

        jLabel1.setText("Select NID to remove: ");

        ConfirmButton.setText("Confirm Removal");
        ConfirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitle1, javax.swing.GroupLayout.DEFAULT_SIZE, 867, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(ConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabelTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nidComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(ConfirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmButtonActionPerformed
        // TODO add your handling code here:
        String selectedNID = (String) nidComboBox.getSelectedItem();
        
        // Error if not selected a student
        if (selectedNID == null) {
            JOptionPane.showMessageDialog(this, "Select a NID before removing a student", "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("Error no NID selected in RemoveStudentDialog");
            return;    
        }
        
        // Remove the student and inform
        StudentRegistry.removeStudent(selectedNID);
        JOptionPane.showMessageDialog(this, "Student removed succesfully", "Info", JOptionPane.INFORMATION_MESSAGE);
        
        // Remove NID from selection after removing the student
        nidComboBox.removeItem(selectedNID);
        nidComboBox.setSelectedIndex(-1);
        
        // Edit table to remove the information of the student comparing with the NID of the combo box
        DefaultTableModel tableModel = (DefaultTableModel) studentsTable.getModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 4).equals(selectedNID)) {
                tableModel.removeRow(i);
            }
        }
        
        System.out.println("Student removed in RemoveStudentDialog");
    }//GEN-LAST:event_ConfirmButtonActionPerformed

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
            java.util.logging.Logger.getLogger(RemoveStudentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemoveStudentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemoveStudentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoveStudentDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RemoveStudentDialog dialog = new RemoveStudentDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ConfirmButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTitle1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> nidComboBox;
    private javax.swing.JTable studentsTable;
    // End of variables declaration//GEN-END:variables
}
