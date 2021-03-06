package com.ameer.simplechat;

/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/


import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
import javax.swing.JOptionPane;
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
            String name = JOptionPane.showInputDialog(null, "ENTER NAME TO CONNECT WITH");
            ClientSocket connection = new ClientSocket("192.168.1.10", 13, name);
            connection.initializeStream();
            //first message invoking the first read line statement from run thread
            connection.getWriter().println(name);
            connection.getWriter().flush();
            String message;
            connection.runInputStream();
            do
            {
                System.out.println("Write your message");
                message = scanner.nextLine();
                System.out.println(connection.getName() + ":"+message);
                connection.sendMessage(message);
            }
            while(!message.equals("shutdown"));
            System.out.println("closing stream");
            connection.shutDown();
        }
        catch(UnknownHostException uh)
        {
            System.out.println(uh);
        }
        catch(IOException io)
        {
            System.out.println("error " + io);
        }
    }
    
}
