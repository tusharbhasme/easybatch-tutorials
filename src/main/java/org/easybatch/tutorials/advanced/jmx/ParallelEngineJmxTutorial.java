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

package org.easybatch.tutorials.advanced.jmx;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.easybatch.core.api.RecordFilter;
import org.easybatch.core.api.Report;
import org.easybatch.core.filter.RecordNumberGreaterThanFilter;
import org.easybatch.core.filter.RecordNumberLowerThanFilter;
import org.easybatch.core.impl.Engine;
import org.easybatch.core.impl.EngineBuilder;
import org.easybatch.core.reader.StringRecordReader;
import org.easybatch.tools.reporting.DefaultReportMerger;
import org.easybatch.tools.reporting.ReportMerger;

/**
* Main class to run the JMX tutorial.
 *
* @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
*/
public class ParallelEngineJmxTutorial {

	  private static final int THREAD_POOL_SIZE = 2;

    public static void main(String[] args) throws Exception {

        // Create the String data source
        String dataSource =
                "1,foo,easy batch rocks! #EasyBatch\n" +
                "2,foo,easy batch rocks! #EasyBatch\n" +
                "3,foo,easy batch rocks! #EasyBatch\n" +
                "4,foo,easy batch rocks! #EasyBatch\n" +
                "5,foo,easy batch rocks! #EasyBatch\n" +
                "6,foo,easy batch rocks! #EasyBatch\n" +
                "7,foo,easy batch rocks! #EasyBatch\n" +
                "8,foo,easy batch rocks! #EasyBatch\n" +
                "9,foo,easy batch rocks! #EasyBatch\n" +
                "10,bar,@foo I do confirm :-)";

        // Build worker engines
        // worker engine 1: process data from tweets.csv, filter records 6-10
        Engine engine1 = buildEngine(dataSource, new RecordNumberGreaterThanFilter(5), "engine1");
        // worker engine 2: process data from tweets.csv, filter records 1-5
        Engine engine2 = buildEngine(dataSource, new RecordNumberLowerThanFilter(6), "engine2");

        //create a 2 threads pool to call worker engines in parallel
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        List<Future<Report>> partialReports = executorService.invokeAll(Arrays.asList(engine1, engine2));

        //merge partial reports into a global one
        Report report1 = partialReports.get(0).get();
        Report report2 = partialReports.get(1).get();

        ReportMerger reportMerger = new DefaultReportMerger();
        Report finalReport = reportMerger.mergerReports(report1, report2);
        System.out.println(finalReport);

        executorService.shutdown();

    }

    private static Engine buildEngine(String dataSource, RecordFilter recordFilter, String mbeanName) throws Exception{
        return EngineBuilder.aNewEngine()
        				.withName(mbeanName) // registers a new mbean
                .reader(new StringRecordReader(dataSource))
                .filter(recordFilter)
                .processor(new TweetSlowProcessor())
                .enableJMX(true)
                .build();
    }

}