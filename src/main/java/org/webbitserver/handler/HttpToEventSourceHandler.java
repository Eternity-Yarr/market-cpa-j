package org.webbitserver.handler;

import org.webbitserver.*;

public class HttpToEventSourceHandler implements HttpHandler {
    private final EventSourceHandler handler;

    public HttpToEventSourceHandler(EventSourceHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception {
        control.upgradeToEventSourceConnection(handler);
    }
}
