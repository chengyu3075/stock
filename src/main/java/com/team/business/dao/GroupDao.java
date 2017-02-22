package com.team.business.dao;

import com.team.business.model.TeamGroup;

public interface GroupDao {
	/**
	 * 团体信息
	 * @param group
	 * @return
	 * @throws Exception
	 */
	int saveGroup(TeamGroup group) throws Exception;
}
