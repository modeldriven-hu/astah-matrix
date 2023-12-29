package hu.modeldriven.astah.core.model;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.*;
import com.change_vision.jude.api.inf.presentation.IPresentation;

public class DummyNamedElement implements INamedElement {

    private final String name;

    public DummyNamedElement(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getFullName(String s) {
        return null;
    }

    @Override
    public String getFullNamespace(String s) {
        return null;
    }

    @Override
    public IConstraint[] getConstraints() {
        return new IConstraint[0];
    }

    @Override
    public IDependency[] getSupplierDependencies() {
        return new IDependency[0];
    }

    @Override
    public IDependency[] getClientDependencies() {
        return new IDependency[0];
    }

    @Override
    public IRealization[] getSupplierRealizations() {
        return new IRealization[0];
    }

    @Override
    public IRealization[] getClientRealizations() {
        return new IRealization[0];
    }

    @Override
    public IUsage[] getSupplierUsages() {
        return new IUsage[0];
    }

    @Override
    public IUsage[] getClientUsages() {
        return new IUsage[0];
    }

    @Override
    public String getDefinition() {
        return null;
    }

    @Override
    public boolean isPublicVisibility() {
        return false;
    }

    @Override
    public boolean isProtectedVisibility() {
        return false;
    }

    @Override
    public boolean isPrivateVisibility() {
        return false;
    }

    @Override
    public boolean isPackageVisibility() {
        return false;
    }

    @Override
    public IDiagram[] getDiagrams() {
        return new IDiagram[0];
    }

    @Override
    public void setName(String s) throws InvalidEditingException {

    }

    @Override
    public void setDefinition(String s) throws InvalidEditingException {

    }

    @Override
    public void setVisibility(String s) throws InvalidEditingException {

    }

    @Override
    public String getAlias1() {
        return null;
    }

    @Override
    public String getAlias2() {
        return null;
    }

    @Override
    public void setAlias1(String s) throws InvalidEditingException {

    }

    @Override
    public void setAlias2(String s) throws InvalidEditingException {

    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public IElement getOwner() {
        return null;
    }

    @Override
    public IElement getContainer() {
        return null;
    }

    @Override
    public IElement[] getContainers() {
        return new IElement[0];
    }

    @Override
    public IComment[] getComments() {
        return new IComment[0];
    }

    @Override
    public String[] getStereotypes() {
        return new String[0];
    }

    @Override
    public boolean hasStereotype(String s) {
        return false;
    }

    @Override
    public void removeStereotype(String s) throws InvalidEditingException {

    }

    @Override
    public void addStereotype(String s) throws InvalidEditingException {

    }

    @Override
    public ITaggedValue[] getTaggedValues() {
        return new ITaggedValue[0];
    }

    @Override
    public String getTaggedValue(String s) {
        return null;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    public String getTypeModifier() {
        return null;
    }

    @Override
    public IPresentation[] getPresentations() throws InvalidUsingException {
        return new IPresentation[0];
    }

    @Override
    public void setTypeModifier(String s) throws InvalidEditingException {

    }

    @Override
    public IHyperlink[] getHyperlinks() {
        return new IHyperlink[0];
    }

    @Override
    public IHyperlink createFileHyperlink(String s, String s1, String s2) throws InvalidEditingException {
        return null;
    }

    @Override
    public IHyperlink createURLHyperlink(String s, String s1) throws InvalidEditingException {
        return null;
    }

    @Override
    public IHyperlink createElementHyperlink(IElement iElement, String s) throws InvalidEditingException {
        return null;
    }

    @Override
    public void deleteHyperlink(IHyperlink iHyperlink) throws InvalidEditingException {

    }
}
