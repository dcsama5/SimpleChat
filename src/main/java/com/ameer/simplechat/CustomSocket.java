package com.ameer.simplechat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ameer
 */
public class CustomSocket extends Socket implements Runnable {
    
    private PrintWriter printWriter;
    private OutputStream out;
    private SocketImpl impl;
    private InputStream in;
    private BufferedReader reader;
    private Thread readerThread;
    private String name;
    
    public CustomSocket() throws IOException
    {
        super();
    }
    
    public CustomSocket(String host, int port, String name) throws IOException
    {
        super(host, port);
        this.name = name;
    }
    
    public void initializeStream()
    {
        try {
        out = this.getOutputStream();
        printWriter = new PrintWriter(out);
        in = this.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        readerThread = new Thread(this);
        }
        catch(IOException ex)
        {
            System.out.println("Error when initialising the stream");
            ex.printStackTrace();
        }
    }
    
    public void shutDown() throws IOException {
        out.close();
        in.close();
        this.close();
    }
    
    public String getName()
    {
        return name;
    }
    
    public void runInputStream()
    {
        readerThread.start();
    }
    public PrintWriter getWriter()
    {
        return this.printWriter;
    }
    public void setName() throws IOException {
        String name = reader.readLine();
        System.out.println("assigning name to " + name);
        this.name = name;}
    
    public void sendMessage(String message) 
    {
        System.out.println("sending message : " + message);
        printWriter.println(message);
        printWriter.flush();
    }

    public void run() {
        try {
        while(true)
        {
            String message = reader.readLine();
            if(!message.equals("shutdown")){
                System.out.println(message);
            }
            else
            { shutDown();
            break;
            }
            Server.broadcastMessage(message);
        }
        }
        catch(IOException ex)
        {
           System.out.println(this.getName() + " has left the server");
        }
    }

  
}