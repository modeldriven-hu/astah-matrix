package hu.modeldriven.astah.core.dialog.typeselector.element;

import hu.modeldriven.astah.core.matcher.StereotypeMatcher;

public class BlockElement extends AbstractElement {
    public BlockElement() {
        super("Block", new StereotypeMatcher("Block"));
    }
}
