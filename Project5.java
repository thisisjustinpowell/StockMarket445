import java.util.*;
import java.io.*;

public class Project5 {

    private static Trade sell(Lot lot) {
        return new Trade(Trade.TradeType.SELL, lot);
    }

    private static Trade buy(Lot lot) {
        return new Trade(Trade.TradeType.BUY, lot);
    }

    private static Lot makeLotOf5(Ticker ticker, double price) {
        return new Lot(ticker, 5, price);
    }

    private static Lot makeLotOf10(Ticker ticker, double price) {
        return new Lot(ticker, 10, price);
    }

    private static List<Trade> getTrades(String filename) throws FileNotFoundException {
        ArrayList<Trade> result = new ArrayList<Trade>(200);
        Scanner trades = new Scanner(new File(filename));
        while (trades.hasNext()) {
            String[] strTrade = trades.nextLine().split(",");
            Trade.TradeType type = strTrade[0].equals("BUY") ? Trade.TradeType.BUY : Trade.TradeType.SELL;
            Lot lot = new Lot(new Ticker(strTrade[1]), Integer.parseInt(strTrade[2]), Double.parseDouble(strTrade[3]));
            Trade trade = new Trade(type, lot);
            result.add(trade);
        }
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Portfolio p = new Portfolio();
        
        if (args.length == 0) {
            Ticker stockA = new Ticker("AAA");
            p.handleTrade(buy(makeLotOf10(stockA, 100.0)));
            System.out.println("I own " + p.getSharesHeld(stockA) + " shares of AAA");
            p.handleTrade(sell(makeLotOf10(stockA, 115.00)));
            System.out.println("I own " + p.getSharesHeld(stockA) + " shares of AAA");
            System.out.println("Profits: " + p.getProfit(stockA));
    
            Ticker stockB = new Ticker("BBB");
            p.handleTrade(buy(makeLotOf10(stockB, 50.0)));
            System.out.println("I own " + p.getSharesHeld(stockB) + " shares of BBB");
            p.handleTrade(sell(makeLotOf5(stockB, 55.00)));
            p.handleTrade(sell(makeLotOf5(stockB, 40.00)));
            System.out.println("I own " + p.getSharesHeld(stockB) + " shares of BBB");
            System.out.println("Profits from : " + p.getProfit(stockB));
            System.out.println("Total Profits: " + p.getTotalProfit());
        } else {
            List<Trade> trades = getTrades(args[0]);
            p.handleTrades(trades);
            for (String company : new String[]{"AAA", "BBB", "CCC", "DDD", "EEE", "FFF", "GGG"}) {
                System.out.println("Company " + company + ": ");
                System.out.println("\tProfit to-date:       " + p.getProfit(new Ticker(company)));
                System.out.println("\tShares still holding: " + p.getSharesHeld(new Ticker(company)));
            }
            System.out.println("Total profits from 200 Trades: " + p.getTotalProfit());
        }
    }
}
