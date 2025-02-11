import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class Portfolio implements PortfolioInterface {

    private HashMap<Ticker, Queue<Lot>> positions;
    private HashMap<Ticker, Double> profits;

    public Portfolio() {
        this.positions = new HashMap<Ticker, Queue<Lot>>();
        this.profits = new HashMap<Ticker, Double>();
    }

    public void handleTrade(Trade trade) {
        // TODO: implement this method. Details in PortfolioInterface.java.
        Ticker company=trade.getLot().getTicker();
        Lot lot= trade.getLot();
        Deque<Lot> newQ= new ArrayDeque<Lot>();
        if(positions.get(company)==null && trade.getType()==Trade.TradeType.BUY){
            newQ.add(trade.getLot());
            positions.put(trade.getLot().getTicker(), newQ);
            profits.put(company, 0.0);
        }
        else if(trade.getType()==Trade.TradeType.BUY && positions.get(company)!=null){
            Lot buyingLot=trade.getLot();
            positions.get(company).add(buyingLot);
        }
        else if(trade.getType()==Trade.TradeType.SELL){
            int sharesToSell=trade.getLot().getQuantity();
            double tradePrice = trade.getLot().getPricePerShare();
            double totalProfit=0;

            while(sharesToSell>0){
                Lot currentLot=positions.get(company).peek();

                if(currentLot==null){
                    System.out.println("You need to buy shares before you can sell shares!");
                }

                int availableShares=currentLot.getQuantity();
                double buyingPrice=currentLot.getPricePerShare();

                if(sharesToSell>=availableShares){
                    totalProfit+=(tradePrice-buyingPrice)*availableShares;
                    sharesToSell-=availableShares;
                    positions.get(company).remove();
                }
                else{
                    totalProfit+=(tradePrice-buyingPrice)*sharesToSell;
                    currentLot.setQuantity(availableShares-sharesToSell);
                    sharesToSell=0;
                }
            }
            profits.replace(company, profits.get(company)+totalProfit);

        }
    }


    public void handleTrades(List<Trade> trades) {
        // TODO: implement this method. Details in PortfolioInterface.java.
        for(Trade trade: trades){
            handleTrade(trade);
        }
    }

    public double getProfit(Ticker ticker) {
        return profits.get(ticker);
    }

    public double getTotalProfit() {
        double totalProfit=0;
        Iterator<Double> iterator= profits.values().iterator();
        while(iterator.hasNext()){
            double current= iterator.next();
            totalProfit+=current;
        }
        return totalProfit;
    }
    public int getSharesHeld(Ticker ticker) {
        // TODO: implement this method. Details in PortfolioInterface.java.
        int shares = 0;
        Iterator<Lot> iterator=positions.get(ticker).iterator();
        while(iterator.hasNext()){
            int current= iterator.next().getQuantity();
            shares+=current;
        }
        return shares;
    }
}
