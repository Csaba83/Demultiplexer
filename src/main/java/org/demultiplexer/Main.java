package org.demultiplexer;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.demultiplexer.config.Config;
import org.demultiplexer.config.Group;
import org.demultiplexer.io.FileExportService;
import org.demultiplexer.io.FileImportService;
import org.demultiplexer.matcher.MatchGroup;
import org.demultiplexer.matcher.service.MatcherService;
import org.demultiplexer.matcher.service.MatcherServiceFactory;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        CliOptions cliOptions = getCliOptions(args);

        Config config = getConfig(cliOptions);
        List<String> sequences = getSequences(cliOptions);

        MatcherService matcherService = MatcherServiceFactory.getMatcherService(cliOptions.getAlignment());
        List<Group> groups = config.getGroups(cliOptions.getAlignment());
        List<MatchGroup> matchResults = matcherService.match(sequences, groups);

        exportResult(cliOptions, matchResults);
    }

    private static CliOptions getCliOptions(String[] args) {
        CliOptions cliOptions = new CliOptions();
        try {
            JCommander.newBuilder()
                    .addObject(cliOptions)
                    .build()
                    .parse(args);
        } catch (ParameterException e) {
            System.out.println(e.getMessage());
            e.getJCommander().usage();
            System.exit(1);
        }
        return cliOptions;
    }

    private static Config getConfig(CliOptions cliOptions) {
        Config config = null;
        try {
            config = new FileImportService().importConfig(cliOptions.getConfigFilePath());
        } catch (IOException e) {
            System.out.println("Exception occurred when importing config: " + e.getMessage());
            System.exit(2);
        }
        return config;
    }

    private static List<String> getSequences(CliOptions cliOptions) {
        List<String> sequences = null;
        try {
            sequences = new FileImportService().importSequences(cliOptions.getSequenceDataFilePath());
        } catch (IOException e) {
            System.out.println("Exception occurred when importing sequences: " + e.getMessage());
            System.exit(2);
        }
        return sequences;
    }

    private static void exportResult(CliOptions cliOptions, List<MatchGroup> matchResults) {
        try {
            new FileExportService().export(matchResults, cliOptions.getOutputPrefix());
        } catch (IOException e) {
            System.out.println("Exception occurred when exporting result: " + e.getMessage());
            System.exit(2);
        }
    }
}
