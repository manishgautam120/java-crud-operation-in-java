package com.lara.db.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.lara.db.util.DbUtil;
import com.lara.entity.Gender;
public class GenderDAO
{
	public static List<Gender> readAllGenders()
	{
		List<Gender> genders = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			con = DbUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, name from gender");
			Gender gender = null;
			while(rs.next())
			{
				gender = new Gender();
				gender.setId(rs.getInt("id"));
				gender.setName(rs.getString("name"));
				genders.add(gender);
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
		return genders;
	}
}
