package org.example;

import com.beust.jcommander.JCommander;
import org.example.config.Config;
import org.example.config.Group;
import org.example.io.FileExportService;
import org.example.io.FileImportService;
import org.example.matcher.MatchGroup;
import org.example.matcher.service.MatcherService;
import org.example.matcher.service.MatcherServiceFactory;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        CliOptions cliOptions = new CliOptions();
        JCommander.newBuilder()
                .addObject(cliOptions)
                .build()
                .parse(args);

        FileImportService fileImportService = new FileImportService();
        Config config = fileImportService.importConfig(cliOptions.getConfigFilePath());
        Set<String> sequences = fileImportService.importSequences(cliOptions.getSequenceDataFilePath());

        MatcherService matcherService = MatcherServiceFactory.getMatcherService(cliOptions.getAlignment());
        List<Group> groups = config.getGroups(cliOptions.getAlignment());
        List<MatchGroup> matchResults = matcherService.match(sequences, groups);

        FileExportService fileExportService = new FileExportService();
        fileExportService.export(matchResults, cliOptions.getOutputFilePath());
    }
}
