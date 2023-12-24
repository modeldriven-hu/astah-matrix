package hu.modeldriven.astah.dialog;

import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.JOptionPane;
import java.util.Optional;

public class PackageSelectorDialog {
    public PackageSelectionResult show() {

        int result = JOptionPane.showConfirmDialog(null,
                null,
                "JOptionPane Example : ",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.CANCEL_OPTION) {
            return PackageSelectionResult.CANCELLED;
        } else {
            return PackageSelectionResult.PACKAGE_SELECTED;
        }
    }

    public enum PackageSelectionResult {
        PACKAGE_SELECTED, CANCELLED
    }

    public IPackage selectedPackage(){
        return null;
    }

}
