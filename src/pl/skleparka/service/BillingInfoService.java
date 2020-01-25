package pl.skleparka.service;

import java.sql.Date;
import java.util.List;

import pl.skleparka.beans.BillingInfo;
import pl.skleparka.dao.BillingInfoDAO;
import pl.skleparka.dao.DAOFactory;

public class BillingInfoService {
	private static BillingInfoService instance;
    
    private BillingInfoService(){}
    
    public static BillingInfoService getInstance(){
        if(instance == null){
            instance = new BillingInfoService();
        }
        return instance;
    }
	
	public void addBillingInfo(int userId, String cardNumber, Date expirationDate, int securityCode, String billingAddress) {
		BillingInfo billingInfo = new BillingInfo();
		billingInfo.setUserId(userId);
		billingInfo.setCardNumber(cardNumber);
		billingInfo.setExpirationDate(expirationDate);
		billingInfo.setSecurityCode(securityCode);
		billingInfo.setBillingAddress(billingAddress);
		
		
		GetDao().create(billingInfo);
	}
	
	public BillingInfo getBillingInfoById(int billingInfoId) {
		return GetDao().read(billingInfoId);
	}
	
	public void updateBillingInfo(int billingInfoId, String cardNumber, Date expirationDate, int securityCode, String billingAddress) {
		BillingInfo billingInfo = GetDao().read(billingInfoId);
		
		if(!cardNumber.isEmpty() && cardNumber.equals(billingInfo.getCardNumber())) {
			billingInfo.setCardNumber(cardNumber);
		}
		
		if((expirationDate != null) && !expirationDate.equals(billingInfo.getExpirationDate())){
			billingInfo.setExpirationDate(expirationDate);
		}
		
		if(securityCode != 0 && securityCode != billingInfo.getSecurityCode()) {
			billingInfo.setSecurityCode(securityCode);
		}
		
		if(!billingAddress.isEmpty() && !billingAddress.equals(billingAddress)) {
			billingInfo.setBillingAddress(billingAddress);
		}
		
		GetDao().update(billingInfo);
		
	}
	
	public void deleteBillingInfo(int billingInfoId) {
		GetDao().delete(billingInfoId);
	}
	
	public List<BillingInfo> getAllBillingInfos(){
		return GetDao().getAll();
	}
	
	public BillingInfo getBillingInfoByUserId(int userId) {
		return GetDao().getByUserId(userId);
	}
	
	private BillingInfoDAO GetDao() {
		DAOFactory factory = DAOFactory.getDAOFactory();
		return factory.getBillingInfoDAO();
	}

}
