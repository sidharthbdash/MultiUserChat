package com.jen;


public class ServerMain
{
    public static void main(String[] args) {
        int port=5090;
        Server server= new Server(port);
        server.start();

    }


}
