Feature: GCP BigQuery CSV File

  Scenario: Feed Data to BigQuery Table ScenarioHistoricalTable

    Given GCP BigQuery has a Dataset 'AccountDataSet' and TableName 'ScenarioHistoricalTable'
    When I feed the data from CSV file 'scenario-feed-data.csv'
    Then Records should be saved "OK"
    And We verify the below columns 'ID','CPPI_AS','CPPI_CS' data will look like below

      |ID                                  |CPPI_AS    |CPPI_CS|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C64|226.7333333|214.1333333|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C65|229.9666667|217.2|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C66|232.9333333|220.4|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C67|202.3333333|191.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C68|199.5|188.9333333|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C69|184.4666667|171.9|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C70|202.3333333|191.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C71|202.3333333|191.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C72|202.3333333|191.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C73|191.5333333|181.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C74|191.5333333|181.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C75|191.5333333|181.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C76|199.5|188.9333333|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C77|202.3333333|191.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C78|204.7666667|193.6333333|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C79|206.9|195.5|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C80|210.6666667|199.4333333|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C81|210.6666667|199.4333333|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C82|244.25|232.55|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C83|233.8452381|221.6023809|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C84|235.3136054|223.055102|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C85|236.5393586|224.3296404|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C86|195.3666667|185.2|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C87|199.5|188.9333333|
      |DDB4D0A7-3B77-0F64-4E98-7DDE0BCC1C88|226.7333333|214.1333333|



