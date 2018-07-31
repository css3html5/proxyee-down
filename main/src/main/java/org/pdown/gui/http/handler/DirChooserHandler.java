package org.pdown.gui.http.handler;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import org.pdown.gui.com.Components;
import org.pdown.gui.http.util.HttpHandlerUtil;

public class DirChooserHandler implements HttpHandler {

  @Override
  public FullHttpResponse handle(Channel channel, FullHttpRequest request) throws Exception {
    Platform.runLater(() -> {
      File file = Components.dirChooser();
      Map<String, Object> result = new HashMap<>();
      Map<String, Object> detail = null;
      if (file != null) {
        detail = new HashMap<>();
        detail.put("path", file.getPath());
        detail.put("canWrite", file.canWrite());
        detail.put("freeSpace", file.getFreeSpace());
        detail.put("totalSpace", file.getTotalSpace());
      }
      result.put("data", detail);
      HttpHandlerUtil.writeJson(channel, result);
    });
    return null;
  }
}