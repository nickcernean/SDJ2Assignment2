package server.mediator;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import server.model.Model;

public class ServerClientHandler implements Runnable
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Model model;
  private boolean running;
  private Gson gson;

  public ServerClientHandler(Socket socket, Model model)
      throws IOException
  {
    this.model = model;
    this.socket = socket;
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    this.gson = new Gson();
  }

  @Override
  public void run()
  {
    running = true;
    while (running)
    {
      try
      {
        String fromClient = in.readLine();
        System.out.println("Server> " + fromClient);
        UserPackage userPackage = gson.fromJson(fromClient, UserPackage.class);
        model.addUser(userPackage.getUser(), userPackage.getPassword());
        out.println("Success : you are now logged in");
        close();
      }
      catch (Exception e)
      {
        running = true;
        e.printStackTrace();
        out.println("Server> " + e.getMessage());
      }
    }
    close();
  }

  public void close()
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      //
    }
  }
}