package com.lbg.feature.bquery;

import com.google.cloud.bigquery.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.bigquery.core.BigQueryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.InputStream;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BigQueryTestService {

    @Autowired
    private BigQuery bigquery;


    public Boolean loadCSVDataToGcpBigQuery(String dataTable, String tableName, InputStream inputStream) throws ExecutionException, InterruptedException {
        BigQueryTemplate bigQueryTemplate = new BigQueryTemplate(bigquery, dataTable);
        ListenableFuture<Job> bigQueryJobFuture = bigQueryTemplate.writeDataToTable(tableName, inputStream, FormatOptions.csv());
        Job job = bigQueryJobFuture.get();
        job.waitFor();
        return job.isDone();
    }

    public Iterable<?> readDataFromGcpBigQuery(String query) throws InterruptedException {
        return query(query);
    }

    public Iterable<?> query(String query) throws InterruptedException {
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder(query).build();
        return bigquery.query(queryConfig).iterateAll();
    }

    public void deleteTableData(String dataSet, String tableName) throws InterruptedException {
        readDataFromGcpBigQuery("delete FROM " + dataSet + "." + tableName + " where true");
    }

    public long getCountOfResultSet(String dataSet, String tableName) throws InterruptedException {
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("Select count(*) FROM " + dataSet + "." + tableName + "").build();
        return bigquery.query(queryConfig).getTotalRows();
    }

    public TableResult getTableResultSet(String dataSet, String tableName, String... cols) throws InterruptedException {
        String queryCols = Stream.of(cols).collect(Collectors.joining(","));
        QueryJobConfiguration queryConfig = QueryJobConfiguration.newBuilder("Select " + queryCols + " FROM " + dataSet + "." + tableName + "").build();
        return bigquery.query(queryConfig);
    }

}
