package com.ameer.simplechat;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/


import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
*
* @author Ameer
*/
public class Server implements Runnable{
	
	CustomServerSocket server;
	static Set<ServerSocketImpl> sockets = new HashSet<ServerSocketImpl>();
	static int count = 0;
	public Server() throws IOException
	{
		try {
			server = new CustomServerSocket(13);
		}
		catch(IOException ex)
		{ 
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		try {
			Thread t = new Thread(new Server());
			t.start();
                        Scanner scanner = new Scanner(System.in);
                        System.out.println("Hello. This is server console. Type message");
                        String message = scanner.nextLine();
                        while(!message.equals("quit"))
                        {
                            broadcastMessage("Server:", message);
                            message = scanner.nextLine();
                        }
		}
		catch(IOException ex)
		{ }
	}
	
	public static void broadcastMessage(String user, String message) {
            System.out.println(count + " "+  sockets.size());
		Iterator<ServerSocketImpl> it = sockets.iterator();
                printConnectedSockets();
		while(it.hasNext()){
			ServerSocketImpl socket = it.next();
			if(!socket.getName().equalsIgnoreCase(user))
                        {
                            if(!socket.isClosed())
                            {
                                socket.sendMessage(user+":"+message);
                            }
                        }
                        
		}
	}
        
        public static void removeConnection(Socket sock) {
           sockets.remove(sock);
        }
        
       private static void printConnectedSockets() {
           for(ServerSocketImpl impl : sockets)
           {
              System.out.println(impl.isClosed() ? impl.getName()+" closed" : impl.getName() +" open");
           }
       }
	
	public void run() {
		while(true)
		{
			try {
				ServerSocketImpl connection = (ServerSocketImpl) server.accept();
				connection.initializeStream();
                                connection.setName();
                                connection.runInputStream();
                                System.out.println(connection.getName() +" has connected");
				sockets.add(connection);
                                count++;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}
