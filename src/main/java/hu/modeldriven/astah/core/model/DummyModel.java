package hu.modeldriven.astah.core.model;

import com.change_vision.jude.api.inf.model.IPackage;

public class DummyModel implements Model {
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
}
