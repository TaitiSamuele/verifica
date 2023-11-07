package com.example;
public class Biglietti {
    private int quantita = 5;
    
    public Biglietti(){}

    public int getQuantita() {
        return quantita;
    }

    public int compra(){
        if (quantita > 0){
            quantita--;
            return 6;    
        }
        return 7;

    }
}
