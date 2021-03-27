package client.view;

import client.viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler
{
  private Scene currentScene;
  private Stage primaryStage;
  private ViewModelFactory modelFactory;

  private LoginViewController loginViewController;
  private ChatViewController chatViewController;

  public ViewHandler(ViewModelFactory viewModelFactory)
  {
    this.modelFactory = viewModelFactory;
  }

  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    this.currentScene = new Scene(new Region());
    //TO DO with enum
    openView("LOGIN");
  }

  public void openView(String fxml)
  {
    Region root = null;
    switch (fxml)
    {
      case "LOGIN":
        root = loadLogInView("LoginView.fxml");
        break;
      case "CHAT":
        root = loadChatView("ChatView.fxml");
        break;
    }
    currentScene.setRoot(root);
    String title = "";
    if (root.getUserData() != null)
    {
      title += root.getUserData();
    }
    primaryStage.setTitle(title);
    primaryStage.setScene(currentScene);
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.show();
  }

  private Region loadChatView(String fxml)
  {
    if (chatViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Region root = loader.load();
        chatViewController = loader.getController();
        chatViewController.init(this, modelFactory.getLogInViewModel(), root);

      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      chatViewController.reset();
    }
    return chatViewController.getRoot();
  }

  private Region loadLogInView(String fxml)
  {
    if (loginViewController == null)
    {
      try
      {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fxml));
        Region root = loader.load();
        loginViewController = loader.getController();
        loginViewController.init(this, modelFactory.getLogInViewModel(), root);

      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      loginViewController.reset();
    }
    return loginViewController.getRoot();
  }
}
