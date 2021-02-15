import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;

public class ClubMembership {

	private static ArrayList<MemberShip> membershipList;
	private static DataTable dataTable;
	private static Vector allData = new Vector();

	public static ArrayList<MemberShip> getMembershipList() {
		return membershipList;
	}

	public static DataTable getDataTable() {
		return dataTable;
	}

	public static Vector getAllData() {
		return allData;
	}

	public static void setAllData() {
		// set all data into vector
		allData.clear();
		for (int i = 0; i < membershipList.size(); i++) {
			Vector<String> singleCustomer = new Vector<>();
			Vector<String> singleCustomerAll = new Vector<>();
			String[] customerInfoSplit = membershipList.get(i).toString().split(OperationCSV.csvSplitBy);
			String[] customerInfo = new String[DataTable.headSize];
			System.arraycopy(customerInfoSplit, 0, customerInfo, 0, customerInfoSplit.length);
			for (String s : customerInfo) {
				singleCustomer.add(s);
				singleCustomerAll = (Vector<String>) singleCustomer.clone();
			}
			allData.add(singleCustomerAll);
			singleCustomer.clear();
		}
	}

	public static String searchData(String searchColName, String searchInfoText) {
		// set the searched data into vector
		allData.clear();

		int searchType = 4;
		if (searchColName.equals("CustomerNum")) {
			searchType = 0;
		} else if (searchColName.equals("FirstName")) {
			searchType = 1;
		} else if (searchColName.equals("LastName")) {
			searchType = 2;
		} else if (searchColName.equals("TelNumber")) {
			searchType = 3;
		}

		int count = 0;
		for (int i = 0; i < membershipList.size(); i++) {
			if (membershipList.get(i).searchInfo(searchType).equals(searchInfoText)) {
				Vector<String> singleCustomer = new Vector<>();
				Vector<String> singleCustomerAll = new Vector<>();
				String[] customerInfoSplit = membershipList.get(i).toString().split(OperationCSV.csvSplitBy);
				String[] customerInfo = new String[DataTable.headSize];
				System.arraycopy(customerInfoSplit, 0, customerInfo, 0, customerInfoSplit.length);
				for (String s : customerInfo) {
					singleCustomer.add(s);
					singleCustomerAll = (Vector<String>) singleCustomer.clone();
				}
				allData.add(singleCustomerAll);
				singleCustomer.clear();
				count++;
			}
		}
		
		if(count == 0){   // cannot search
			return "ERROR";
		}
		else{
			return "";
		}
		
	}

	public static void main(String[] args) throws ParseException, IOException {

		// read original csv
		OperationCSV operationCSV = new OperationCSV("customerlist.csv");

		// repair original csv
		if (operationCSV.repairCSV()) {
			membershipList = operationCSV.getMemberShipList();
			operationCSV.writeCSV();
		}

		// load new csv
		operationCSV.loadCSV();

		// get membership list
		membershipList = operationCSV.getMemberShipList();

		// get data table
		ClubMembership.setAllData();
		dataTable = new DataTable();

		// start the frame
		WelcomeFrame welcomeFrame = new WelcomeFrame();
		welcomeFrame.setVisible(true);
	}
}
