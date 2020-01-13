package com.lara.db.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.lara.db.util.DbUtil;
import com.lara.entity.Skill;
public class SkillDAO
{
	public static List<Skill> readAllSkills()
	{
		List<Skill> skills = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			con = DbUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, name from skill");
			Skill skill = null;
			while(rs.next())
			{
				skill = new Skill();
				skill.setId(rs.getInt("id"));
				skill.setName(rs.getString("name"));
				skills.add(skill);
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
		return skills;
	}
}
