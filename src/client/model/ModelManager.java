package client.model;

import client.mediator.LogInClient;
import client.mediator.ServerModel;

import java.io.IOException;

public class ModelManager implements Model
{
  private ServerModel serverModel;

  public ModelManager() throws IOException
  {
    this.serverModel = new LogInClient();
    serverModel.connect();
  }

  @Override public String logIn(String source) throws Exception
  {
    return serverModel.logIn(source);
  }
}
