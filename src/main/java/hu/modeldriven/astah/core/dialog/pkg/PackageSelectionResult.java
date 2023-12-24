package hu.modeldriven.astah.core.dialog.pkg;

import com.change_vision.jude.api.inf.model.IPackage;

import java.util.Optional;

public class PackageSelectionResult {

    private final Optional<IPackage> selectedPackage;

    public PackageSelectionResult(Optional<IPackage> selectedPackage) {
        this.selectedPackage = selectedPackage;
    }

    public PackageSelectionResult() {
        this.selectedPackage = Optional.empty();
    }

    public Optional<IPackage> selectedPackage() {
        return selectedPackage;
    }
}

