package hu.modeldriven.astah.core.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public class CombinedMatcher implements TypeMatcher {

    TypeMatcher[] elementMatchers;

    public CombinedMatcher(TypeMatcher... elementMatchers) {
        this.elementMatchers = elementMatchers;
    }

    @Override
    public boolean matches(IElement element) {

        for (TypeMatcher elementMatcher : elementMatchers) {
            if (!elementMatcher.matches(element)) {
                return false;
            }
        }

        return true;
    }
}
