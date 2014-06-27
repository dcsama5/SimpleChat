package com.ameer.simplechat;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 *
 * @author Ameer
 */
public class Client {
    
    static Scanner scanner;
    
    
    public static void main(String[] args)
    {
        
        scanner = new Scanner(System.in);
        try{
        Socket connection = new Socket("localhost", 13);
        Thread readerThread = new Thread(new ReaderThread(connection));
        readerThread.start();
        OutputStream out = connection.getOutputStream();
        PrintWriter printer = new PrintWriter(out);
        String message;
        do 
        {
            System.out.println("write the message");
            message = scanner.nextLine();
            //IOUtils.write(message, out, "UTF-8");
            printer.println(message);
            printer.flush();
            System.out.println("Sent the message");
        }
        while(!message.equals("quit"));
        }
        catch(UnknownHostException uh)
        {
            
        }
        catch(IOException io)
        {
            
        }
    }
    
}
