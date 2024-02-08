package hu.modeldriven.astah.core.dialog.typeselector;

public class SelectedType {

    private final Type type;
    private final String stereotype;

    public SelectedType(Type type, String stereotype) {
        this.type = type;
        this.stereotype = stereotype;
    }

    public SelectedType(Type type) {
        this(type, null);
    }

    public Type type() {
        return this.type;
    }

    public String stereotype() {
        return this.stereotype;
    }

    public boolean hasStereotype() {
        return this.stereotype != null;
    }
}
