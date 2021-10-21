package Team4CA.LMS.service;

import java.util.ArrayList;
import java.util.List;

import Team4CA.LMS.domain.LeaveRequest;
import Team4CA.LMS.domain.LeaveStatus;

public interface LeaveRequestService {
	
	public ArrayList<LeaveRequest> findAll();
	public boolean applyLeave(LeaveRequest leavereq);
	public boolean updateLeave(LeaveRequest leavereq);
	public void cancelLeave(LeaveRequest leavereq);
	public boolean saveLeave(LeaveRequest leavereq);
	public LeaveRequest findLeaveById(Integer id);
	public List<LeaveRequest> findLeaveByLeaveTypeId(Integer id);
	
	public ArrayList<LeaveRequest> findByLeaveStatus(LeaveStatus ls);
	public boolean approvedLeaveByManager(Integer id);
	public boolean rejectLeaveByManager(LeaveRequest lq);
	public void addComment(String s, Integer id);
	public List<LeaveRequest> findLeaveRequestByEmployeeId(Integer id);
	

	
}
