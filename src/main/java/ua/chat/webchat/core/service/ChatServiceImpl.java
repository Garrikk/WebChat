package ua.chat.webchat.core.service;

import java.util.ArrayList;
import ua.chat.webchat.core.entity.ChatMessage;
import ua.chat.webchat.core.entity.ChatPerson;
import ua.chat.webchat.core.dao.DAO;

public class ChatServiceImpl implements ChatService
{
        @Override
	public ChatPerson[] getAllUsers()
	{
		ArrayList<ChatPerson> users = DAO.getInstance().getAllUsers();
		return users.toArray(new ChatPerson[users.size()]);
	}
	
        @Override
	public ChatMessage[] getAllMessages()
	{
		ArrayList<ChatMessage> messages = DAO.getInstance().getAllMessages();
		return messages.toArray(new ChatMessage[messages.size()]);
	}

        @Override
	public void postMessage(String message, String nickname) 
	{
		 DAO.getInstance().postMessage(message, nickname);
	}

        @Override
	public ChatPerson[] getOnlineUsers() 
	{
		ArrayList<ChatPerson> onlineUsers = DAO.getInstance().getOnlineUsers();
		return onlineUsers.toArray(new ChatPerson[onlineUsers.size()]);
	}

        @Override
	public int login(String nickname, String password) 
	{
		int loginResult = DAO.getInstance().loginUser(nickname, password);
		return loginResult;
	}

        @Override
	public int registerNewUser(String nickname, String password)
	{
		int regResult = DAO.getInstance().registerUser(nickname, password);
		return regResult;
	}

        @Override
	public int logout(String nickname, String password)
	{
		int logoutResult = DAO.getInstance().logoutUser(nickname, password);
		return logoutResult;
	}
}
