package org.ai.order.resource;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;

/**
 * Created by hua.ai on 2016/4/17.
 */
@Path("events")
public class SseResource {

    private static final Logger LOG = LoggerFactory.getLogger(SseResource.class);

    @GET
    @Produces(SseFeature.SERVER_SENT_EVENTS)
    public EventOutput get() throws IOException {
        final EventOutput eventOutput = new EventOutput();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sendEvent();
                } catch (IOException e) {
                    LOG.error("Error caused", e);
                }
            }

            private void sendEvent() throws IOException {
                try {
                    for (int i = 0; i < 10; i++) {
                        final OutboundEvent.Builder builder = new OutboundEvent.Builder();
                        builder.name("message-to-client");
                        builder.data(String.class, "Hello World " + i + " !");
                        final OutboundEvent event = builder.build();
                        eventOutput.write(event);
                    }
                } finally {
                    eventOutput.close();
                }
            }
        }).start();
        return eventOutput;
    }
}
