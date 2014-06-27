package com.ameer.simplechat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketImpl;

/**
 *
 * @author ameer
 */
public class CustomServerSocket extends ServerSocket{
    
    public CustomServerSocket(int port) throws IOException
    {
        super(port);
    }
    
    public Socket accept() throws IOException {
        if (isClosed())
        {
            System.out.println("closed");
            throw new SocketException("Socket is closed");
        }
        if (!isBound())
        {
            System.out.println("not bound");
            throw new SocketException("Socket is not bound yet");
        }
            Socket s = new CustomSocket();
            implAccept(s);
            return s;
    }
}
