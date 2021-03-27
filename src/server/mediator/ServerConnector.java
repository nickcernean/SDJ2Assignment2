package server.mediator;

import server.model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector implements Runnable
{
  private final int PORT = 6789;
  private Model model;
  private boolean running;
  private ServerSocket welcomeSocket;

  public ServerConnector(Model model)
  {
    this.model = model;
  }

  @Override public void run()
  {
    try
    {
      System.out.println("Starting server....");
      welcomeSocket = new ServerSocket(PORT);

      running = true;
      while (running)
      {
        System.out.println("Waiting for a client");
        Socket socket = welcomeSocket.accept();
        ServerClientHandler clientHandler = new ServerClientHandler(socket, model);
        Thread clientThread = new Thread(clientHandler);
        clientThread.setDaemon(true);
        clientThread.start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void close()
  {
    running = false;
    try
    {
      welcomeSocket.close();
    }
    catch (Exception e)
    {
      //
    }
  }

}

