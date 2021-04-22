package ecom.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {
public static final String LOGIN_PAGE_TITLE="Account Login";
public static final String HOME_PAGE_TITLE="My Account";
public static final String HOME_PAGE_HEADER="Your Store";
public static final String CART_PAGE_TITLE="Shopping Cart";
public static final String REGISTER_PAGE_TITLE="Register Account";
public static final String REGISTER_DATA_SHEET_NAME="Registration";
public static final String REGISTER_PAGE_SUCCESS_HEADER="Your Account Has Been Created!";
public static final int SUBHEADERS_COUNT=4;
public static List<String> expectedSubHeadList() {
	List<String> expList = new ArrayList<String>();
	expList.add("My Account");
	expList.add("My Orders");
	expList.add("My Affiliate Account");
	expList.add("Newsletter");
	
	return expList;
}
public static List<String> expectedColumnList() {
	List<String> expList = new ArrayList<String>();
	expList.add("Image");
	expList.add("Product Name");
	expList.add("Model");
	expList.add("Quantity");
	expList.add("Unit Price");
	expList.add("Total");
	
	return expList;
}

}
