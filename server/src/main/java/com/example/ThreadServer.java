package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadServer extends Thread {
    Socket socket;
    Biglietti b;
    ArrayList<ThreadServer> listaThread;
    BufferedReader inClient;
    DataOutputStream outClient;

    public ThreadServer(Socket socket, Biglietti b,ArrayList<ThreadServer> listaThread) {
        this.socket = socket;
        this.b = b;
        this.listaThread = listaThread;
    }

    public void broadcast(String s) throws IOException{
        for (ThreadServer thread : listaThread) {
            System.out.println("mando");
            if(!currentThread().equals(thread)){
                thread.send(s);
                
            }
        }
    }
    private void send(String message) throws IOException{
        this.outClient = new DataOutputStream(socket.getOutputStream());
        //rispondo
        outClient.writeBytes(message + "\n");
    }
    private void send(int n) throws IOException{
        send("" + n);
    }

    public void run() {
        try {
            // creao i tubi
            this.inClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // invio al client
            do {
                // leggo il numero in arrivo dal client
                String richiesta = inClient.readLine();
                int risposta = 0;
                boolean finiti = false;

                switch (richiesta) {
                    case "D":
                        risposta = b.getQuantita();
                        break;
                
                    case "A":
                        risposta = b.compra();

                        if(b.getQuantita() == 0) { finiti = true;}

                        break;
                    case "Q":
                        risposta = 8; 
                        listaThread.remove(currentThread());
                        break;
                    default:
                        risposta = 9;    
                }
                send(risposta);

                if(finiti == true) { broadcast("7");}
                
            } while (true);
            
        } catch (Exception e) {
            System.out.println("\t\terrore nella comunicazione: " + e.getMessage());
            listaThread.remove(currentThread());
        }
    }
}
