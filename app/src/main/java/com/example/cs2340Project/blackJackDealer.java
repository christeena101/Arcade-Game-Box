package com.example.cs2340Project;

public class blackJackDealer extends blackJackPlayer {

    /**
     * Hits the dealer's hand based on casino rules.
     *
     * @param deck Deck to pull cards from.
     */
    public void dealerHit(blackJackDeck deck) {
        if (this.getHandSum() < 17) {
            this.hit(deck);
        }
    }

    /**
     * Player to check if a given player beat the dealer.
     * 2 - Player wins
     * 1 - Tie
     * 0 - Player lost
     *
     * @param player Player to check if they won.
     * @return Returns state of player win/loss
     */
    public int playerWin(blackJackPlayer player) {
        if ((player.getHandSum() > this.getHandSum() && player.getHandSum() < 22) | (player.getHandSum() < 22 && this.getHandSum() > 21)) {
            return 2;
        } else if (player.getHandSum() == this.getHandSum()) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Get the card shown (index 0).
     *
     * @return The index 0 card (card to be shown).
     */
    public blackJackCard getShownCard() {
        return this.getHand().get(0);
    }

    /**
     * Prints the dealers hand.
     */
    @Override
    public void printHand() {
        System.out.printf("Dealer hand:\n");
        for (blackJackCard card: this.getHand()) {
            System.out.printf("%s\n",card);
        }
    }

    /**
     * Shows player the single shown card.
     */
    public void printShownCard() {
        System.out.printf("Dealer card:\n%s\n",this.getShownCard());
    }
}
