package model;

public class Staff {

	private int staffID,roleID;
	private String staffName,roleName;
	
	public Staff(int roleID,String roleName,int staffID,String staffName)
	{
		super();
		this.roleID=roleID;
		this.roleName=roleName;
		this.staffID=staffID;
		this.staffName=staffName;
	}
	
	public Staff(String roleName)
	{
		super();
		this.roleName=roleName;
	}
	
	public Staff(int roleID,String roleName)
	{
		super();
		this.roleID=roleID;
		this.roleName=roleName;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return roleName;
	}

	
	
	
	
}
