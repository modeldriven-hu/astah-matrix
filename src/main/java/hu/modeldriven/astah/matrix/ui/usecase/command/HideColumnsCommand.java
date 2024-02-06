package hu.modeldriven.astah.matrix.ui.usecase.command;

import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.commandstack.Command;

import javax.swing.*;

public class HideColumnsCommand implements Command {

    private final JTable table;
    private final int[] hiddenColumns;

    public HideColumnsCommand(JTable table, int[] columnsToHide) {
        this.table = table;
        this.hiddenColumns = columnsToHide;
    }

    @Override
    public void execute() {
        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel matrixTableModel = (MatrixTableModel) table.getModel();
            matrixTableModel.hideColumns(this.hiddenColumns);
        }
    }

    @Override
    public void undo() {
        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel matrixTableModel = (MatrixTableModel) table.getModel();
            matrixTableModel.showColumns(this.hiddenColumns);
        }
    }

    @Override
    public void redo() {
        execute();
    }
}

