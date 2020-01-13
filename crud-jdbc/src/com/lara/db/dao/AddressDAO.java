package com.lara.db.dao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.lara.db.util.DbUtil;
import com.lara.entity.Address;
public class AddressDAO
{
	public static int insertAddress(Address add)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("insert into address(id, house_no,");
		sql.append("street_name, city, state, employee_id) ");
		sql.append("values(address_seq.nextval, ");
		sql.append("'" + add.getHouseNo() + "',");
		sql.append("'" + add.getStreetName() + "',");
		sql.append("'" + add.getCity() + "',");
		sql.append("'" + add.getState() + "',");
		sql.append(add.getEmpId() + ")");
		int status = 0;
		Connection con = null;
		Statement stmt = null;		
		try
		{
			con = DbUtil.getConnection();
			stmt = con.createStatement();
			status = stmt.executeUpdate(sql.toString());
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			DbUtil.closeAll(null, stmt, con);
		}
		return status;
	}
}
