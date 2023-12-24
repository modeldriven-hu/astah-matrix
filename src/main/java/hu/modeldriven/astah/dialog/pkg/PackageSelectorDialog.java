package hu.modeldriven.astah.dialog.pkg;

import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.BorderLayout;
import java.util.Optional;

public class PackageSelectorDialog {



    private final IPackage rootPackage;
    private IPackage selectedPackage;

    public PackageSelectorDialog(IPackage rootPackage){
        this.rootPackage = rootPackage;
    }

    public PackageSelectionResult show() {

        int result = JOptionPane.showConfirmDialog(null,
                getPanel(),
                "JOptionPane Example : ",
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

        JScrollPane scrollPane = new JScrollPane(tree);

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

}
