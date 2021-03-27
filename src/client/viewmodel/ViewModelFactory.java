package client.viewmodel;

import client.model.Model;

public class ViewModelFactory
{
  private LogInViewModel logInViewModel;
  private ChatViewModel chatViewModel;

  public ViewModelFactory(Model model)
  {
    logInViewModel = new LogInViewModel(model);
    chatViewModel = new ChatViewModel(model);
  }

  public LogInViewModel getLogInViewModel()
  {
    return logInViewModel;
  }

  public ChatViewModel getChatViewModel()
  {
    return chatViewModel;
  }
}
