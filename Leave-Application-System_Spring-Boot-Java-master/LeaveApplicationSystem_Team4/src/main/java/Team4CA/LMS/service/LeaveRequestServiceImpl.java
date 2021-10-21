package Team4CA.LMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team4CA.LMS.domain.LeaveRequest;
import Team4CA.LMS.domain.LeaveStatus;
import Team4CA.LMS.domain.LeaveType;
import Team4CA.LMS.repo.LeaveRequestRepo;
import Team4CA.LMS.repo.LeaveTypeRepo;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
	
	@Autowired
	LeaveRequestRepo lrRepo;

	@Override
	public ArrayList<LeaveRequest> findAll() {
		ArrayList<LeaveRequest> list = (ArrayList<LeaveRequest>) lrRepo.findAll();
		return list;
	}

	@Override
	public boolean applyLeave(LeaveRequest leavereq) {
		if(lrRepo.save(leavereq)!=null) return true; else return false;
	}

	@Override
	public boolean updateLeave(LeaveRequest leavereq) {
		if(lrRepo.save(leavereq)!=null) return true; else return false;
	}

	@Override
	public void cancelLeave(LeaveRequest leavereq) {
		lrRepo.delete(leavereq);
	}

	@Override
	public boolean saveLeave(LeaveRequest leavereq) {
		if(lrRepo.save(leavereq)!=null) return true; else return false;
	}

	@Override
	public LeaveRequest findLeaveById(Integer id) {
		return lrRepo.findById(id).get();
	}

	@Override
	public List<LeaveRequest> findLeaveByLeaveTypeId(Integer id) {
		return lrRepo.findLeaveByLeaveTypeId(id);
	}
	@Override
	public ArrayList<LeaveRequest> findByLeaveStatus(LeaveStatus ls) {
		ArrayList<LeaveRequest> pendingList = (ArrayList<LeaveRequest>)lrRepo.findByLeaveStatus(LeaveStatus.APPLIED);
			return pendingList;
	
	}

	@Override
	public boolean approvedLeaveByManager(Integer id) {
		LeaveRequest leaveRequest = lrRepo.findById(id).get();
		leaveRequest.setLeaveStatus(LeaveStatus.APPROVED);
		if (lrRepo.save(leaveRequest) != null)
			return true;
		else
			return false;
	}
	@Autowired LeaveTypeRepo leaveTypeRepo;
	
	@Override
	public boolean rejectLeaveByManager(LeaveRequest l) {
		LeaveRequest leaveRequest1 = lrRepo.findById(l.getId()).get();
		leaveRequest1.setLeaveStatus(LeaveStatus.REJECTED);
		leaveRequest1.setComment(l.getComment());
		
		// new added
		int id = leaveRequest1.getLeaveType().getId();
		LeaveType lt = leaveTypeRepo.findById(id).get();
		lt.setLeaveDayLeft(lt.getLeaveDayLeft() + leaveRequest1.getDuration());
		leaveTypeRepo.save(lt);
		leaveRequest1.setLeaveType(lt);
		
		if ( lrRepo.save(leaveRequest1) != null)
			return true;
		else
			return false;
	}

	@Override
	public void addComment(String s, Integer id) {
		LeaveRequest leaveRequest1 = lrRepo.findById(id).get();
		leaveRequest1.setComment(s);
		
	}

	@Override
	public List<LeaveRequest> findLeaveRequestByEmployeeId(Integer id) {
		return lrRepo.findLeaveRequestByEmployeeId(id);
	}
	
	
	
}
