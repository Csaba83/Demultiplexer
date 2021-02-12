package org.demultiplexer;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import org.demultiplexer.config.Alignment;

public class CliOptions {

    @Parameter(names = {"-s", "--sequenceFile"}, required = true)
    private String sequenceDataFilePath;

    @Parameter(names = {"-c", "--configFile"}, required = true)
    private String configFilePath;

    @Parameter(names = {"-o", "--outputPrefix"}, required = true,
            description = "Like C:/ or C:/prefix. Output will be like C:/group1.seq or C:/prefixGroup1.seq")
    private String outputPrefix;

    @Parameter(names = {"-a", "--alignment"}, required = true, converter = AlignmentConverter.class)
    private Alignment alignment;

    public String getSequenceDataFilePath() {
        return sequenceDataFilePath;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }

    public String getOutputPrefix() {
        return outputPrefix;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    private static class AlignmentConverter implements IStringConverter<Alignment> {

        @Override
        public Alignment convert(String s) {
            try {
                return Alignment.getValueByName(s);
            }
            catch (IllegalStateException e) {
                throw new ParameterException(e.getMessage());
            }
        }
    }
}
