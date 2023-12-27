package hu.modeldriven.astah.core.dialog.type;

import com.change_vision.jude.api.inf.model.IAction;
import com.change_vision.jude.api.inf.model.IClass;
import com.change_vision.jude.api.inf.model.IRequirement;
import com.change_vision.jude.api.inf.model.IUseCase;
import hu.modeldriven.astah.core.dialog.type.matcher.ClassMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.CombinedMatcher;
import hu.modeldriven.astah.core.dialog.type.matcher.StereotypeMatcher;

import java.util.ArrayList;
import java.util.List;

public interface TypeSelectorData {

    public List<TypeSelector> asRows();

}
