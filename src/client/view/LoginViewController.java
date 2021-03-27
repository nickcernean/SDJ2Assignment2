package client.view;

import client.viewmodel.LogInViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class LoginViewController
{
  @FXML private Label errorLabel;
  @FXML private TextField usernameField;
  @FXML private TextField passwordField;
  @FXML private Button logInButton;
  @FXML private Button submitButton;
  private ViewHandler viewHandler;
  private LogInViewModel viewModel;
  private Region root;

  public void init(ViewHandler viewHandler, LogInViewModel logInViewModel,
      Region root)
  {
    this.viewModel = logInViewModel;
    this.viewHandler = viewHandler;
    this.root = root;
    //To DO the bindings
    usernameField.textProperty().bindBidirectional(viewModel.getNameProperty());
    passwordField.textProperty()
        .bindBidirectional(viewModel.getPasswordProperty());
    errorLabel.textProperty().bind(viewModel.getErrorProperty());

  }

  @FXML public void onEnter(ActionEvent event)
  {
    if (event.getTarget() == usernameField)
    {
      passwordField.requestFocus();
    }
    else if (event.getTarget() == passwordField)
    {
      logInButton.requestFocus();
    }
    else if (event.getTarget() == logInButton)
    {
      logIn();
    }
  }

  @FXML public void logIn()
  {
    try
    {
      viewModel.logIn();
      viewHandler.openView("CHAT");
      usernameField.setText("");
      passwordField.setText("");

    }
    catch (Exception e)
    {
      errorLabel.setText(e.getMessage());

    }

  }

  public void signUp()
  {
    // TO Add to a database
  }

  public void reset()
  {
    viewModel.reset();
  }

  public Region getRoot()
  {
    return root;
  }
}
