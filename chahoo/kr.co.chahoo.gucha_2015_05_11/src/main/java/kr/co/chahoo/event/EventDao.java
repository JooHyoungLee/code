package kr.co.chahoo.event;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import kr.co.chahoo.common.model.ConditionSet;
import kr.co.chahoo.event.model.EventDto;

public class EventDao extends SqlSessionDaoSupport{
	
	/**
	 * 총 이벤트 
	 * @return
	 */
	public int getAllCount() {
		int count = getSqlSession().selectOne("event.getAllCount");
		return count;
	}
	
	/**
	 * 이벤트 목록
	 * @param conditionSet	조건 데이터
	 * @return
	 */
	public List<EventDto> getEventList(ConditionSet conditionSet) {
		List<EventDto> list = getSqlSession().selectList("event.getEventList", conditionSet);
		return list;
	}
	/**
	 * 기간별 이벤트 목록
	 * @param conditionSet	조건 데이터
	 * @return
	 */
	public List<EventDto> getEventRagneList(ConditionSet conditionSet) {
		List<EventDto> list = getSqlSession().selectList("event.getEventRagneList", conditionSet);
		return list;
	}
	
	/**
	 * 이벤트 상세정보
	 * @param event_id	이벤트 아이디
	 * @return
	 */
	public EventDto getEvent(int event_id) {
		EventDto eventDto = getSqlSession().selectOne("event.getEvent", event_id);
		return eventDto;
	}
	
	/**
	 * 이벤트 생성
	 * @param eventDto	이벤트 정보
	 * @return
	 */
	public int createEvent(EventDto eventDto) {
		int ret = getSqlSession().insert("event.createEvent", eventDto);
		return ret;
	}
	
	/**
	 * 이벤트 수정
	 * @param eventDto	이벤트 정보
	 * @return
	 */
	public int updateEvent(EventDto eventDto) {
		int ret = getSqlSession().update("event.updateEvent", eventDto);
		return ret;
	}
	
	/**
	 * 이벤트 삭제
	 * @param event_id	이벤트 아이디
	 * @return
	 */
	public int deleteEvent(int event_id) {
		int ret = getSqlSession().delete("event.deleteEvent", event_id);
		return ret;
	}
	
	public int getLastEventID() {
		int ret = getSqlSession().selectOne("event.getLastEventId");
		return ret;
	}
}
