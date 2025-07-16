package com.bt.sample.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Model(adaptables = Resource.class)
public class HelloWorldModel {

    private static final Logger LOG = LoggerFactory.getLogger(HelloWorldModel.class);

    @ValueMapValue(name = PROPERTY_RESOURCE_TYPE, injectionStrategy = InjectionStrategy.OPTIONAL)
    @Default(values = "No resourceType")
    protected String resourceType;

    @SlingObject
    private Resource currentResource;

    @SlingObject
    private ResourceResolver resourceResolver;

    private String message;

    @PostConstruct
    protected void init() {
        LOG.debug("Initializing HelloWorldModel for resource: {}", currentResource.getPath());

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        if (pageManager == null) {
            LOG.warn("PageManager could not be adapted from ResourceResolver");
        } else {
            LOG.info("PageManager successfully adapted from ResourceResolver");
        }

        String currentPagePath = Optional.ofNullable(pageManager)
                .map(pm -> pm.getContainingPage(currentResource))
                .map(Page::getPath)
                .orElse("");

        if (currentPagePath.isEmpty()) {
            LOG.error("Could not resolve the current page path for resource: {}", currentResource.getPath());
        } else {
            LOG.debug("Current page path: {}", currentPagePath);
        }

        message = "Hello World!\n"
                + "Resource type is: " + resourceType + "\n"
                + "Current page is:  " + currentPagePath + "\n";

        LOG.info("Generated message: {}", message);
    }

    public String getMessage() {
        LOG.trace("getMessage() called");
        return message;
    }
}
