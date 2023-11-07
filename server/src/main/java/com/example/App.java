package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            // creao un socket
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("CREO IL SERVER");
            // istanzio biglietti
            Biglietti b = new Biglietti();
            // creo l'array di tutti i thread
            ArrayList <ThreadServer> lista = new ArrayList();
            while (true) {
                // apro il socket di ascolto
                Socket client = serverSocket.accept();
                // passo tutto alla classe ThreadServer
                ThreadServer th = new ThreadServer(client, b, lista);
                lista.add(th);
                System.out.println(lista.size());
                th.start();
            }
        } catch (Exception e) {
            System.out.println("\nErrore");
        }
        
    }
}
