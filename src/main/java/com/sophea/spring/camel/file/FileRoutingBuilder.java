package com.sophea.spring.camel.file;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileRoutingBuilder extends RouteBuilder {
    @Value("${file.directory.watcher}")
    private String dirFile;
    @Value("${file.directory.snapshot}")
    private String dirSnapshot;

    private String getDirWatcher() {
        if (dirFile.startsWith("//"))
            return dirFile;
        return String.format("//%s", dirFile);
    }

    private String getDirSnapshot() {
        if (dirSnapshot.startsWith("//"))
            return dirSnapshot;
        return String.format("//%s", dirSnapshot);
    }

    @Override
    public void configure() throws Exception {
        from(String.format("file-watch:%s", this.getDirWatcher()))
                .log(LoggingLevel.INFO, "com.sophea.spring.camel.file.watch", "File event: ${header.CamelFileEventType} occurred on file ${header.CamelFileName} at ${header.CamelFileLastModified}");
        from(String.format("file-watch:%s%s", this.getDirWatcher(), "?events=DELETE,CREATE&antInclude=**/*.txt"))
                .log("File event: ${header.CamelFileEventType} occurred on file ${header.CamelFileName} at ${header.CamelFileLastModified}");
        from(String.format("file-watch:%s%s", this.getDirWatcher(), "?events=MODIFY&recursive=false"))
                .setHeader(Exchange.FILE_NAME, simple("${header.CamelFileName}.${header.CamelFileLastModified}"))
                .to(String.format("file:%s", this.getDirSnapshot()));
    }
}
