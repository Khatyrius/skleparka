package pl.skleparka.beans;

public class Product {
private int productId;
private String itemName;
private int quantity;
private String type;
private double price;
private String description;
private String imageUrl;
private int sellerId;

public Product(){}

public Product(int productId, String itemName, int quantity, String type, double price, String description,
		String imageUrl, int sellerId) {
	super();
	this.productId = productId;
	this.itemName = itemName;
	this.quantity = quantity;
	this.type = type;
	this.price = price;
	this.description = description;
	this.imageUrl = imageUrl;
	this.sellerId = sellerId;
}

public int getProductId() {
	return productId;
}

public void setProductId(int productId) {
	this.productId = productId;
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

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getImageUrl() {
	return imageUrl;
}

public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}

public int getSellerId() {
	return sellerId;
}

public void setSellerId(int sellerId) {
	this.sellerId = sellerId;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((description == null) ? 0 : description.hashCode());
	result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
	result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
	long temp;
	temp = Double.doubleToLongBits(price);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + productId;
	result = prime * result + quantity;
	result = prime * result + sellerId;
	result = prime * result + ((type == null) ? 0 : type.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Product other = (Product) obj;
	if (description == null) {
		if (other.description != null)
			return false;
	} else if (!description.equals(other.description))
		return false;
	if (imageUrl == null) {
		if (other.imageUrl != null)
			return false;
	} else if (!imageUrl.equals(other.imageUrl))
		return false;
	if (itemName == null) {
		if (other.itemName != null)
			return false;
	} else if (!itemName.equals(other.itemName))
		return false;
	if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
		return false;
	if (productId != other.productId)
		return false;
	if (quantity != other.quantity)
		return false;
	if (sellerId != other.sellerId)
		return false;
	if (type == null) {
		if (other.type != null)
			return false;
	} else if (!type.equals(other.type))
		return false;
	return true;
}

@Override
public String toString() {
	return "Product [productId=" + productId + ", itemName=" + itemName + ", quantity=" + quantity + ", type=" + type
			+ ", price=" + price + ", description=" + description + ", imageUrl=" + imageUrl + ", sellerId=" + sellerId
			+ "]";
}


}
