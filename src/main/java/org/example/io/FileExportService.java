package org.example.io;

import org.example.matcher.MatchGroup;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileExportService implements ExportService {
    @Override
    public void export(List<MatchGroup> matchGroups, String path) throws IOException {

        for (MatchGroup matchGroup : matchGroups ) {
            FileWriter fileWriter = new FileWriter(path + matchGroup.getGroupName() + ".seq");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for ( String sequence : matchGroup.getSequences() ) {
                printWriter.print(sequence);
                printWriter.print(" ");
            }
            printWriter.close();
        }
    }
}
