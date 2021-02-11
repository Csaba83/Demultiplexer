package org.example;

import com.beust.jcommander.JCommander;
import org.example.io.FileExportService;
import org.example.io.FileImportService;
import org.example.matcher.AlignmentMatcher;
import org.example.matcher.AlignmentMatcherFactory;
import org.example.matcher.MatchGroup;
import org.example.matcher.MatcherService;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        CommandLineOptions cliOptions = new CommandLineOptions();
        JCommander.newBuilder()
                .addObject(cliOptions)
                .build()
                .parse(args);

        FileImportService fileImportService = new FileImportService();
        Config config = fileImportService.importConfig(cliOptions.getConfigFilePath());
        Set<String> sequences = fileImportService.importSequences(cliOptions.getSequenceDataFilePath());

        AlignmentMatcher matcher = AlignmentMatcherFactory.getMatcher(cliOptions.getAlignment());
        MatcherService matcherService = new MatcherService(matcher);
        List<Config.AlignmentGroup> groups = config.getGroups(cliOptions.getAlignment());
        List<MatchGroup> matchResults = matcherService.match(sequences, groups);

        FileExportService fileExportService = new FileExportService();
        fileExportService.export(matchResults, cliOptions.getOutputFilePath());
    }
}
