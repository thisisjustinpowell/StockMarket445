import java.util.List;

public interface PortfolioInterface {

    // Handles a single trade. 
    // If the trade is of type BUY, then the lot of shares should be recorded in the portfolio's `positions` HashMap.
    // If the trade is of type SELL, then the lots in the positions HashMap for the trade's ticker should be updated.
    // Additionally, the profit (or loss) from selling this lot should be updated in the `profits` HashMap.
    public void handleTrade(Trade trade);


    // Handles a List of Trades. Trades should be processed from first to last.
    // If a user calls handleTrade on each Trad individually, the result should
    // be the same as calling handleTrades() on a list of the same Trades.
    public void handleTrades(List<Trade> trades);

    // Returns the current profit (or loss) that the portfolio has earned on a given Ticker.
    // This ignores any shares that are currently held, so the only time profits are affected is when shares are sold.
    public double getProfit(Ticker ticker);

    // Returns the total profit (or loss) that the portfolio has earned on all Tickers.
    public double getTotalProfit();

    // Returns the number of shares of a given Ticker that the portfolio currently holds.
    public int getSharesHeld(Ticker ticker);
}
