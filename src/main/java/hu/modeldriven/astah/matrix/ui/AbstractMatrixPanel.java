package hu.modeldriven.astah.matrix.ui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;

public class AbstractMatrixPanel extends JPanel {

    private static final String TEXT_FIELD_SIZE = ",width 170:170:170";
    protected JButton columnPackageSelectButton;
    protected JTextField columnPackageTextField;
    protected JButton columnTypeSelectButton;
    protected JTextField columnTypeTextField;
    protected JButton dependencySelectButton;
    protected JTextField dependencyTextField;
    protected JTable matrixTable;
    protected JButton queryButton;
    protected JButton rowPackageSelectButton;
    protected JTextField rowPackageTextField;
    protected JButton rowTypeSelectButton;
    protected JTextField rowTypeTextField;
    protected JPanel contentPanel;
    protected JScrollPane scrollPane;
    protected JButton newButton;
    protected JButton openButton;
    protected JButton saveButton;
    protected JButton exportButton;
    protected BorderLayout panelLayout;
    private JPanel topPanel;
    private JLabel columnElementTypeLabel;
    private JLabel columnPackageLabel;
    private JLabel dependencyTypeLabel;
    private JLabel rowElementTypeLabel;
    private JLabel rowPackageLabel;
    private JToolBar toolBar;

    public AbstractMatrixPanel() {
        super();
        initComponents();
    }

    private void initComponents() {

        setName("matrixPanel");

        topPanel = new JPanel(new BorderLayout());
        topPanel.setName("topPanel");
        topPanel.setBackground(Color.WHITE);

        contentPanel = new JPanel();
        contentPanel.setName("contentPanel");
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
        scrollPane = new JScrollPane();
        matrixTable = new JTable();

        panelLayout = new BorderLayout();

        setLayout(panelLayout);

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

        topPanel.add(contentPanel, BorderLayout.WEST);

        add(topPanel, BorderLayout.NORTH);

        scrollPane.setViewportView(matrixTable);

        add(scrollPane, BorderLayout.CENTER);

        toolBar = new JToolBar();

        toolBar.setFloatable(true);
        toolBar.setRollover(true);

        newButton = new JButton("New");
        openButton = new JButton("Open");
        saveButton = new JButton("Save");
        saveButton.setEnabled(false);
        exportButton = new JButton("Export");
        exportButton.setEnabled(false);
        queryButton = new JButton("Query");
        queryButton.setEnabled(false);

        for (JButton button : Arrays.asList(newButton, openButton, saveButton, exportButton, queryButton)) {
            button.setFocusable(false);
            toolBar.add(button);
        }

        topPanel.add(toolBar, BorderLayout.NORTH);
    }


}
