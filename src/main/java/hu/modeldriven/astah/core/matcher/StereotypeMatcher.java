package hu.modeldriven.astah.core.matcher;

import com.change_vision.jude.api.inf.model.IElement;

import java.util.Arrays;

public class StereotypeMatcher implements TypeMatcher {

    public static final String STRING_SEPARATOR = ",";
    private final String stereotype;

    public StereotypeMatcher(String stereotype) {
        this.stereotype = stereotype;
    }

    @Override
    public boolean matches(IElement element) {
        if (stereotype.contains(STRING_SEPARATOR)) {
            return Arrays.stream(stereotype.split(STRING_SEPARATOR)).anyMatch(s -> element.hasStereotype(s.trim()));
        } else {
            return element.hasStereotype(stereotype);
        }
    }
}
