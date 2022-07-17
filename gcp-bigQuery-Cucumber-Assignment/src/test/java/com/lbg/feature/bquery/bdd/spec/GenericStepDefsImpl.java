package com.lbg.feature.bquery.bdd.spec;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.lbg.feature.bquery.BigQueryTestService;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GenericStepDefsImpl implements En {


    @Autowired
    private BigQueryTestService bigQueryTest;

    private Map<String, Object> executionContext = new HashMap<>();

    public GenericStepDefsImpl() {

        Given("GCP BigQuery has a Dataset {string} and TableName {string}", (String dataSetName, String tableName) -> {
            executionContext.put("dataSet", dataSetName);
            executionContext.put("tableName", tableName);
            bigQueryTest.deleteTableData(dataSetName, tableName);
        });

        When("I feed the data from CSV file {string}", (String filename) -> {
            executionContext.put("filename", filename);
            File file = new File(getClass().getClassLoader().getResource(filename).getPath());
            bigQueryTest.loadCSVDataToGcpBigQuery(executionContext.get("dataSet").toString(), executionContext.get("tableName").toString(),
                    new FileInputStream(file));
        });

        Then("Records should be saved {string}", (String result) -> {
            assertThat(result).isEqualToIgnoringCase("OK");
        });

        And("Record count should be {long}", (Long count) -> {
            long rows = bigQueryTest.getCountOfResultSet(executionContext.get("dataSet").toString(), executionContext.get("tableName").toString());
            Assert.assertNotEquals(count.longValue(), rows);
        });

        And("We verify the below columns {string},{string},{string} data will look like below", (String identifier, String col1, String col2, DataTable dataTable) -> {
            List<String> header = dataTable.row(0);
            List<Map<String, String>> actualResultSet =dataTable.asMaps();

            TableResult tableResult = bigQueryTest.getTableResultSet(executionContext.get("dataSet").toString(), executionContext.get("tableName").toString(), identifier, col1, col2);
            Iterable<FieldValueList> queryResults = tableResult.iterateAll();

            List<Map<String, String>> expectedResultSet = new ArrayList<>();
            for (FieldValueList row : queryResults) {
                Map<String, String> rowMap = new HashMap<>();

                for (int fieldIdx = 0; fieldIdx <= header.size() - 1; fieldIdx++) {
                    if (Objects.isNull(row.get(fieldIdx).getValue())) {
                        rowMap.put(header.get(fieldIdx), null);
                    } else {
                        rowMap.put(header.get(fieldIdx), row.get(fieldIdx).getValue().toString());
                    }
                }

                expectedResultSet.add(rowMap);
            }

            Map<String, Map<String, String>> actualResultsByKey = createResultsByKey(expectedResultSet, identifier);
            Map<String, Map<String, String>> expectedResultsByKey = createResultsByKey(expectedResultSet, identifier);

            MapDifference<String, Map<String, String>> diff = Maps.difference(expectedResultsByKey, actualResultsByKey);

            Assert.assertEquals(diff.entriesDiffering().size(),0);

        });
    }

    private Map<String, Map<String, String>> createResultsByKey(List<Map<String, String>> actualResults, String key) {
        return actualResults.stream().collect(Collectors.toMap(row -> row.get(key), row -> row));
    }

}
