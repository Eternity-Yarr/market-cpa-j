package org.webbitserver.handler;

import org.webbitserver.*;

public class HttpToWebSocketHandler implements HttpHandler {
    private final WebSocketHandler handler;

    public HttpToWebSocketHandler(WebSocketHandler handler) {
        this.handler = handler;
    }

    @Override
    public void handleHttpRequest(HttpRequest request, HttpResponse response, HttpControl control) throws Exception {
        control.upgradeToWebSocketConnection(handler);
    }
}
