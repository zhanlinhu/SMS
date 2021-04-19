package com.sms.dao;

import com.sms.dto.ScoreVo;
import com.sms.entity.Course;
import com.sms.entity.Score;
import com.sms.utils.page.Pagination;

import java.util.List;

public interface ScoreDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Score record);

    int insertSelective(Score record);

    Score selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Score record);

    int updateByPrimaryKey(Score record);

	int delete(Score score);

	int update(List<Score> scoreList);

	List<Course> getCourseList(Pagination<Course> page, String id, String result);

	int getTotalItemsCount(String id, String result);

	int getTotalItemsCountForExport(ScoreVo scoreVo);

	List<ScoreVo> getScoreListForExport(Pagination<ScoreVo> page,
			ScoreVo scoreVo);
}