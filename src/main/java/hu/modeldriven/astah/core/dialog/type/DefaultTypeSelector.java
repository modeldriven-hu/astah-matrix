package hu.modeldriven.astah.core.dialog.type;

import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;

public class DefaultTypeSelector implements TypeSelector {

    private final String name;
    private final TypeMatcher matcher;
    private boolean selected = false;

    public DefaultTypeSelector(String name, TypeMatcher matcher) {
        this.name = name;
        this.matcher = matcher;
    }

    @Override
    public boolean selected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public TypeMatcher matcher() {
        return matcher;
    }

}
