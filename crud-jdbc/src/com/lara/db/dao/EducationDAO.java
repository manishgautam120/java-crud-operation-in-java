package com.lara.db.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.lara.db.util.DbUtil;
import com.lara.entity.Education;;
public class EducationDAO
{
	public static List<Education> readAllEducations()
	{
		List<Education> educations = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			con = DbUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, name from education");
			Education education = null;
			while(rs.next())
			{
				education = new Education();
				education.setId(rs.getInt("id"));
				education.setName(rs.getString("name"));
				educations.add(education);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(rs, stmt, con);
		}
		return educations;
	}
}
