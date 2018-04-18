package kr.co.chahoo.service;

import java.util.List;

import kr.co.chahoo.common.model.ConditionSet;
import kr.co.chahoo.event.model.EventDto;

public interface EventService {
	/**
	 * 이벤트 총 수
	 * @return
	 */
	public int getAllCount();
	
	/**
	 * 이베트 목록
	 * @param conditionSet	조건 데이터
	 * @return
	 */
	public List<EventDto> getEventList(ConditionSet conditionSet);
	
	/**
	 * 이벤트 기간 목록
	 * @param conditionSet	조건 데이터
	 * @return
	 */
	public List<EventDto> getEventRagneList(ConditionSet conditionSet);
	
	/**
	 * 이벤트 상세정보
	 * @param event_id	이벤트 아이디
	 * @return
	 */
	public EventDto getEvent(int event_id);
	
	/**
	 * 이벤트 생성
	 * @param eventDto	이벤트 정보
	 * @return
	 */
	public int createEvent(EventDto eventDto);
	
	/**
	 * 이벤트 수정
	 * @param eventDto	이벤트 정보
	 * @return
	 */
	public int updateEvent(EventDto eventDto);
	
	/**
	 * 이벤트 삭제 
	 * @param event_id	이벤트 아이디
	 * @return
	 */
	public int deleteEvent(int event_id);
	
	/**
	 * 이벤트 목록 Json 변환
	 * @param list	이벤트 목록
	 * @return
	 */
	public String eventJsonList(List<EventDto> list);
	
	/**
	 * 마지막 저장된 이벤트 아이디 반환
	 * @return
	 */
	public int getLastEventID();
}
