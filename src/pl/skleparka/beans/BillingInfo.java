package pl.skleparka.beans;

import java.sql.Date;

	public class BillingInfo {
	private int billingInfoId;
	private int userId;
	private String cardNumber;
	private Date expirationDate;
	private int securityCode;
	private String billingAddress;
	
	public BillingInfo() { }
	
	

	public BillingInfo(int billingInfoId, int userId, String cardNumber, Date expirationDate, int securityCode,
			String billingAddress) {
		super();
		this.billingInfoId = billingInfoId;
		this.userId = userId;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
		this.billingAddress = billingAddress;
	}



	public int getBillingInfoId() {
		return billingInfoId;
	}

	public void setBillingInfoId(int billingInfoId) {
		this.billingInfoId = billingInfoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result + billingInfoId;
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + securityCode;
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
		BillingInfo other = (BillingInfo) obj;
		if (billingAddress == null) {
			if (other.billingAddress != null)
				return false;
		} else if (!billingAddress.equals(other.billingAddress))
			return false;
		if (billingInfoId != other.billingInfoId)
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (securityCode != other.securityCode)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "BillingInfo [billingInfoId=" + billingInfoId + ", userId=" + userId + ", cardNumber=" + cardNumber
				+ ", expirationDate=" + expirationDate + ", securityCode=" + securityCode + ", billingAddress="
				+ billingAddress + "]";
	}

	

	}
