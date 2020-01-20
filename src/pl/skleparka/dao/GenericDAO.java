package pl.skleparka.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T, PK extends Serializable>{
    void create(T newObject);
    T read(PK primaryKey);
    void update(T updateObject);
    void delete(PK key);
    List<T> getAll();
}
