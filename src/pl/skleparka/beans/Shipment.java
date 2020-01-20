package pl.skleparka.beans;

public class Shipment {
	private int shipmentId;
	private int orderId;
	private int userId;
	private String trackingNumber;
	private String returnAddress;
	private String carrier;
	private float charge;
	private String status;
	
	public Shipment() {}

	public Shipment(int shipmentId, int orderId, int userId, String trackingNumber, String returnAddress,
			String carrier, float charge, String status) {
		super();
		this.shipmentId = shipmentId;
		this.orderId = orderId;
		this.userId = userId;
		this.trackingNumber = trackingNumber;
		this.returnAddress = returnAddress;
		this.carrier = carrier;
		this.charge = charge;
		this.status = status;
	}

	public int getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(int shipmentId) {
		this.shipmentId = shipmentId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public String getReturnAddress() {
		return returnAddress;
	}

	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public float getCharge() {
		return charge;
	}

	public void setCharge(float charge) {
		this.charge = charge;
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
		result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
		result = prime * result + Float.floatToIntBits(charge);
		result = prime * result + orderId;
		result = prime * result + ((returnAddress == null) ? 0 : returnAddress.hashCode());
		result = prime * result + shipmentId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((trackingNumber == null) ? 0 : trackingNumber.hashCode());
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
		Shipment other = (Shipment) obj;
		if (carrier == null) {
			if (other.carrier != null)
				return false;
		} else if (!carrier.equals(other.carrier))
			return false;
		if (Float.floatToIntBits(charge) != Float.floatToIntBits(other.charge))
			return false;
		if (orderId != other.orderId)
			return false;
		if (returnAddress == null) {
			if (other.returnAddress != null)
				return false;
		} else if (!returnAddress.equals(other.returnAddress))
			return false;
		if (shipmentId != other.shipmentId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (trackingNumber == null) {
			if (other.trackingNumber != null)
				return false;
		} else if (!trackingNumber.equals(other.trackingNumber))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shipment [shipmentId=" + shipmentId + ", orderId=" + orderId + ", userId=" + userId
				+ ", trackingNumber=" + trackingNumber + ", returnAddress=" + returnAddress + ", carrier=" + carrier
				+ ", charge=" + charge + ", status=" + status + "]";
	}

	
	
	
}
