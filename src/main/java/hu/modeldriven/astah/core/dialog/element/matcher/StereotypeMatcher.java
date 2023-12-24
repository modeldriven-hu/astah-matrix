package hu.modeldriven.astah.core.dialog.element.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public class StereotypeMatcher implements ElementMatcher {

    private final String requiredStereotype;

    public StereotypeMatcher(String requiredStereotype) {
        this.requiredStereotype = requiredStereotype;
    }

    @Override
    public boolean matches(IElement element) {
        return element.hasStereotype(requiredStereotype);
    }
}
