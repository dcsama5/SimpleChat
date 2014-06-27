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
import java.net.SocketException;
import java.net.SocketImpl;

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
    
    public CustomSocket() throws IOException
    {
        super();   
    }
    
    public void initializeStream()
    {
        try {
        out = this.getOutputStream();
        printWriter = new PrintWriter(out);
        in = this.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));
        readerThread = new Thread(this);
        readerThread.start();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public void sendMessage(String message) 
    {
        System.out.println("attempting to send message");
        printWriter.println(message);
        printWriter.flush();
    }

    public void run() {
        while(true)
        {
            try {
            String message = reader.readLine();
            System.out.println("recieved message " + this.toString() + " " + message);
            Server.broadcastMessage(message);
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
            
        }
    }

  
}