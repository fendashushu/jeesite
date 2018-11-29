/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.statistics.DayStatistics;
import com.thinkgem.jeesite.modules.core.dao.statistics.DayStatisticsDao;

/**
 * 统计Service
 * @author li
 * @version 2018-11-14
 */
@Service
@Transactional(readOnly = true)
public class DayStatisticsService extends CrudService<DayStatisticsDao, DayStatistics> {
    @Autowired
    private DayStatisticsDao statisticsDao;

	public DayStatistics get(String id) {
		return super.get(id);
	}

	public DayStatistics getByDate(String date) {
		return statisticsDao.getByDate(date);
	}

	public DayStatistics getLastest() {
		return statisticsDao.getLastest(DateUtils.getDate());
	}

	public List<DayStatistics> findList(DayStatistics dayStatistics) {
		return super.findList(dayStatistics);
	}
	
	public Page<DayStatistics> findPage(Page<DayStatistics> page, DayStatistics dayStatistics) {
		return super.findPage(page, dayStatistics);
	}

    public Map<String,Object> getNewDataDay(){
	    return statisticsDao.getNewDataDay(DateUtils.getDate());
    }

    public Map<String,Object> getNewDataMember(){
	    return statisticsDao.getNewDataMember();
    }

    public Map<String,Object> getNewDataOrder(){
	    return statisticsDao.getNewDataOrder();
    }

    public Map<String,Object> getTransfer(){
	    return statisticsDao.getTransfer(UserUtils.getUser().getLoginName());
    }

    public Map<String,Object> getNewDataMonth(){
        Map map = new HashMap();
        String today = DateUtils.getDate();
        String frist = today.substring(0,8)+"01";
        String last = today.substring(0,8)+"31";
        map.put("last",last);
        map.put("frist",frist);
	    return statisticsDao.getNewDataMonth(map);
    }


    public Map<String,Object> getNewDataYear(){
        Map map = new HashMap();
        String today = DateUtils.getDate();
        String frist = today.substring(0,5)+"01-01";
        String last = today.substring(0,5)+"12-31";
        map.put("last",last);
        map.put("frist",frist);
	    return statisticsDao.getNewDataYear(map);
    }

	@Transactional(readOnly = false)
	public void save(DayStatistics dayStatistics) {
		super.save(dayStatistics);
	}
	
	@Transactional(readOnly = false)
	public void delete(DayStatistics dayStatistics) {
		super.delete(dayStatistics);
	}
	
}