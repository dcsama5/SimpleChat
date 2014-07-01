/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ameer.simplechat;

import java.io.IOException;

/**
 *
 * @author ameer
 */
public class ClientSocket  extends AbstractSocket {

    public ClientSocket(String host, int port, String name) throws IOException
    {
        super(host, port);
        this.name = name;
    }
    
    @Override
    public void run() {
        try {
            while(true)
            {
                String message = super.reader.readLine();
                System.out.println(message);
            }
            
        }
        catch(IOException ex) {
            System.err.println("error on ClientSocket read Thread");
        }
    }
    
}
