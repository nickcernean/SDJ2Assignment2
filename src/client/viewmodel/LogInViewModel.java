package client.viewmodel;

import client.model.Model;
import com.google.gson.Gson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import server.mediator.UserPackage;

import java.io.IOException;
import java.util.Optional;

public class LogInViewModel
{
  private StringProperty nameProperty;
  private StringProperty passwordProperty;
  private StringProperty errorProperty;
  private Model model;
  private Gson json;

  public LogInViewModel(Model model)
  {
    this.json = new Gson();
    this.model = model;
    nameProperty = new SimpleStringProperty();
    passwordProperty = new SimpleStringProperty();
    errorProperty = new SimpleStringProperty();
  }

  public void reset()
  {
  }

  public boolean alert()
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Information");
    alert.setHeaderText("You successfully logged in to BOOKCHAT! ");
    Optional<ButtonType> result = alert.showAndWait();
    return (result.isPresent()) && (result.get() == ButtonType.OK);
  }

  public void logIn() throws IOException
  {
    try
    {
      UserPackage userPackage = new UserPackage(nameProperty.get(),
          passwordProperty.get());

      String toServer = json.toJson(userPackage);
      String serverReply = model.logIn(toServer);

      if (serverReply.startsWith("Success"))
      {
        alert();
      }
      else
      {
        errorProperty.set(serverReply);
      }
      nameProperty.set("");
      passwordProperty.set("");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public StringProperty getNameProperty()
  {
    return nameProperty;
  }

  public StringProperty getPasswordProperty()
  {
    return passwordProperty;
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }
}
