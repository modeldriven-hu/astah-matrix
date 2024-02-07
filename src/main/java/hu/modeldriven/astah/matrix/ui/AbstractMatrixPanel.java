package hu.modeldriven.astah.matrix.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;

public class AbstractMatrixPanel extends JPanel {

    private static final String TEXT_FIELD_SIZE = ",width 170:170:170";
    protected javax.swing.JButton columnPackageSelectButton;
    protected javax.swing.JTextField columnPackageTextField;
    protected javax.swing.JButton columnTypeSelectButton;
    protected javax.swing.JTextField columnTypeTextField;
    protected javax.swing.JButton dependencySelectButton;
    protected javax.swing.JTextField dependencyTextField;
    protected javax.swing.JTable matrixTable;
    protected javax.swing.JButton queryButton;
    protected javax.swing.JButton rowPackageSelectButton;
    protected javax.swing.JTextField rowPackageTextField;
    protected javax.swing.JButton rowTypeSelectButton;
    protected javax.swing.JTextField rowTypeTextField;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel columnElementTypeLabel;
    private javax.swing.JLabel columnPackageLabel;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel dependencyTypeLabel;
    protected javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel rowElementTypeLabel;
    private javax.swing.JLabel rowPackageLabel;

    public AbstractMatrixPanel() {
        super();
        initComponents();
    }

    private void initComponents() {

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);

        rowElementTypeLabel = new JLabel();
        rowTypeTextField = new JTextField();
        rowTypeSelectButton = new JButton();
        columnElementTypeLabel = new JLabel();
        columnTypeTextField = new JTextField();
        columnTypeSelectButton = new JButton();
        rowPackageLabel = new JLabel();
        rowPackageTextField = new JTextField();
        rowPackageSelectButton = new JButton();
        columnPackageLabel = new JLabel();
        columnPackageTextField = new JTextField();
        columnPackageSelectButton = new JButton();
        dependencyTypeLabel = new JLabel();
        dependencyTextField = new JTextField();
        dependencySelectButton = new JButton();
        queryButton = new JButton();
        scrollPane = new JScrollPane();
        matrixTable = new JTable();

        setLayout(new BorderLayout());

        contentPanel.setLayout(new MigLayout(
                "fill,hidemode 3",
                // columns
                "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]" +
                        "[fill]",
                // rows
                "[]" +
                        "[]" +
                        "[]" +
                        "[]"));

        rowElementTypeLabel.setText("Row element type:");
        contentPanel.add(rowElementTypeLabel, "cell 0 0");

        rowTypeTextField.setEnabled(false);
        contentPanel.add(rowTypeTextField, "cell 1 0" + TEXT_FIELD_SIZE);

        rowTypeSelectButton.setText("Select...");
        contentPanel.add(rowTypeSelectButton, "cell 2 0");

        columnElementTypeLabel.setText("Column element type:");
        contentPanel.add(columnElementTypeLabel, "cell 4 0");

        columnTypeTextField.setEnabled(false);
        contentPanel.add(columnTypeTextField, "cell 5 0" + TEXT_FIELD_SIZE);

        columnTypeSelectButton.setText("Select...");
        contentPanel.add(columnTypeSelectButton, "cell 6 0");

        rowPackageLabel.setText("Row package:");
        contentPanel.add(rowPackageLabel, "cell 0 1");

        rowPackageTextField.setEnabled(false);
        contentPanel.add(rowPackageTextField, "cell 1 1" + TEXT_FIELD_SIZE);

        rowPackageSelectButton.setText("Select ...");
        contentPanel.add(rowPackageSelectButton, "cell 2 1");

        contentPanel.add(new JLabel(), "cell 3 1,width 40:40:40");

        columnPackageLabel.setText("Column package:");
        contentPanel.add(columnPackageLabel, "cell 4 1");

        columnPackageTextField.setEnabled(false);
        contentPanel.add(columnPackageTextField, "cell 5 1" + TEXT_FIELD_SIZE);

        columnPackageSelectButton.setText("Select...");
        contentPanel.add(columnPackageSelectButton, "cell 6 1");

        dependencyTypeLabel.setText("Dependency type:");
        contentPanel.add(dependencyTypeLabel, "cell 0 2");

        dependencyTextField.setEnabled(false);
        contentPanel.add(dependencyTextField, "cell 1 2" + TEXT_FIELD_SIZE);

        dependencySelectButton.setText("Select...");
        contentPanel.add(dependencySelectButton, "cell 2 2");

        queryButton.setText("Query");
        queryButton.setEnabled(false);
        contentPanel.add(queryButton, "cell 0 4");

        topPanel.add(contentPanel, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        scrollPane.setViewportView(matrixTable);

        add(scrollPane, BorderLayout.CENTER);
    }


}
