package com.lara.db.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.lara.db.util.DbUtil;
import com.lara.entity.Employee;
public class EmployeeDAO
{
	public static int insertEmployee(Employee emp)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("insert into employee(id, first_name, ");
		sql.append("last_name, date_birth, gender_id, ");
		sql.append("latest_education_id, joining_date, ");
		sql.append("project_id, username, password) ");
		sql.append("values(employee_seq.nextval, ");
		sql.append("'" + emp.getFirstName() + "', ");
		sql.append("'" + emp.getLastName() + "', ");
		sql.append("to_timestamp('" + emp.getDob() + "', 'DD-MON-YYYY'),");// HH24:MI:SS'), ");
		sql.append(emp.getGenderId() + ", ");
		sql.append(emp.getEducationId() + ", ");
		sql.append("to_date('" + emp.getDoj() + "', 'DD-MON-YYYY'), ");
		sql.append(emp.getProjectId() + ", ");
		sql.append("'" + emp.getUsername() + "', ");
		sql.append("'" + emp.getPassword() + "')");
		String sqlSkills = "insert into employee_skill(employee_id, skill_id) values(?, ?)";
		String sqlEmpSelect = "select max(id) as id from employee";
		Connection con = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int status = 0;
		int empId = 0;
		try
		{
			con = DbUtil.getConnection();
			stmt = con.createStatement();
			status = stmt.executeUpdate(sql.toString());
			rs = stmt.executeQuery(sqlEmpSelect);
			if(rs.next())
			{
				empId = rs.getInt("id");
			}
			emp.getAddress().setEmpId(empId);
			pstmt = con.prepareStatement(sqlSkills);
			for(int skillId : emp.getSkillIds())
			{
				pstmt.setInt(1, empId);
				pstmt.setInt(2, skillId);
				pstmt.executeUpdate();
			}
			AddressDAO.insertAddress(emp.getAddress());
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();			
		}
		finally
		{
			DbUtil.closeAll(rs, stmt, con);
			DbUtil.closeAll(null, pstmt, null);
		}
		return status;
	}
}
