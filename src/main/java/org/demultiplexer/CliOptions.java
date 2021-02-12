package org.demultiplexer;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import org.demultiplexer.config.Alignment;

public class CliOptions {

    @Parameter(names = {"-s", "--sequenceFile"}, required = true)
    private String sequenceDataFilePath;

    @Parameter(names = {"-c", "--configFile"}, required = true)
    private String configFilePath;

    @Parameter(names = {"-o", "--outputDirectory"}, required = true)
    private String outputDirectory;

    @Parameter(names = {"-a", "--alignment"}, required = true, converter = AlignmentConverter.class)
    private Alignment alignment;

    public String getSequenceDataFilePath() {
        return sequenceDataFilePath;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    private static class AlignmentConverter implements IStringConverter<Alignment> {

        @Override
        public Alignment convert(String s) {
            return Alignment.getValueByName(s);
        }
    }
}
