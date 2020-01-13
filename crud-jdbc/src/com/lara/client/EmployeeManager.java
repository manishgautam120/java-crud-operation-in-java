package com.lara.client;
import java.util.List;
import java.util.Scanner;
import com.lara.db.dao.EducationDAO;
import com.lara.db.dao.EmployeeDAO;
import com.lara.db.dao.GenderDAO;
import com.lara.db.dao.ProjectDAO;
import com.lara.db.dao.SkillDAO;
import com.lara.entity.Address;
import com.lara.entity.Education;
import com.lara.entity.Employee;
import com.lara.entity.Gender;
import com.lara.entity.Project;
import com.lara.entity.Skill;
public class EmployeeManager
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1 for Inserting Employee");
		System.out.println("Enter 2 for Searching an Employee");
		int action = sc.nextInt();
		if(action == 1)
		{
			insert(sc);
		}
		else if(action == 2)
		{
			//search(sc);
		}
		else
		{
			System.out.println("Input is not proper");
			System.out.println("Enter 1 for insert and 2 for search");
		}
	}
	private static void insert(Scanner sc)
	{
		Address add = new Address();
		Employee emp = new Employee();
		emp.setAddress(add);
		/* Gender selection */
		List<Gender> genders = GenderDAO.readAllGenders();
		System.out.println("Please select Gender");
		for(Gender gender : genders)
		{
			System.out.print("enter " + gender.getId() + " ");
			System.out.println("for " + gender.getName());
		}
		emp.setGenderId(sc.nextInt());
		/* Education selection */
		List<Education> educations = EducationDAO.readAllEducations();
		System.out.println("Please select education");
		for(Education education : educations)
		{
			System.out.print("enter " + education.getId() + " ");
			System.out.println("for " + education.getName());
		}
		emp.setEducationId(sc.nextInt());
		/* Project selection */
		List<Project> projects = ProjectDAO.readAllProjects();
		System.out.println("Please select project");
		for(Project project : projects)
		{
			System.out.print("enter " + project.getId() + " ");
			System.out.println("for " + project.getName());
		}
		emp.setProjectId(sc.nextInt());
		/* Skills selection */
		List<Skill> skills = SkillDAO.readAllSkills();
		System.out.println("Please select skills by using comma as delimiter");
		for(Skill skill : skills)
		{
			System.out.print("enter " + skill.getId() + " ");
			System.out.println("for " + skill.getName());
		}
		String skillsStr = sc.next();
		String skillsElements[] = skillsStr.split(",");
		int skillIds[] = new int[skillsElements.length];
		for(int i = 0; i < skillsElements.length; i++)
		{
			skillIds[i] = Integer.parseInt(skillsElements[i].trim());
		}
		emp.setSkillIds(skillIds);
		/* enter dob */
		System.out.println("Please Enter Date of birth");
		System.out.println("format --> DD-MON-YYYY");
		emp.setDob(sc.next());
		/* enter doj */
		System.out.println("Please Enter Date of Join");
		System.out.println("format --> DD-MON-YYYY");
		emp.setDoj(sc.next());
		/* first name */
		System.out.println("Please enter first name");
		emp.setFirstName(sc.next());
		/* last name */
		System.out.println("Please enter last name");
		emp.setLastName(sc.next());
		/* house no */
		System.out.println("Please enter house no");
		add.setHouseNo(sc.next());
		/* street name*/
		System.out.println("Please enter street name");
		add.setStreetName(sc.next());
		/* city name*/
		System.out.println("Please enter city");
		add.setCity(sc.next());
		/* state name*/
		System.out.println("Please enter state");
		add.setState(sc.next());
		/* username */
		System.out.println("Please username");
		emp.setUsername(sc.next());
		/* password */
		System.out.println("Please enter password");
		emp.setPassword(sc.next());
		EmployeeDAO.insertEmployee(emp);
	}
}
