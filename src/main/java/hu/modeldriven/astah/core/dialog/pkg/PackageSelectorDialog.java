package hu.modeldriven.astah.core.dialog.pkg;

import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.util.function.Consumer;

public class PackageSelectorDialog {

    private final IPackage rootPackage;

    private final Consumer<IPackage> callback;

    public PackageSelectorDialog(IPackage rootPackage, Consumer<IPackage> callback) {
        this.rootPackage = rootPackage;
        this.callback = callback;
    }

    public void show() {
        JDialog dialog = new JDialog();
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().setLayout(new BorderLayout());

        PackageSelectorPanel panel = new PackageSelectorPanel(dialog, rootPackage, callback);
        dialog.getContentPane().add(panel);

        dialog.pack();
        dialog.setVisible(true);
    }

}
