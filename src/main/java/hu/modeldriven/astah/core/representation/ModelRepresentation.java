package hu.modeldriven.astah.core.representation;

import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IPackage;

import java.util.List;

public interface ModelRepresentation {

    IPackage rootPackage() throws ModelAccessException;

    IPackage findPackageById(String id) throws ModelAccessException;

    boolean isElementSelected() throws ModelAccessException;

    IElement getSingleSelection() throws ModelAccessException;

    List<IElement> getSelection() throws ModelAccessException;


}
