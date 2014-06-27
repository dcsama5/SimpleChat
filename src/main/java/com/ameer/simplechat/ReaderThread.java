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
import java.net.Socket;
/**
 *  This class is a thread that sets up an InputStream between 2 sockets
 * @author ameer
 */
public class ReaderThread implements Runnable {
    
    InputStream in;
    BufferedReader reader;
    
    /**
     * public ReaderThread constructor which initialises the input stream to read
     * from the client side. 
     * @param connection
     * @throws IOException 
     */
    public ReaderThread(Socket connection) throws IOException
    {
        in = connection.getInputStream();
        reader = new BufferedReader(new InputStreamReader(in));
    }
    
    public void run()
    {
        String message = null;
        while(true) {
        try {
                message = reader.readLine();
                System.out.println(message);
        }
        catch(Exception io)
        {
            try {
            reader.close();
            break;
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
        }
    }
}
