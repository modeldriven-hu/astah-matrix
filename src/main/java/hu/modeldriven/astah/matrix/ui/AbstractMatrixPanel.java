/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hu.modeldriven.astah.matrix.ui;

/**
 * @author zsolt
 */
public class AbstractMatrixPanel extends javax.swing.JPanel {

    /**
     * Creates new form AbstractMatrixPanel
     */
    public AbstractMatrixPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topPanel = new javax.swing.JPanel();
        rowElementTypeLabel = new javax.swing.JLabel();
        columnElementTypeLabel = new javax.swing.JLabel();
        rowTypeTextField = new javax.swing.JTextField();
        rowTypeSelectButton = new javax.swing.JButton();
        rowPackageLabel = new javax.swing.JLabel();
        rowPackageTextField = new javax.swing.JTextField();
        rowPackageSelectButton = new javax.swing.JButton();
        columnTypeTextField = new javax.swing.JTextField();
        columnTypeSelectButton = new javax.swing.JButton();
        columnPackageLabel = new javax.swing.JLabel();
        columnPackageSelectField = new javax.swing.JTextField();
        columnPackageSelectButton = new javax.swing.JButton();
        dependencyTypeLabel = new javax.swing.JLabel();
        dependencyTypeComboBox = new javax.swing.JComboBox<>();
        directionLabel = new javax.swing.JLabel();
        directionComboBox = new javax.swing.JComboBox<>();
        queryButton = new javax.swing.JButton();
        contentPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        matrixTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        topPanel.setBackground(new java.awt.Color(255, 255, 255));

        rowElementTypeLabel.setText("Row element type:");

        columnElementTypeLabel.setText("Column element type:");

        rowTypeTextField.setEnabled(false);

        rowTypeSelectButton.setText("Select...");

        rowPackageLabel.setText("Row package:");

        rowPackageTextField.setEnabled(false);

        rowPackageSelectButton.setText("Select...");

        columnTypeTextField.setEnabled(false);

        columnTypeSelectButton.setText("Select...");

        columnPackageLabel.setText("Column package:");

        columnPackageSelectField.setEnabled(false);

        columnPackageSelectButton.setText("Select...");

        dependencyTypeLabel.setText("Dependency type:");

        dependencyTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        directionLabel.setText("Direction:");

        directionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Both", "Row to column", "Column to row" }));

        queryButton.setText("Query");

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(directionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(directionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dependencyTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(topPanelLayout.createSequentialGroup()
                            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(topPanelLayout.createSequentialGroup()
                                    .addComponent(dependencyTypeLabel)
                                    .addGap(9, 9, 9)
                                    .addComponent(rowPackageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(rowPackageLabel)
                                .addGroup(topPanelLayout.createSequentialGroup()
                                    .addComponent(rowElementTypeLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rowTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(rowTypeSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(rowPackageSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                .addComponent(columnElementTypeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(columnTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                .addComponent(columnPackageLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(columnPackageSelectField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(columnTypeSelectButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(columnPackageSelectButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(queryButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        topPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rowPackageSelectButton, rowTypeSelectButton});

        topPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {rowPackageTextField, rowTypeTextField});

        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rowElementTypeLabel)
                    .addComponent(columnElementTypeLabel)
                    .addComponent(rowTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rowTypeSelectButton)
                    .addComponent(columnTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(columnTypeSelectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(columnPackageLabel)
                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rowPackageLabel)
                        .addComponent(rowPackageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rowPackageSelectButton)
                        .addComponent(columnPackageSelectField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(columnPackageSelectButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dependencyTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dependencyTypeLabel))
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addComponent(queryButton))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(directionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(directionLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(topPanel, java.awt.BorderLayout.NORTH);

        contentPanel.setLayout(new java.awt.BorderLayout());

        matrixTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(matrixTable);

        contentPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        add(contentPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel columnElementTypeLabel;
    private javax.swing.JLabel columnPackageLabel;
    protected javax.swing.JButton columnPackageSelectButton;
    protected javax.swing.JTextField columnPackageSelectField;
    protected javax.swing.JButton columnTypeSelectButton;
    protected javax.swing.JTextField columnTypeTextField;
    private javax.swing.JPanel contentPanel;
    protected javax.swing.JComboBox<String> dependencyTypeComboBox;
    private javax.swing.JLabel dependencyTypeLabel;
    protected javax.swing.JComboBox<String> directionComboBox;
    private javax.swing.JLabel directionLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable matrixTable;
    protected javax.swing.JButton queryButton;
    private javax.swing.JLabel rowElementTypeLabel;
    private javax.swing.JLabel rowPackageLabel;
    protected javax.swing.JButton rowPackageSelectButton;
    protected javax.swing.JTextField rowPackageTextField;
    protected javax.swing.JButton rowTypeSelectButton;
    protected javax.swing.JTextField rowTypeTextField;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
