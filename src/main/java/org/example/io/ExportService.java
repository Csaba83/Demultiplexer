package org.example.io;

import org.example.matcher.MatchGroup;

import java.io.IOException;
import java.util.List;

public interface ExportService {

    void export(List<MatchGroup> matchGroups, String path) throws IOException;
}
