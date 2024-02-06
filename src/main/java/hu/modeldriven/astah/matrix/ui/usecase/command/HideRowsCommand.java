package hu.modeldriven.astah.matrix.ui.usecase.command;

import hu.modeldriven.astah.matrix.ui.table.MatrixTableModel;
import hu.modeldriven.core.commandstack.Command;

import javax.swing.*;

public class HideRowsCommand implements Command {

    private final JTable table;
    private final int[] hiddenRows;

    public HideRowsCommand(JTable table, int[] rowsToHide) {
        this.table = table;
        this.hiddenRows = rowsToHide;
    }

    @Override
    public void execute() {
        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel matrixTableModel = (MatrixTableModel) table.getModel();
            matrixTableModel.hideRows(this.hiddenRows);
        }
    }

    @Override
    public void undo() {
        if (table.getModel() instanceof MatrixTableModel) {
            MatrixTableModel matrixTableModel = (MatrixTableModel) table.getModel();
            matrixTableModel.showRows(this.hiddenRows);
        }
    }

    @Override
    public void redo() {
        execute();
    }
}
