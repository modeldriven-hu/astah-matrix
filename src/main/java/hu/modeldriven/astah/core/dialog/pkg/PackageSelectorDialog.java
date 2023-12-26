package hu.modeldriven.astah.core.dialog.pkg;

import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.BorderLayout;
import java.util.Optional;

public class PackageSelectorDialog {


    private final IPackage rootPackage;
    private IPackage selectedPackage;

    public PackageSelectorDialog(IPackage rootPackage) {
        this.rootPackage = rootPackage;
    }

    public PackageSelectionResult show() {

        int result = JOptionPane.showConfirmDialog(null,
                getPanel(),
                "Select a package",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.CANCEL_OPTION) {
            return new PackageSelectionResult();
        } else {
            return new PackageSelectionResult(Optional.ofNullable(selectedPackage));
        }
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel();

        DefaultTreeModel model = new DefaultTreeModel(new PackageTreeNode(rootPackage, null));
        JTree tree = new JTree(model);
        tree.setCellRenderer(new PackageTreeNodeRenderer());
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent treeSelectionEvent) {
                PackageTreeNode node = (PackageTreeNode)
                        tree.getLastSelectedPathComponent();

                if (node == null) {
                    return;
                }

                PackageSelectorDialog.this.selectedPackage = node.model();
            }
        });

        JScrollPane scrollPane = new JScrollPane(tree);

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.setPreferredSize(new java.awt.Dimension(400, 400));

        return panel;
    }

}
