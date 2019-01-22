package com.jen;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends  Thread {
    private final int serverport;

    private ArrayList<ServerWorker> workerList =new ArrayList<>();

    public Server(int serverPort) {
        this.serverport=serverPort;
    }


    public List<ServerWorker> getWorkerList() {
        return workerList;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket= new ServerSocket(serverport);
            while(true) {
                System.out.println("about to accept the client connections...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("connected with "+clientSocket);
                ServerWorker worker=new ServerWorker(this,clientSocket);
                workerList.add(worker);
                worker.start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeWorker(ServerWorker serverWorker) {
        workerList.remove(serverWorker);
    }
}
