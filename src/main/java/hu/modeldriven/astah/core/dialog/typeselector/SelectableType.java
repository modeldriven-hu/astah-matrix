package hu.modeldriven.astah.core.dialog.typeselector;

public interface SelectableType {
    boolean selected();

    void setSelected(boolean selected);

    Type type();

}
