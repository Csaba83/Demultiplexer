package org.example;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;

public class CliOptions {

    @Parameter(names = {"-sf", "--sequenceFile"}, required = true)
    private String sequenceDataFilePath;

    @Parameter(names = {"-cf", "--configFile"}, required = true)
    private String configFilePath;

    @Parameter(names = {"-of", "--outputFile"}, required = true)
    private String outputFilePath;

    @Parameter(names = {"-a", "--alignment"}, required = true, converter = AlignmentConverter.class)
    private Config.Alignment alignment;

    public String getSequenceDataFilePath() {
        return sequenceDataFilePath;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public Config.Alignment getAlignment() {
        return alignment;
    }

    private static class AlignmentConverter implements IStringConverter<Config.Alignment> {

        @Override
        public Config.Alignment convert(String s) {
            return Config.Alignment.getValueByName(s);
        }
    }
}
