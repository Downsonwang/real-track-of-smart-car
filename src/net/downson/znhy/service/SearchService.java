package net.downson.znhy.service;

import net.downson.znhy.bean.GPS;
import net.downson.znhy.dao.SearchDao;

import java.sql.SQLException;
import java.util.List;

public class SearchService {

   public List<GPS> findCategory() throws SQLException {
       SearchDao SearchDao = new SearchDao();
      List<GPS> sr = SearchDao.querySearch();
      return sr;
    }
    }
