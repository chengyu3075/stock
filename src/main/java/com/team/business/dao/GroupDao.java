package com.team.business.dao;

import com.team.business.model.Group;

public interface GroupDao {
	/**
	 * 团体信息
	 * @param group
	 * @return
	 * @throws Exception
	 */
	int saveGroup(Group group) throws Exception;
}
