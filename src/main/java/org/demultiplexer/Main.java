package org.demultiplexer;

import com.beust.jcommander.JCommander;
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

    public static void main(String[] args) throws IOException {
        CliOptions cliOptions = new CliOptions();
        JCommander.newBuilder()
                .addObject(cliOptions)
                .build()
                .parse(args);

        FileImportService fileImportService = new FileImportService();
        Config config = fileImportService.importConfig(cliOptions.getConfigFilePath());
        List<String> sequences = fileImportService.importSequences(cliOptions.getSequenceDataFilePath());

        MatcherService matcherService = MatcherServiceFactory.getMatcherService(cliOptions.getAlignment());
        List<Group> groups = config.getGroups(cliOptions.getAlignment());
        List<MatchGroup> matchResults = matcherService.match(sequences, groups);

        FileExportService fileExportService = new FileExportService();
        fileExportService.export(matchResults, cliOptions.getOutputDirectory());
    }
}
