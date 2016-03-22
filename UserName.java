package RestaurantManagement;

public class UserName {
	private static String userName; //保存登录用户名
	private static String tableNum;  //保存餐桌编号
	
	static void setUserName(String str)
	{
		userName=str;
	}
	
	static String getUserName()
	{
		return userName;
	}
	
	static void settableNum(String i)
	{
		tableNum=i;
	}
	
	static String gettableNum()
	{
		return tableNum;
	}


}
