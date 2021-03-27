package client.mediator;

import java.io.IOException;

public interface ServerModel
{
  void connect() throws IOException;
  void disconnect() throws IOException;
String logIn(String source) throws IOException;
}
