package mhotel.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAOInterface<T> {
	T insert(T pValue) throws SQLException;// INSERT IN DATABASE
	T update(T pValue) throws SQLException;// UPDATE
	List<T> listAll() throws SQLException; // SELECT ALL 
	T loadById(Long pId) throws SQLException; // SELECT FOR GIVEN ID - NULL IF NOT FOUND
}
