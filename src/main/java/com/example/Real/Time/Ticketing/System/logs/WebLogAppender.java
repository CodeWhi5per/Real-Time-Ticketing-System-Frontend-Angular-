package com.example.Real.Time.Ticketing.System.logs;


import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Plugin(name = "WebLogAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE, printObject = true)
public class WebLogAppender extends AbstractAppender {

    private static final BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();

    public WebLogAppender(String name, Layout<? extends Serializable> layout) {
        super(name, null, layout, false, null);
    }

    @PluginFactory
    public static WebLogAppender createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout) {
        if (name == null) {
            LOGGER.error("No name provided for WebLogAppender");
            return null;
        }
        if (layout == null) {
            layout = PatternLayout.createDefaultLayout();
        }
        return new WebLogAppender(name, layout);
    }

    @Override
    public void append(LogEvent event) {
        String message = event.getMessage().getFormattedMessage();
        if (message.startsWith("Vendor") || message.startsWith("Customer") || message.startsWith("VIP Customer")) {
            logQueue.offer(getLayout().toSerializable(event).toString());
        }
    }


    public static BlockingQueue<String> getLogQueue() {
        return logQueue;
    }
}

