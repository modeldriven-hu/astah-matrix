package hu.modeldriven.astah.core.dialog.typeselector;

import hu.modeldriven.astah.core.matcher.TypeMatcher;

import java.util.Map;

public interface Type {

    String name();

    TypeMatcher matcher();

    Map<String, Object> asMap();
}
