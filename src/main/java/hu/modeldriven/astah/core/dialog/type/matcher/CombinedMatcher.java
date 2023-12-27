package hu.modeldriven.astah.core.dialog.type.matcher;

import com.change_vision.jude.api.inf.model.IElement;

public class CombinedMatcher implements ElementMatcher {

    ElementMatcher[] elementMatchers;

    public CombinedMatcher(ElementMatcher... elementMatchers) {
        this.elementMatchers = elementMatchers;
    }

    @Override
    public boolean matches(IElement element) {

        for (ElementMatcher elementMatcher : elementMatchers) {
            if (!elementMatcher.matches(element)) {
                return false;
            }
        }

        return true;
    }
}
