package com.quseit.payapp.bean.response.terminal_info;

public class Item {
    private String id;

    private String label;

    private Merchant merchant;

    private Store store;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Merchant getMerchant() {
        return this.merchant;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Store getStore() {
        return this.store;
    }

}