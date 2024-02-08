package hu.modeldriven.astah.core.dialog.typeselector;

class SelectableTypeImpl implements SelectableType {

    private final Type type;
    private boolean selected = false;

    public SelectableTypeImpl(Type type) {
        this.type = type;
    }

    @Override
    public Type type() {
        return type;
    }

    @Override
    public boolean selected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

}
