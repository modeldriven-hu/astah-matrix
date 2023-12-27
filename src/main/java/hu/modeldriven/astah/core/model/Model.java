package hu.modeldriven.astah.core.model;

import com.change_vision.jude.api.inf.model.IPackage;

public interface Model {

    IPackage rootPackage() throws ModelAccessException;

}
