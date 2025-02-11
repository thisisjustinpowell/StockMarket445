/**
 * Represents a lot of shares that are bought or sold at the same time.
 * Intentionally not hashable.
 */
public class Lot {
    // The Company whose shares this lot represents
    private Ticker ticker;
    // The number of shares in the lot
    private int quantity;
    // The price-per-share in this lot 
    // (either most recently-sold price or most-recently bought price)
    private double pricePerShare;

    public Lot(Ticker ticker, int quantity, double pricePerShare) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
    }

    public Ticker getTicker() {
        return this.ticker;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerShare() {
        return this.pricePerShare;
    }

    public String toString() {
        return quantity + " shares of " + ticker + " @ $" + pricePerShare + " each";
    }

}
