package pl.skleparka.dao;

import java.util.List;

import pl.skleparka.beans.History;

public interface HistoryDAO extends GenericDAO<History, Integer>{
List<History> getHistoryOfUser(int userId);
}
