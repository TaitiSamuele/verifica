package com.example;

import java.io.BufferedReader;
import java.io.IOException;

public class Ascolto extends Thread{
    BufferedReader inServer;

    public Ascolto(BufferedReader inServer){
        this.inServer = inServer; 
    }

    /*@Override
    public void run() {
        do{
            String risposta = "";
        try{
            
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
            
            }}  
        }while(!risposta.equals("Q"));
        }catch(Error e){
            System.out.println("ERRORE");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/
}
