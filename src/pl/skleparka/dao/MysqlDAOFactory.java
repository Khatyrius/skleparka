package pl.skleparka.dao;

public class MysqlDAOFactory extends DAOFactory {

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}
	
	@Override
	public BillingInfoDAO getBillingInfoDAO() {
		return new BillingInfoDAOImpl();
	}

	@Override
	public ProductDAO getProductDAO() {
		return new ProductDAOImpl();
	}

	@Override
	public CartDAO getCartDAO() {
		return new CartDAOImpl();
	}

	@Override
	public ReviewDAO getReviewDAO() {
		return new ReviewDAOImpl();
	}

	@Override
	public HistoryDAO getHistoryDAO() {
		return new HistoryDAOImpl();
	}

	@Override
	public OrdersDAO getOrdersDAO() {
		return new OrdersDAOImpl();
	}

	@Override
	public ShipmentDAO getShipmentDAO() {
		return new ShipmentDAOImpl();
	}

	@Override
	public PaymentDAO getPaymentDAO() {
		return new PaymentDAOImpl();
	}
}