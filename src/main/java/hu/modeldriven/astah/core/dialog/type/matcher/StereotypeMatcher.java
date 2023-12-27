package hu.modeldriven.astah.core.dialog.type.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public class StereotypeMatcher implements TypeMatcher {

    private final String requiredStereotype;

    public StereotypeMatcher(String requiredStereotype) {
        this.requiredStereotype = requiredStereotype;
    }

    @Override
    public boolean matches(IElement element) {
        return element.hasStereotype(requiredStereotype);
    }
}
