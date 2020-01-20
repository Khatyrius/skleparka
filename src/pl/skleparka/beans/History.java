package pl.skleparka.beans;

public class History {
private int historyId;
private int userId;
private int orderId;

public History() {
	
}

public History(int historyId, int userId, int orderId) {
	super();
	this.historyId = historyId;
	this.userId = userId;
	this.orderId = orderId;
}

public int getHistoryId() {
	return historyId;
}

public void setHistoryId(int historyId) {
	this.historyId = historyId;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public int getOrderId() {
	return orderId;
}

public void setOrderId(int orderId) {
	this.orderId = orderId;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + historyId;
	result = prime * result + orderId;
	result = prime * result + userId;
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
	History other = (History) obj;
	if (historyId != other.historyId)
		return false;
	if (orderId != other.orderId)
		return false;
	if (userId != other.userId)
		return false;
	return true;
}

@Override
public String toString() {
	return "History [historyId=" + historyId + ", userId=" + userId + ", orderId=" + orderId + "]";
}



}
