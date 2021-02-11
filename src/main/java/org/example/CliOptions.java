package org.example;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import org.example.config.Alignment;

public class CliOptions {

    @Parameter(names = {"-sf", "--sequenceFile"}, required = true)
    private String sequenceDataFilePath;

    @Parameter(names = {"-cf", "--configFile"}, required = true)
    private String configFilePath;

    @Parameter(names = {"-of", "--outputFile"}, required = true)
    private String outputFilePath;

    @Parameter(names = {"-a", "--alignment"}, required = true, converter = AlignmentConverter.class)
    private Alignment alignment;

    public String getSequenceDataFilePath() {
        return sequenceDataFilePath;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
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
