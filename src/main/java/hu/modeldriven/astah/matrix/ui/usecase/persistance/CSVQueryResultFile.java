package hu.modeldriven.astah.matrix.ui.usecase.persistance;

import com.change_vision.jude.api.inf.model.INamedElement;
import de.siegmar.fastcsv.writer.CsvWriter;
import hu.modeldriven.astah.matrix.ui.table.MatrixData;
import hu.modeldriven.astah.matrix.ui.table.RelationshipDirection;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CSVQueryResultFile {

    private final File file;

    public CSVQueryResultFile(File file) {
        this.file = file;
    }

    public void write(MatrixData matrixData) throws CSVExportException {

        try (CsvWriter writer = CsvWriter.builder().build(file.toPath())) {

            List<INamedElement> columns = matrixData.columns();

            String[] header = new String[matrixData.columnCount() + 1];
            header[0] = "Row/Column";

            for (int columnIndex = 0; columnIndex < matrixData.columnCount(); columnIndex++) {
                header[columnIndex + 1] = columns.get(columnIndex).getName();
            }

            writer.writeRow(header);

            // Write data
            List<INamedElement> rows = matrixData.rows();
            for (int rowIndex = 0; rowIndex < matrixData.rowCount(); rowIndex++) {
                String[] data = new String[matrixData.columnCount() + 1];
                data[0] = rows.get(rowIndex).getName();
                for (int columnIndex = 0; columnIndex < matrixData.columnCount(); columnIndex++) {
                    data[columnIndex + 1] = getRelationshipDirectionSymbol(matrixData.getRelationship(rowIndex, columnIndex));
                }
                writer.writeRow(data);
            }

        } catch (IOException e) {
            throw new CSVExportException(e);
        }
    }

    private String getRelationshipDirectionSymbol(RelationshipDirection direction) {
        switch (direction) {
            case ROW_TO_COLUMN:
                return "->";
            case COLUMN_TO_ROW:
                return "<-";
            case BOTH:
                return "<->";
            default:
                return " ";
        }
    }

}
