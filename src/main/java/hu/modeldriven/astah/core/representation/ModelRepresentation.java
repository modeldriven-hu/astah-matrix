package hu.modeldriven.astah.core.representation;

import com.change_vision.jude.api.inf.model.IPackage;

public interface ModelRepresentation {

    IPackage rootPackage() throws ModelAccessException;

    IPackage findPackageById(String id) throws ModelAccessException;

}
