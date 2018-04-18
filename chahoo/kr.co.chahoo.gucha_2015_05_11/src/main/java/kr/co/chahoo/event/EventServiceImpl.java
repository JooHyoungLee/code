package kr.co.chahoo.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.chahoo.common.FormatUtils;
import kr.co.chahoo.common.model.ConditionSet;
import kr.co.chahoo.event.model.EventDto;
import kr.co.chahoo.service.EventService;

@Service
public class EventServiceImpl implements EventService{

	@Autowired private EventDao eventDao;
	
	@Override
	public int getAllCount() {
		int count = eventDao.getAllCount();
		return count;
	}

	@Override
	public List<EventDto> getEventList(ConditionSet conditionSet) {
		List<EventDto> list = eventDao.getEventList(conditionSet);
		return list;
	}

	@Override
	public List<EventDto> getEventRagneList(ConditionSet conditionSet) {
		List<EventDto> list = eventDao.getEventRagneList(conditionSet);
		return list;
	}

	@Override
	public EventDto getEvent(int event_id) {
		EventDto eventDto = eventDao.getEvent(event_id);
		return eventDto;
	}

	@Override
	public int createEvent(EventDto eventDto) {
		int ret = 0;
		try {
			ret = eventDao.createEvent(eventDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int updateEvent(EventDto eventDto) {
		int ret = 0;
		try {
			ret = eventDao.updateEvent(eventDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public int deleteEvent(int event_id) {
		int ret = 0;
		try {
			ret = eventDao.deleteEvent(event_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public String eventJsonList(List<EventDto> list) {
		/*
			{
	            title: 'Event1',
	            start: '2011-04-04'
	        },
		 */
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for(int i=0; i<list.size(); i++)
		{
			boolean allday = false;
			if(list.get(i).getAll_day() == 1)
			{
				allday = true;
			}
			else
			{
				allday = false;
			}
			sb.append("{");
			sb.append("\"id\":\"").append(list.get(i).getEvent_id()).append("\",");
			sb.append("\"title\":\"").append(list.get(i).getTitle()).append("\",");
			sb.append("\"start\":\"").append(FormatUtils.timestampToString_YYYY_MM_DD_T_HH_MM(list.get(i).getStart())).append("\",");
			sb.append("\"end\":\"").append(FormatUtils.timestampToString_YYYY_MM_DD_T_HH_MM(list.get(i).getEnd())).append("\",");
			sb.append("\"allDay\": ").append(allday).append(",");
			sb.append("\"description\":\"").append(list.get(i).getDescription()).append("\",");  
			sb.append("\"memberId\":\"").append(list.get(i).getMember_id()).append("\"");  
			sb.append("}");
			
			if(i != list.size()-1)
			{
				sb.append(",");
			}
		}
		sb.append("]");
		
		return sb.toString();
	}

	@Override
	public int getLastEventID() {
		int ret = eventDao.getLastEventID();
		return ret;
	}

}
