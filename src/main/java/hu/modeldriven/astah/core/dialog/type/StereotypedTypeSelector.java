package hu.modeldriven.astah.core.dialog.type;

import hu.modeldriven.astah.core.dialog.type.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.StereotypeMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;

import java.lang.reflect.Type;

public class StereotypedTypeSelector implements TypeSelector {

    private final String stereotype;
    private final TypeSelector typeSelector;

    public StereotypedTypeSelector(String stereotype, TypeSelector typeSelector) {
        this.stereotype = stereotype;
        this.typeSelector = typeSelector;
    }

    @Override
    public boolean selected() {
        return typeSelector.selected();
    }

    @Override
    public void setSelected(boolean selected) {
        typeSelector.setSelected(selected);
    }

    @Override
    public String name() {
        return  "« " + stereotype + " » " + typeSelector.name();
    }

    @Override
    public TypeMatcher matcher() {
        return new CombinedMatcher(new StereotypeMatcher(stereotype), typeSelector.matcher());
    }
}
