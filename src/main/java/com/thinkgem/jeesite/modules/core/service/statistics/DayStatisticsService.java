/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.statistics;

import java.util.List;

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

	public DayStatistics get(String id) {
		return super.get(id);
	}
	
	public List<DayStatistics> findList(DayStatistics dayStatistics) {
		return super.findList(dayStatistics);
	}
	
	public Page<DayStatistics> findPage(Page<DayStatistics> page, DayStatistics dayStatistics) {
		return super.findPage(page, dayStatistics);
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