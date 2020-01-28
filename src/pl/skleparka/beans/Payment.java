package pl.skleparka.beans;

public class Payment {
	private int paymentId;
	private String paymentType;
	private int userId;
	private double amount;
	private int sellerId;
	private int billingId;
	private int orderId;
	private String status;
	
	public Payment() {}

	public Payment(int paymentId, String paymentType, int userId, double amount, int sellerId, int billingId,
			int orderId, String status) {
		super();
		this.paymentId = paymentId;
		this.paymentType = paymentType;
		this.userId = userId;
		this.amount = amount;
		this.sellerId = sellerId;
		this.billingId = billingId;
		this.orderId = orderId;
		this.status = status;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getBillingId() {
		return billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + billingId;
		result = prime * result + orderId;
		result = prime * result + paymentId;
		result = prime * result + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = prime * result + sellerId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Payment other = (Payment) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (billingId != other.billingId)
			return false;
		if (orderId != other.orderId)
			return false;
		if (paymentId != other.paymentId)
			return false;
		if (paymentType == null) {
			if (other.paymentType != null)
				return false;
		} else if (!paymentType.equals(other.paymentType))
			return false;
		if (sellerId != other.sellerId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentType=" + paymentType + ", userId=" + userId + ", amount="
				+ amount + ", sellerId=" + sellerId + ", billingId=" + billingId + ", orderId=" + orderId
				+ ", status=" + status + "]";
	}

	
	
}
