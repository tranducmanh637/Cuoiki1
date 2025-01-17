package Do_an_ck1;

public class ComputerComponents {
private String itemID;
private String itemName;
private int amount;
private double unitPrice;

public ComputerComponents(String itemID, String itemName, int amount, double unitPrice) {
	super();
	this.itemID = itemID;
	this.itemName = itemName;
	this.amount = amount;
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

public int getAmount() {
	return amount;
}

public void setAmount(int amount) {
	this.amount = amount;
}

public double getUnitPrice() {
	return unitPrice;
}

public void setUnitPrice(double unitPrice) {
	this.unitPrice = unitPrice;
}

public double displayPrice() {
    return amount*unitPrice;
}

@Override
public String toString() {
	return "ComputerComponents [itemID=" + itemID + ", itemName=" + itemName + ", amount=" + amount + ", unitPrice="
			+ unitPrice + "]"+ displayPrice();
}


}