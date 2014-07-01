/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ameer.simplechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketImpl;

/**
 *
 * @author ameer
 */
public abstract class AbstractSocket extends Socket implements Runnable {
    
    protected PrintWriter printWriter;
    protected OutputStream out;
    protected SocketImpl impl;
    protected InputStream in;
    protected BufferedReader reader;
    protected Thread readerThread;
    protected String name;
    
    public AbstractSocket()
    {
        super();
    }
    
    public AbstractSocket(String host, int port) throws IOException 
    {
        super(host, port);
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
    public void runInputStream()
    {
        readerThread.start();
    }

    public void shutDown() throws IOException
    {
        out.close();
        in.close();
        this.close();
    }
    public PrintWriter getWriter() {
        return this.printWriter;
    }
    public void sendMessage(String message)
    {
        printWriter.println(message);
        printWriter.flush();
    }
    public String getName()
    {
        return name;
    }
    public abstract void run();
}
