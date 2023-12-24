package hu.modeldriven.astah.dialog.pkg;

import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IPackage;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class PackageTreeNode implements TreeNode {

    private final PackageTreeNode parent;
    private final List<PackageTreeNode> children;

    private final String name;

    public PackageTreeNode(IPackage pkg, PackageTreeNode parent) {
        this.parent = parent;
        this.children = new ArrayList<>();
        this.name = pkg.getName();

        for (INamedElement child : pkg.getOwnedElements()){
            if (child instanceof IPackage){
                IPackage p = (IPackage) child;
                children.add(new PackageTreeNode(p, this));
            }
        }

    }

    @Override
    public TreeNode getChildAt(int i) {
        return children.get(i);
    }

    @Override
    public int getChildCount() {
        return children.size();
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public int getIndex(TreeNode treeNode) {
        return children.indexOf(treeNode);
    }

    @Override
    public boolean getAllowsChildren() {
        return true;
    }

    @Override
    public boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return Collections.enumeration(children);
    }

    public String name() {
        return name;
    }
}
