package hu.modeldriven.astah.dialog.pkg;

import hu.modeldriven.astah.core.dialog.pkg.PackageSelectionResult;
import hu.modeldriven.astah.core.dialog.pkg.PackageSelectorDialog;

import javax.swing.SwingUtilities;

public class PackageSelectorDialogTest {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            TestPackage rootPackage = new TestPackage("Root");

            rootPackage.addElement(new TestPackage("Child1"));
            rootPackage.addElement(new TestPackage("Child2"));
            rootPackage.addElement(new TestPackage("Child3"));

            TestPackage childContainer = new TestPackage("ChildContainer");
            childContainer.addElement(new TestPackage("Child4"));
            childContainer.addElement(new TestPackage("Child5"));

            rootPackage.addElement(childContainer);

            PackageSelectorDialog dialog = new PackageSelectorDialog(rootPackage);
            PackageSelectionResult result = dialog.show();

            System.out.println(result.selectedPackage().get().getName());
        });

    }

}
