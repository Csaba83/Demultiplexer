package org.example.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.Alignment;
import org.example.config.Config;
import org.example.config.Group;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FileImportService implements ImportService {

    private static final String PREFIX = "prefix";
    private static final String INFIX = "infix";
    private static final String POSTFIX = "postfix";

    @Override
    public Config importConfig(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Config config = new Config();

        JsonNode root = mapper.readTree(new File(path));
        for (Alignment alignment : Alignment.values()) {
            if (root.has(alignment.getName())) {
                JsonNode alignmentNode = root.get(alignment.getName());
                Iterator<Map.Entry<String, JsonNode>> groupNodes = alignmentNode.fields();
                config.addGroups(alignment, readGroup(groupNodes));
            }
        }
        return config;
    }

    private List<Group> readGroup(Iterator<Map.Entry<String, JsonNode>> groupNodes) {
        List<Group> groups = new ArrayList<>();
        groupNodes.forEachRemaining(entry -> {
            JsonNode groupElements = entry.getValue();
            String groupName = entry.getKey();
            //TODO builder
            String prefix = groupElements.has(PREFIX) ? groupElements.get(PREFIX).asText() : null;
            String infix = groupElements.has(INFIX) ? groupElements.get(INFIX).asText() : null;
            String postfix = groupElements.has(POSTFIX) ? groupElements.get(POSTFIX).asText() : null;
            groups.add(new Group(groupName, prefix, infix, postfix));
        });
        return groups;
    }

    @Override
    public Set<String> importSequences(String path) throws IOException {
        Scanner sc = new Scanner(new File(path));

        Set<String> sequences = new HashSet<>();
        while (sc.hasNextLine()) {
            sequences.add(sc.nextLine());
        }
        return sequences;
    }
}
