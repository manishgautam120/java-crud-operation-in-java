package com.lara.db.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.lara.db.util.DbUtil;
import com.lara.entity.Project;
public class ProjectDAO
{
	public static List<Project> readAllProjects()
	{
		List<Project> projects = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			con = DbUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, name from project");
			Project project = null;
			while(rs.next())
			{
				project = new Project();
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				projects.add(project);
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
		return projects;
	}
}
