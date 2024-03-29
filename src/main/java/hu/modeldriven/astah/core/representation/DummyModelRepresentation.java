package hu.modeldriven.astah.core.representation;

import com.change_vision.jude.api.inf.model.IElement;
import com.change_vision.jude.api.inf.model.IPackage;

import java.util.List;

public class DummyModelRepresentation implements ModelRepresentation {
    @Override
    public IPackage rootPackage() throws ModelAccessException {

        DummyPackage rootPackage = new DummyPackage("Root");

        rootPackage.addElement(new DummyPackage("Child1"));
        rootPackage.addElement(new DummyPackage("Child2"));
        rootPackage.addElement(new DummyPackage("Child3"));

        DummyPackage childContainer = new DummyPackage("ChildContainer");
        childContainer.addElement(new DummyPackage("Child4"));
        childContainer.addElement(new DummyPackage("Child5"));

        rootPackage.addElement(childContainer);

        return rootPackage;
    }

    @Override
    public IPackage findPackageById(String id) throws ModelAccessException {
        return null;
    }

    @Override
    public boolean isElementSelected() throws ModelAccessException {
        return false;
    }

    @Override
    public IElement getSingleSelection() throws ModelAccessException {
        return null;
    }

    @Override
    public List<IElement> getSelection() throws ModelAccessException {
        return null;
    }


}
