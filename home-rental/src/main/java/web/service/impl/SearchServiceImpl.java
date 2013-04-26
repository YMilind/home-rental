package web.service.impl;

import java.util.List;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.SearchDao;
import web.model.SearchResult;
import web.service.SearchService;

/**
 * @author Romain <ro.foncier@gmail.com>
 */

@Service("searchService")
@Transactional(readOnly=true)
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;
    
    @Override
    public List<SearchResult> searchForSpecificCountry(String country, LocalDateTime checkin, LocalDateTime checkout, Integer guests) {
        return searchDao.searchForSpecificCountry(country, checkin, checkout, guests);
    }
    
    @Override
    public List<SearchResult> searchInRadius25(Integer lat, Integer lng, LocalDateTime checkin, LocalDateTime checkout, Integer guests) {
        return searchDao.searchInRadius25(lat, lng, checkin, checkout, guests);
    }
}