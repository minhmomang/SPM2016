package Helper;


import DAL.ErrorDAL;

public class ErrorMesage {

	public static String getMesageError(int error)
	{
		return ErrorDAL.getMesageError(error);
	}
}
