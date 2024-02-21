# notion-jdbc

notion-jdbc parses your sql statement through apache calcite,
and send requests to the notion api to query the notion database using SQL statements.

## load notion-jdbc to data grip

Before you start, you need to [create a notion integration](https://www.notion.so/my-integrations) and get your internal integration secret.then you need to [add connection to your database pages](https://www.notion.so/help/add-and-manage-connections-with-the-api#add-connections-to-pages) which you want to query with notion-jdbc. 


Enter data grip and double click the 'shift' button. Type 'Driver' into your command bar.
select the 'Driver' option.  
![step1](https://github.com/MRKKmrkk/notion-jdbc/blob/main/img/step-1.png)

After doing that you can see a 'Data Sources and Drivers' menu. click the '+' button , [download](https://github.com/MRKKmrkk/notion-jdbc/releases/download/1.0/notion-jdbc-1.0-SNAPSHOT-jar-with-dependencies.jar) and choose notion-jdbc-1.0-SNAPSHOT-jar-with-dependencies.jar to the right place,
choose the Class as `org.apache.calcite.jdbc.Driver` and apply it.  
![step2](https://github.com/MRKKmrkk/notion-jdbc/blob/main/img/step-2.png)

create model.json on your pc and set the internal integration secret which you created before on model.json :
```json
{
  "version": "1.0",
  "defaultSchema": "notion",
  "schemas": [
    {
      "name": "notion",
      "type": "custom",
      "factory": "org.esni.notion.jdbc.adpter.NotionSchemaFactory",
      "operand": { 
        "token": "Your Internal Integration Secret"
      }
    }
  ]
}
```
Click 'Create Data Source' button on 'Data Sources and Drivers' menu,
set up the URL as `jdbc:calcite:model=path_of_your_model.json`.click apply button and now you can enjoy notion-jdbc on data grip.(all of your table name is your notion database's title but replace space to the '_')  
![step3](https://github.com/MRKKmrkk/notion-jdbc/blob/main/img/step-3.png)




