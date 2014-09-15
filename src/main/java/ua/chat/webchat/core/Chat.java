package ua.chat.webchat.core;

import java.util.ArrayList;
import ua.chat.webchat.service.ChatService;

public class Chat implements ChatService
{
        @Override
	public ChatPerson[] getAllUsers()
	{
		ArrayList<ChatPerson> users = DataAccessLayer.getInstance().getAllUsers();
		return users.toArray(new ChatPerson[users.size()]);
	}
	
        @Override
	public ChatMessage[] getAllMessages()
	{
		ArrayList<ChatMessage> messages = DataAccessLayer.getInstance().getAllMessages();
		return messages.toArray(new ChatMessage[messages.size()]);
	}

        @Override
	public void postMessage(String message, String nickname) 
	{
		 DataAccessLayer.getInstance().postMessage(message, nickname);
	}

        @Override
	public ChatPerson[] getOnlineUsers() 
	{
		ArrayList<ChatPerson> onlineUsers = DataAccessLayer.getInstance().getOnlineUsers();
		return onlineUsers.toArray(new ChatPerson[onlineUsers.size()]);
	}

        @Override
	public int login(String nickname, String password) 
	{
		int loginResult = DataAccessLayer.getInstance().loginUser(nickname, password);
		return loginResult;
	}

        @Override
	public int registerNewUser(String nickname, String password)
	{
		int regResult = DataAccessLayer.getInstance().registerUser(nickname, password);
		return regResult;
	}

        @Override
	public int logout(String nickname, String password)
	{
		int logoutResult = DataAccessLayer.getInstance().logoutUser(nickname, password);
		return logoutResult;
	}
}
