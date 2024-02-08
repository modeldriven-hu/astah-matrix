package hu.modeldriven.astah.dialog.pkg;

import hu.modeldriven.astah.core.dialog.pkg.PackageSelectorDialog;

import javax.swing.*;

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

            PackageSelectorDialog dialog = new PackageSelectorDialog(
                    null,
                    rootPackage,
                    selectedPackage -> {
                        System.out.println(selectedPackage.getName());
                    });

            dialog.show();
        });

    }

}
