package com.example.cs2340Project;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static org.junit.Assert.*;

public class BlackJackFunctionalityTests {

    @Test
    public void testDeckCreation() {
        BlackJackDeck myDeck = new BlackJackDeck();
        assertEquals(myDeck, new BlackJackDeck());
    }

    @Test
    public void testShuffle() {
        BlackJackDeck myDeck = new BlackJackDeck();
        myDeck.makeDeck();
        BlackJackDeck testDeck = myDeck;
        myDeck.shuffle();
        assertNotEquals(testDeck.getCard(), myDeck.getCard());
    }

    @Test
    public void testCardToString() {
        BlackJackCard card1 = new BlackJackCard(8, 'H');
        BlackJackCard card2 = new BlackJackCard(10, 'S', "Jack");
        BlackJackCard card3 = new BlackJackCard(8, 'H');

        String test = card1.toString();
        String test2 = card2.toString();
        String test3 = card3.toString();

        assertEquals("8 of Hearts", test);
        assertEquals("Jack of Spades", test2);
        assertEquals(test, test3);
        assertNotEquals(test, test2);
    }

    @Test
    public void testClearHand() {
        BlackJackDeck myDeck = new BlackJackDeck();
        BlackJackPlayer me = new BlackJackPlayer();
        BlackJackDealer dealer = new BlackJackDealer();

        for (int i = 0; i < 4; i++) {
            me.hit(myDeck);
            dealer.hit(myDeck);
        }

        me.clearHand(myDeck);
        dealer.clearHand(myDeck);

        assertEquals(me.getHand().size(), 0);
        assertEquals(dealer.getHand().size(), 0);
    }
    @Test
    public void testHitDealerHand() {
        BlackJackDeck myDeck = new BlackJackDeck();
        BlackJackDealer dealer = new BlackJackDealer();

        myDeck.makeDeck();
        dealer.hit(myDeck);
        ArrayList<BlackJackCard> test = dealer.getHand();

        dealer.dealerHit(myDeck);

        test = dealer.getHand();
        assertEquals(test.size(), 2);
    }

    @Test
    public void testFunctionality() throws IOException {
        Scanner scan = new Scanner(System.in);
        BlackJackDeck myDeck = new BlackJackDeck();
        BlackJackPlayer me = new BlackJackPlayer();
        BlackJackDealer dealer = new BlackJackDealer();
        int money = 500;
        int bet = 0;
        String cont = "y";
        String hit = "n";
        while ("y".equals(cont)) {
            bet = scan.nextInt();
            dealer.hit(myDeck);
            me.hit(myDeck);
            dealer.hit(myDeck);
            me.hit(myDeck);
            while (me.getHandSum() < 21 & hit.equals("y")) {
                Runtime.getRuntime().exec("cls");
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
            dealer.hit(myDeck);
            Runtime.getRuntime().exec("cls");
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
            Runtime.getRuntime().exec("cls");
        }
    }

    @Test
    public void testPlayerHandSum() {
        BlackJackDeck deck = new BlackJackDeck();
        deck.makeDeck();
        BlackJackPlayer player = new BlackJackPlayer();

        player.hit(deck);
        player.hit(deck);

        ArrayList<BlackJackCard> hand = player.getHand();

        hand.get(0).setValue(5);
        hand.get(1).setValue(10);

        assertEquals(15,player.getHandSum());
    }

    @Test
    public void testPlayerWin() {
        //player win
        BlackJackDeck deck = new BlackJackDeck();
        deck.makeDeck();

        BlackJackPlayer player = new BlackJackPlayer();
        BlackJackDealer dealer = new BlackJackDealer();

        player.hit(deck);
        dealer.hit(deck);
        player.hit(deck);
        dealer.hit(deck);

        ArrayList<BlackJackCard> playerHand = player.getHand();
        ArrayList<BlackJackCard> dealerHand = dealer.getHand();

        playerHand.get(0).setValue(10);
        playerHand.get(1).setValue(10);

        dealerHand.get(0).setValue(10);
        dealerHand.get(1).setValue(5);

        assertEquals(dealer.playerWin(player), 2);
    }

    @Test
    public void testGetHand() {
        BlackJackPlayer player = new BlackJackPlayer();
        ArrayList<BlackJackCard> test = new ArrayList<>();

        assertEquals(player.getHand(), test);

        BlackJackDeck deck = new BlackJackDeck();
        deck.makeDeck();

        player.hit(deck);
        assertEquals(player.getHand().size(), 1);
    }

    @Test
    public void testGetShownCard() {
        BlackJackDeck deck = new BlackJackDeck();
        deck.makeDeck();

        BlackJackDealer dealer = new BlackJackDealer();
        dealer.hit(deck);
        dealer.hit(deck);

        ArrayList<BlackJackCard> hand = dealer.getHand();
        assertEquals(dealer.getShownCard(),hand.get(0));
    }

    @Test
    public void testHit() {
        BlackJackDeck deck = new BlackJackDeck();
        deck.makeDeck();

        BlackJackPlayer player = new BlackJackPlayer();
        player.hit(deck);
        player.hit(deck);
        player.hit(deck);

        ArrayList<BlackJackCard> test = player.getHand();

        test.get(0).setValue(4);
        test.get(1).setValue(5);
        test.get(2).setValue(3);

        assertEquals(player.getHand().size(), 3);
        assertEquals(player.getHandSum(), 12);
    }

    @Test
    public void testPrintHand() {
        BlackJackDeck deck = new BlackJackDeck();
        deck.makeDeck();

        BlackJackPlayer player = new BlackJackPlayer();
        BlackJackDealer dealer = new BlackJackDealer();

        player.hit(deck);
        dealer.hit(deck);
        player.hit(deck);
        dealer.hit(deck);

        ArrayList<BlackJackCard> pHand = player.getHand();
        ArrayList<BlackJackCard> dHand = dealer.getHand();

        /* pHand and dHand represent the player's and dealer's hand, so turning them
        * to strings and comparing them will act like printing their respective hands like in
        * testCardToString */
        assertNotEquals(pHand.toString(), null);
        assertNotEquals(dHand.toString(), null);
        player.printHand();
        dealer.printHand();
    }
}
