package hu.modeldriven.astah.core.dialog.typeselector.element;

import hu.modeldriven.astah.core.matcher.TypeMatcher;

import java.util.HashMap;
import java.util.Map;

public class AbstractElement implements Element {

    protected final String name;
    protected final TypeMatcher matcher;

    public AbstractElement(String name, TypeMatcher matcher) {
        this.name = name;
        this.matcher = matcher;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public TypeMatcher matcher() {
        return matcher;
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        return result;
    }

}
