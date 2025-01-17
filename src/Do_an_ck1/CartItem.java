package Do_an_ck1;

public class CartItem {
    private String itemID;
    private String itemName;
    private int quantity;
    private double unitPrice;

    public CartItem(String itemID, String itemName, int quantity, double unitPrice) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return this.quantity * this.unitPrice;
    }

    @Override
    public String toString() {
        return "CartItem [itemID=" + itemID + ", itemName=" + itemName + ", quantity=" + quantity + ", unitPrice=" + unitPrice + "]";
    }

}

