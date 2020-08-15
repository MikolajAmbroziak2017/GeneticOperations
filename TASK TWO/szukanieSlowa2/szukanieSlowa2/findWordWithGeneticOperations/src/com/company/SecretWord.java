package com.company;

import java.util.Random;

public class SecretWord implements GeneticUtility{
    private TableUtility utility;
    private String word;
    public char[] tableChar;

    public SecretWord(String word) {
        this.word = word;
        this.utility=new TableUtility();
        this.tableChar=utility.parseStringToChar(word);
    }
    //algortym fitness zlicza ilosc pasujacych liter w tablicy między szukanym a poszkującym slowem
    @Override
    public int fitnes(char[] fitnessWord) {
        int licznik=0;
        for(int i=0;i<fitnessWord.length;i++){
            if (fitnessWord[i]==tableChar[i])
                licznik++;
        }
        return licznik;
    }
    //Mutuje randomowo wybiera wartosc tablicy o okreslonym indexie
    @Override
    public char[] mutation(char[] chromosom1) {
        char[] template=utility.copyTable(chromosom1);
        int fit=fitnes(template);
        Random p=new Random();
        //mutacja wykonuje się tylko na literach które nie pasują w innym przypadku losuje drugi raz
        do
            {
            chromosom1=utility.copyTable(template);
            chromosom1[p.nextInt(chromosom1.length)]=utility.randomCharacter();
            }
        while(fitnes(chromosom1)<fit);

        return chromosom1;
    }
    // generowanie losowego ciągu znaków o długości jak poszukiwane słowo
    public char[] generateParent(){
        char [] parent=new char[tableChar.length];
        for (int i=0;i<tableChar.length;i++) {
            parent[i]=utility.randomCharacter();
        }
        return parent;
    }

    public int getWordLength() {
        return tableChar.length;
    }
}
