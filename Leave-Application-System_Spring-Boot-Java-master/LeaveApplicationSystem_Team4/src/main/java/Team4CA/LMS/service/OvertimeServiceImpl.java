package Team4CA.LMS.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Team4CA.LMS.domain.EmployeeTotalOvertime;
import Team4CA.LMS.domain.LeaveType;
import Team4CA.LMS.domain.Overtime;
import Team4CA.LMS.domain.OvertimeStatus;
import Team4CA.LMS.repo.EmployeeTotalOvertimeRepo;
import Team4CA.LMS.repo.LeaveTypeRepo;
import Team4CA.LMS.repo.OvertimeRepo;

@Service
public class OvertimeServiceImpl implements OvertimeService {

	@Autowired
	OvertimeRepo ovRepo;
	

	@Autowired
	EmployeeTotalOvertimeRepo overtimeTotalTimeRepo;
	
	@Autowired
	LeaveTypeRepo leaveTypeRepo;

	@Override
	public ArrayList<Overtime> findAll() {
		ArrayList<Overtime> list = (ArrayList<Overtime>) ovRepo.findAll();
		return list;
	}

	@Override
	public boolean applyOver(Overtime over) {
		if(ovRepo.save(over)!=null) return true; else return false;
	}

	@Override
	public boolean updateOver(Overtime over) {
		if(ovRepo.save(over)!=null) return true; else return false;
	}

	@Override
	public void cancelOver(Overtime over) {
		ovRepo.delete(over);
		
	}

	@Override
	public boolean saveOver(Overtime over) {
		if(ovRepo.save(over)!=null) return true; else return false;
	}

	@Override
	public Overtime findOverById(Integer id) {
		return ovRepo.findById(id).get();
	}
	@Override
	public boolean approvedOvertimeByManager(Integer id) {

		Overtime overtime = ovRepo.findById(id).get();
		overtime.setOvertimeStatus(OvertimeStatus.APPROVED);
		
		if (ovRepo.save(overtime) != null) {
			
			if(overtimeTotalTimeRepo.findByEmployeeId(overtime.getEmployee().getId()) != null) {
				EmployeeTotalOvertime o = overtimeTotalTimeRepo.findByEmployeeId(overtime.getEmployee().getId());
				double time = o.getTotal() + overtime.getDuration();
				o.setTotal(time);
				overtimeTotalTimeRepo.save(o);
			}else {
				EmployeeTotalOvertime eto = new EmployeeTotalOvertime();
				eto.setEmployeeId(overtime.getEmployee().getId());
				eto.setTotal(overtime.getDuration());
				overtimeTotalTimeRepo.save(eto);
			}
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean rejectedOvertimeByManager(Integer id) {
		Overtime overtime = ovRepo.findById(id).get();
		overtime.setOvertimeStatus(OvertimeStatus.REJECTED);
		if (ovRepo.save(overtime) != null)
			return true;
		else
			return false;
	}

	

	@Override
	public List<Overtime> findOvertimeByOvertimeStatus(OvertimeStatus os) {
		List<Overtime> overtimeList = ovRepo.findOvertimeByOvertimeStatus(os);
		return overtimeList;
	}
	

	@Override
	public boolean calculateForCompensationLeave(Integer id) {
		Overtime overtime = ovRepo.findById(id).get();
		
		EmployeeTotalOvertime eto = overtimeTotalTimeRepo.findByEmployeeId(overtime.getEmployee().getId());
		double ot = eto.getTotal();
		if(ot > 8 || ot == 8) {
			double day = (int) Math.abs(ot/8);
			eto.setTotal(ot%8);
			addCompensationLeave(overtime,day);
			
			
		}else if(ot > 4 || ot == 4) {
			eto.setTotal(ot - 4);
			addCompensationLeave(overtime,0.5);
			
		}
		return true;
	}
	
	public void addCompensationLeave(Overtime overtime,double day) {
		//check employee for Compensation Leave
		boolean flag = true;
		List<LeaveType> leaveTypeList = leaveTypeRepo.findAllLeaveTypeByEmployeeId(overtime.getEmployee().getId());
		
		for (LeaveType leaveType : leaveTypeList) {
			
			String s1 = "CompensationLeave";
			
			if(leaveType.getLeaveTypeName().equals(s1)) {
				leaveType.setLeaveDay(leaveType.getLeaveDay() + day);
				leaveType.setLeaveDayLeft(leaveType.getLeaveDayLeft() + day);
				leaveTypeRepo.save(leaveType);
				flag = false;
			}
		}
		
		//if employee didn't have Compensation leave yet , create
		if(flag) {

			LeaveType lt1 = new LeaveType("CompensationLeave",day,day,overtime.getEmployee());
			leaveTypeRepo.save(lt1);
		}
	}

	@Override
	public List<Overtime> findOvertimeByEmployeeId(Integer id) {
		return ovRepo.findOvertimeByEmployeeId(id);
	}
	
	
}
