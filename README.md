# Getting Started of Apache Camel
### EIP: ```Enterprise Integration Patterns```
* ### Routing:
A route in Apache Camel is a sequence of steps, executed in order by Camel, that consume and process a message. A Camel route starts with a consumer, and is followed by a chain of endpoints and processors.:
``` #properties
public class MyRouterClass extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:someUrl")
            .bean(myService, "loadData");

        from("direct:someUrl")
            .process(new MyProcessorClass());

        from("direct:someUrl")
            .process(()-> doSmth)
            .to("direct:someUrl2");
    }
}
``` 
* ### Processor:
The Processor interface is used to implement consumers of message exchanges or to implement a Message Translator, and other use-cases.:
``` #properties
public class MyProcessorClass implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        // do something...
    }
}
``` 
* ### Components - <i>File watcher</i>:
To monitor all of the files in a given directory for changes(Create , Modify and Delete):
``` #properties
public class FileRoutingBuilder extends RouteBuilder {
    private final static String DIR_FILE = String.format("//%s/%s", FileConstants.FILES, "watch");

    @Override
    public void configure() throws Exception {
        from(String.format("file-watch:%s", DIR_FILE))
                .log("File event: ${header.CamelFileEventType} occurred on file ${header.CamelFileName} at ${header.CamelFileLastModified}");
        from(String.format("file-watch:%s%s", DIR_FILE, "?events=DELETE,CREATE&antInclude=**/*.txt"))
                .log("File event: ${header.CamelFileEventType} occurred on file ${header.CamelFileName} at ${header.CamelFileLastModified}");
        from(String.format("file-watch:%s%s", DIR_FILE, "?events=MODIFY&recursive=false"))
                .setHeader(Exchange.FILE_NAME, simple("${header.CamelFileName}.${header.CamelFileLastModified}"))
                .to(String.format("file:%s/%s", DIR_FILE, "snapshots"));
    }
}
``` 
* [Microservice orchestration with Apache Camel](https://har-d.medium.com/microservice-orchestration-with-apache-camel-15ae9d108ba)
* [microservices-with-apache-camel](https://dzone.com/articles/microservices-with-apache-camel)
