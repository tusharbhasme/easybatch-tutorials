/*
 * The MIT License
 *
 *  Copyright (c) 2015, Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package org.easybatch.tutorials.basic.wordcount;

import org.easybatch.core.api.Report;
import org.easybatch.core.reader.StringRecordReader;

import java.util.Map;

import static org.easybatch.core.impl.EngineBuilder.aNewEngine;

/**
* Main class to run the word count tutorial.
 *
* @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
*/
@SuppressWarnings("unchecked")
public class Launcher {

    public static void main(String[] args) throws Exception {

        // Create a data source
        String dataSource =
                "Spring batch is cool but a bit complex\n" +
                "Easy batch is cool too, but easier";

        // Build and run a batch engine
        Report report = aNewEngine()
                .reader(new StringRecordReader(dataSource))
                .mapper(new LineTokenizer())
                .processor(new WordCounter())
                .build().call();

        // Get result
        Map<String, Integer> words = (Map<String, Integer>) report.getBatchResult();

        System.out.println(words);

    }

}