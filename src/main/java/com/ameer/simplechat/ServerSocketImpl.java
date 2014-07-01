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
                System.out.println("HIT FOR NAME" +super.getName());

    }
    @Override
    public void run() {
        try {
            while(true) {
                String message = super.reader.readLine();
                System.out.println("THIS MESSAGE IS "+message);
                if(!message.equals("shutdown"))
                    System.out.println(message);
                else
                {
                    super.shutDown();
                    System.out.println("trying to break");
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
