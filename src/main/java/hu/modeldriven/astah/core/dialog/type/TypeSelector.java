package hu.modeldriven.astah.core.dialog.type;

import hu.modeldriven.astah.core.dialog.type.matcher.TypeMatcher;

public interface TypeSelector {
    boolean selected();

    void setSelected(boolean selected);

    String name();

    TypeMatcher matcher();
}
