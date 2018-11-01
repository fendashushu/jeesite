/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.core.service.setting;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.core.entity.setting.MemberSetting;
import com.thinkgem.jeesite.modules.core.dao.setting.MemberSettingDao;

/**
 * 会员设置Service
 * @author 李延明
 * @version 2018-11-01
 */
@Service
@Transactional(readOnly = true)
public class MemberSettingService extends CrudService<MemberSettingDao, MemberSetting> {

	public MemberSetting get(String id) {
		return super.get(id);
	}
	
	public List<MemberSetting> findList(MemberSetting memberSetting) {
		return super.findList(memberSetting);
	}
	
	public Page<MemberSetting> findPage(Page<MemberSetting> page, MemberSetting memberSetting) {
		return super.findPage(page, memberSetting);
	}
	
	@Transactional(readOnly = false)
	public void save(MemberSetting memberSetting) {
		super.save(memberSetting);
	}
	
	@Transactional(readOnly = false)
	public void delete(MemberSetting memberSetting) {
		super.delete(memberSetting);
	}
	
}