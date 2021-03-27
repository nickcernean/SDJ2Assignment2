package client.mediator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LogInClient implements ServerModel
{
  public static final String HOST = "localhost";
  public static final int PORT = 6789;
  private String host;
  private int port;
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;

  public LogInClient(String host, int port) throws IOException
  {
    this.host = host;
    this.port = port;
    this.socket = new Socket();
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
  }

  public LogInClient() throws IOException
  {
    this.host = HOST;
    this.port = PORT;
    this.socket = new Socket(host, port);
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
  }

  @Override public void connect() throws IOException
  {
    this.socket = new Socket(HOST, PORT);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
  }

  @Override public void disconnect() throws IOException
  {
    in.close();
    out.close();
    socket.close();
  }

  @Override public String logIn(String source) throws IOException
  {
    out.println(source);
    return in.readLine();
  }
}
