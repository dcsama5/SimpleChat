package com.ameer.simplechat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Ameer
 */
public class Server implements Runnable{
    
    CustomSocket connection;
    CustomServerSocket server;
    static Set<CustomSocket> sockets = new HashSet<CustomSocket>();
    
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
        }
        catch(IOException ex)
        { }
        
    }

    public static void broadcastMessage(String message) {
        Iterator<CustomSocket> it = sockets.iterator();
        System.out.println("Trying to broadcast message");
        while(it.hasNext()){
            CustomSocket socket = it.next();
            System.out.println("socket " +socket.toString());
            if(!socket.isClosed())
            socket.sendMessage(socket.toString() + " " + message);
        }
    }

    public void run() {

    while(true)
    {
        try {
        connection = (CustomSocket) server.accept();
        connection.initializeStream();
        sockets.add(connection);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    }
}