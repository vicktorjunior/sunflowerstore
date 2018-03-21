package br.com.sunflowerstore.model.wrapper;

import br.com.sunflowerstore.model.ItemSell;

import java.util.ArrayList;
import java.util.List;

public class ItemSellWrapper {

    private List<ItemSell> itemSellList = new ArrayList<ItemSell>();

    public List<ItemSell> getItemSellList() {
        return itemSellList;
    }

    public void setItemSellList(List<ItemSell> itemSellList) {
        this.itemSellList = itemSellList;
    }
}
