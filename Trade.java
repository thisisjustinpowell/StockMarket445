/**
 * Represents a transaction (either buying or selling) of a lot of shares
 * for a specific company at a specific price per share.
 */
public class Trade {
    public static enum TradeType {
        BUY,
        SELL,
    };
    private TradeType type;
    private Lot lot;

    public Trade(TradeType type, Lot lot) {
        this.type = type;
        this.lot = lot;
    }

    public TradeType getType() {
        return this.type;
    }

    
    public Lot getLot() {
        return this.lot;
    }
}