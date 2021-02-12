package org.demultiplexer.io;

import org.demultiplexer.config.Config;

import java.io.IOException;
import java.util.Collection;

public interface ImportService {

    Config importConfig(String path) throws IOException;

    Collection<String> importSequences(String path) throws IOException;
}
