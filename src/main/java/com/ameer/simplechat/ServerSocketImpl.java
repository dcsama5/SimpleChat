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
public class ServerSocketImpl extends AbstractSocket {

    
    public void setName() throws IOException {
        String name = super.reader.readLine();
        super.name = name;
    }
    @Override
    public void run() {
        try {
            while(true) {
                String message = super.reader.readLine();
                if(!message.equals("shutdown"))
                    System.out.println(this.getName()+":"+message);
                else
                {
                    super.shutDown();
                    System.out.println(this.getName()+ " has shut the connection");
                    Server.removeConnection(this);
                    break;
                }
                Server.broadcastMessage(this.getName(), message);
            }
        }
        catch(IOException ex)
        {
            System.out.println(this.getName() + " has left the server and probably didn't exit gracefully");
        }
    }
    
}
