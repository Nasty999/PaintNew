package main.gui.actions;

import main.net.Client;
import main.net.Message;
import main.gui.DialogWindow;
import main.gui.MainFrame;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class User {
    private final MainFrame mainFrame;

    private final Client client;

    private final Action newUserAction;

    private final Action loginAction;

    private final Action changeNameAction;

    private final Action changePasswordAction;


    public User(MainFrame mainFrame, Client client) {
        this.mainFrame = mainFrame;
        this.client = client;

        newUserAction = new NewUserAction();
        loginAction = new LoginAction();
        changeNameAction = new ChangeNameAction();
        changePasswordAction = new ChangePasswordAction();
    }

    public Action getNewUserAction() {
        return newUserAction;
    }

    public Action getLoginAction() {
        return loginAction;
    }

    public Action getChangeNameAction() {
        return changeNameAction;
    }

    public Action getChangePasswordAction() {
        return changePasswordAction;
    }

    private class NewUserAction extends AbstractAction {
        NewUserAction() {
            super("Registration");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            DialogWindow authDialog = new DialogWindow(mainFrame);
            client.sendToServer(new Message(6,authDialog.getUserName() + ";" + authDialog.getPassword()));
            Message answer = client.getMessage();
            if (answer.getMessage().equals("OK")) {
                mainFrame.setIsConnected(true);
                JOptionPane.showMessageDialog(mainFrame, "Registration OK");
            } else if(answer.getMessage().equals("Exists")) {
                JOptionPane.showMessageDialog(mainFrame, "A User with that name already exists");
            }
        }
    }

    private class LoginAction extends AbstractAction {
        LoginAction() {
            super("Login");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            DialogWindow authDialog = new DialogWindow(mainFrame);
            client.sendToServer(new Message(5,authDialog.getUserName() + ";" + authDialog.getPassword()));
            Message answer = client.getMessage();
            if (answer.getMessage().equals("OK")) {
                mainFrame.setIsConnected(true);
                JOptionPane.showMessageDialog(mainFrame, "LOGIN OK");
            } else if(answer.getMessage().equals("Exists")) {
                JOptionPane.showMessageDialog(mainFrame, "User's name/password invalid");
            }
        }
    }

    private class ChangeNameAction extends AbstractAction {
        ChangeNameAction() {
            super("Change name");
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(mainFrame.getIsConnected()) {
                String name = JOptionPane.showInputDialog(mainFrame, "Enter a new name");
                client.sendToServer(new Message(7, name));
                Message answer = client.getMessage();
                if (answer.getMessage().equals("OK")) {
                    JOptionPane.showMessageDialog(mainFrame, "Change name OK");
                } else if(answer.getMessage().equals("Exists")) {
                    JOptionPane.showMessageDialog(mainFrame, "User's name already exists");
                }
            } else  {
                JOptionPane.showMessageDialog(mainFrame, "You are not logged in!!!");
            }
        }
    }

    // TODO: 16.12.2019 Безопасная передача пароля
    private class ChangePasswordAction extends AbstractAction {
        ChangePasswordAction() {
            super("Change password");
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(mainFrame.getIsConnected()) {
                String password = JOptionPane.showInputDialog(mainFrame, "Enter a new password");
                client.sendToServer(new Message(8, password));
                Message answer = client.getMessage();
                if (answer.getMessage().equals("OK")) {
                    JOptionPane.showMessageDialog(mainFrame, "Change password OK");
                } else if(answer.getMessage().equals("Exists")) {
                    JOptionPane.showMessageDialog(mainFrame, "Something went wrong");
                }
            } else  {
                JOptionPane.showMessageDialog(mainFrame, "You are not logged in!!!");
            }
        }
    }
}



