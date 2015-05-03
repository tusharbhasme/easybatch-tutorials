package org.easybatch.tutorials.basic.wordcount;

import org.easybatch.core.api.Record;
import org.easybatch.core.api.RecordMapper;

import java.util.Arrays;
import java.util.List;

/**
 * This mapper splits each line into a list of words.
 *
 * @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 */
public class LineTokenizer implements RecordMapper<List<String>> {

    public List<String> mapRecord(Record record) throws Exception {
        String payload = (String) record.getPayload();
        return Arrays.asList(payload.split(" "));
    }

}