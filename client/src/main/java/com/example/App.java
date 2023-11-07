package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            // creao un socket / collegamento al server
            Socket socket = new Socket("localhost", 3000);
            // creo i buffer e stream per comunicare
            DataOutputStream outServer = new DataOutputStream(socket.getOutputStream());
            BufferedReader inServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Ascolto a = new Ascolto(inServer);
            //a.run();
            String risposta = "";
            String richiesta = "";

            do {
                // leggo dalla tastiera
                System.out.println("scegli che tipo di richiesta fare..");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                
                richiesta = in.readLine();
                // invio il numero al server
                outServer.writeBytes(richiesta + "\n");
                // aspetto la risposta dal server
                risposta = inServer.readLine();
                switch (risposta) {
                case "6":
                    System.out.println("il biglietto e' stato acquistato");
                    break;
                case "7":
                    System.out.println("i biglietti sono esauriti");
                    break;
                case "8": 
                    System.out.println("connessione chiusa");
                    break;
                case "9":
                    System.out.println("insrimento sbagliato");
                    break;
                default:
                    System.out.println("la disponibilita' e' di " + risposta + " biglietti");
                    break;
                }
            } while (!richiesta.equals("Q"));

            socket.close();

        } catch (Exception e) {
            System.out.println("Errore");
        }
    }
}
