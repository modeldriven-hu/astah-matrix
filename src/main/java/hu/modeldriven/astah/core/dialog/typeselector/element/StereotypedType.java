package hu.modeldriven.astah.core.dialog.typeselector.element;

import hu.modeldriven.astah.core.dialog.typeselector.Type;
import hu.modeldriven.astah.core.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.matcher.StereotypeMatcher;
import hu.modeldriven.astah.core.matcher.TypeMatcher;

import java.util.HashMap;
import java.util.Map;

public class StereotypedType implements Type {

    private final String stereotype;
    private final Type type;

    public StereotypedType(Type type, String stereotype) {
        this.type = type;
        this.stereotype = stereotype;
    }

    @Override
    public String name() {
        return "« " + stereotype + " » " + type.name();
    }

    @Override
    public TypeMatcher matcher() {
        return new CombinedMatcher(new StereotypeMatcher(stereotype), type.matcher());
    }

    @Override
    public Map<String, Object> asMap() {
        Map<String, Object> result = new HashMap<>();

        result.put("stereotype", stereotype);
        result.putAll(type.asMap());

        return result;
    }
}
