public class testing implements PortfolioInterface{



    public void handleTrade(Trade trade){
        else if(trade.getType()==Trade.TradeType.SELL){
            int tradeNumberOfShares=trade.getLot().getQuantity();
            int buyingLotNumberOfShares=positions.get(company).peek().getQuantity();
            if(tradeNumberOfShares==buyingLotNumberOfShares){
                double buyingPrice= positions.get(company).peek().getPricePerShare();
                double tradePrice=trade.getLot().getPricePerShare();

                positions.get(company).remove();
                double profit=(tradePrice-buyingPrice)*buyingLotNumberOfShares;
                profits.replace(company, profit);
            }
            if(tradeNumberOfShares<buyingLotNumberOfShares){
                double buyingPrice= positions.get(company).peek().getPricePerShare();
                double tradePrice=trade.getLot().getPricePerShare();

                double profit= (tradePrice-buyingPrice)*tradeNumberOfShares;
                profits.replace(company, profit);
                positions.get(company).peek().setQuantity(buyingLotNumberOfShares-tradeNumberOfShares);
            }
            if(tradeNumberOfShares>buyingLotNumberOfShares){
                double buyingPrice= positions.get(company).peek().getPricePerShare();
                double tradePrice=trade.getLot().getPricePerShare();

                positions.get(company).remove();
                double profit=(tradePrice-buyingPrice)*buyingLotNumberOfShares;
                profits.replace(company, profit);
                int differenceInShares= tradeNumberOfShares-buyingLotNumberOfShares;

                if(positions.get(company).peek().getQuantity()<differenceInShares){
                    double newBuyingPrice=positions.get(company).peek().getPricePerShare();
                    int newBuyingNumberOfShares=positions.get(company).peek().getQuantity();
                    profit=(tradePrice-newBuyingPrice)*differenceInShares;
                    profits.replace(company, profit);
                    positions.get(company).remove();
                }
                if(positions.get(company).peek().getQuantity()>differenceInShares){
                    double newBuyingPrice=positions.get(company).peek().getPricePerShare();
                    int newBuyingNumberOfShares=positions.get(company).peek().getQuantity();
                    positions.get(company).peek().setQuantity(newBuyingNumberOfShares-differenceInShares);
                    profit=(tradePrice-newBuyingPrice)*newBuyingNumberOfShares;
                    profits.replace(company, profit);
                }

        }
    }
    }
}