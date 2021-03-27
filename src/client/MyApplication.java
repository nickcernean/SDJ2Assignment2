package client;

import client.model.Model;
import client.model.ModelManager;
import client.view.ViewHandler;
import client.viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage)
  {
    try
    {
      Model model = new ModelManager();
      ViewModelFactory viewModelFactory = new ViewModelFactory(model);
      ViewHandler viewHandler = new ViewHandler(viewModelFactory);
      viewHandler.start(primaryStage);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
