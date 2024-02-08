package hu.modeldriven.astah.matrix.ui.usecase.persistance;

import hu.modeldriven.astah.core.representation.ModelRepresentation;
import hu.modeldriven.astah.matrix.ui.usecase.model.DefaultQueryModel;
import hu.modeldriven.astah.matrix.ui.usecase.model.QueryModel;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;

public class YAMLQueryFile {

    private final File file;
    private final Yaml yaml;

    public YAMLQueryFile(File file) {
        this.file = file;

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setIndent(2);
        dumperOptions.setPrettyFlow(true);
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setTagInspector(tag -> false);

        this.yaml = new Yaml(new Constructor(loaderOptions), new Representer(dumperOptions), dumperOptions);
    }

    public void write(QueryModel queryModel) throws QueryFileException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            String yamlAsString = yaml.dump(queryModel.asMap());
            writer.write(yamlAsString);
        } catch (Exception e) {
            throw new QueryFileException(e);
        }
    }

    public QueryModel read(ModelRepresentation modelRepresentation) throws QueryFileException {

        try (Reader reader = new FileReader(this.file)) {
            return new DefaultQueryModel(yaml.load(reader), modelRepresentation);
        } catch (Exception e) {
            throw new QueryFileException(e);
        }
    }

}
