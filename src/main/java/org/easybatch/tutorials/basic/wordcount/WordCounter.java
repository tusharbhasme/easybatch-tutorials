package org.easybatch.tutorials.basic.wordcount;

import org.easybatch.core.api.ComputationalRecordProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This processor counts the number of occurrences of each word.
 *
 * @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 */
public class WordCounter implements ComputationalRecordProcessor<List<String>, List<String>, Map<String, Integer>> {

    private Map<String, Integer> words = new HashMap<String, Integer>();

    public Map<String, Integer> getComputationResult() {
        return words;
    }

    public List<String> processRecord(List<String> tokens) throws Exception {
        for (String token : tokens) {
            Integer count = words.get(token);
            count = (count == null) ? 1 : count + 1;
            words.put(token, count);
        }
        return tokens;
    }
}