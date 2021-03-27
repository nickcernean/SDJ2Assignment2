package server;

import server.mediator.ServerConnector;
import server.model.Model;
import server.model.ModelManager;

import java.io.IOException;

public class ServerStart
{
  public static void main(String[] args) throws IOException
  {
    Model model = new ModelManager();
    ServerConnector serverConnector = new ServerConnector(model);
    new Thread(serverConnector).start();
  }
}
