package com.bt.sample.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.Self;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LoggingDemoModel {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingDemoModel.class);

    @Self
    private Resource currentResource;

    @PostConstruct
    protected void init() {
        String resourcePath = currentResource != null ? currentResource.getPath() : "unknown";

        LOG.trace("TRACE log: Resource path is {}", resourcePath);
        LOG.debug("DEBUG log: Initializing LoggingDemoModel for {}", resourcePath);
        LOG.info("INFO log: LoggingDemoModel initialized for resource {}", resourcePath);
        LOG.warn("WARN log: This is just a demo warning for {}", resourcePath);
        LOG.error("ERROR log: Simulating an error log for {}", resourcePath);
    }
}
