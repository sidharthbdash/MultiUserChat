package com.jen;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChatClient {
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private InputStream serverIn;
    private OutputStream serverOut;

    public ChatClient(String serverName, int serverPort) {
        this.serverName =serverName;
        this.serverPort = serverPort;
    }

    public static void main(String[] args) throws IOException {
        ChatClient client= new ChatClient("localhost",5090);
        if(!client.connect()){
            System.err.println("failed");
        }else {
            System.out.printf("Connection successfull");
            client.login("jenar","jenar");
        }
    }

    private void login(String login, String password) throws IOException {
        String cmd= "login "+ login +" "+password + "\n";
        serverOut.write(cmd.getBytes());

    }

    private boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            System.out.println("the client port is "+socket.getLocalPort());
            this.serverOut= socket.getOutputStream();
            this.serverIn= socket.getInputStream();

            return  true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  false;
    }

}
