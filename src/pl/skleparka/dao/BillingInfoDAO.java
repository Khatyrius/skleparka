package pl.skleparka.dao;

import pl.skleparka.beans.BillingInfo;

public interface BillingInfoDAO extends GenericDAO<BillingInfo, Integer>{
BillingInfo getByUserId(int userId);
}
