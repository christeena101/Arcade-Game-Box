package com.example.cs2340Project;

import java.io.IOException;
import java.util.Scanner;

public class BlackJackFullFunctionality {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        BlackJackDeck myDeck = new BlackJackDeck();
        BlackJackPlayer me = new BlackJackPlayer();
        BlackJackDealer dealer = new BlackJackDealer();
        myDeck.shuffle();
        String cont = "y";
        String hit = "y";
        while ("y".equals(cont)) {
            dealer.hit(myDeck);
            me.hit(myDeck);
            dealer.hit(myDeck);
            me.hit(myDeck);
            
            dealer.printShownCard();
            me.printHand();
            while (me.getHandSum() < 21 & "y".equals(hit)) {
                dealer.printShownCard();
                System.out.println();
                me.printHand();
                System.out.println();
                System.out.println("Hit?");
                hit = scan.nextLine();
                if ("y".equals(hit)) {
                    me.hit(myDeck);
                }
            }
            dealer.dealerHit(myDeck);
            dealer.printHand();
            me.printHand();
            int won = dealer.playerWin(me);
            if (won == 2) {
                System.out.println("You won!");
            } else if (won == 1) {
                System.out.println("Tie");
            } else {
                System.out.println("Loss :(");
            }
            System.out.println();
            System.out.println("Play again?");
            cont = scan.nextLine();
            if (cont.equals("y")) {
                me.clearHand(myDeck);
                dealer.clearHand(myDeck);
                hit = "y";
            }
        }
    }
}
