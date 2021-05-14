package com.sms.dao;

import com.sms.dto.CourseDto;
import com.sms.dto.ScoreDto;
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

	List<CourseDto> getMyScoreList(Pagination<CourseDto> page, String id, String result);

	int getTotalItemsCount(String id, String result);

	int getTotalItemsCountForExport(ScoreDto scoreDto);

	List<ScoreDto> getScoreListForExport(Pagination<ScoreDto> page,
                                         ScoreDto scoreDto);
}