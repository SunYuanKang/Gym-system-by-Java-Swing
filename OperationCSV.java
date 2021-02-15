import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OperationCSV {

	private final ArrayList<MemberShip> memberShipList = new ArrayList<MemberShip>();
	private String filename;
	
	public OperationCSV(String filename) {
		this.filename = "./" + filename;
	}

	// the regex to split csv file
	public static String csvSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
	
	public ArrayList<MemberShip> getMemberShipList() {
		return memberShipList;
	}

	// handle the issues in original CSV
	public boolean repairCSV() throws ParseException, IOException {
		boolean isRepaired = false;
		String paramsNameOriginal[] = { "customerNum", "firstName", "lastName", "dateOfBirth", "gender", "postAddress",
				"telNumber" };
		Map<String, String> map = new HashMap<String, String>();
		ArrayList<String> allLine = new ArrayList<String>();

		// read original CSV
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(filename));
		String line = null;
		while ((line = reader.readLine()) != null) {
			allLine.add(line);
		}
		reader.close();
		if(!allLine.get(0).split(csvSplitBy)[0].equals("")){
			isRepaired = false;
			return isRepaired;
		}
		
		// change the data into MemberShip class
		for (int i = 0; i < allLine.size(); i++) {
			String everyLine = allLine.get(i);
			String item[] = everyLine.split(csvSplitBy);
			for (int j = 0; j < item.length; j++) {
				map.put(paramsNameOriginal[j], item[j]);
			}
			for (int j = 0; j < paramsNameOriginal.length; j++) {
				if (!map.containsKey(paramsNameOriginal[j])) {
					map.put(paramsNameOriginal[j], "");
				}
			}
			MemberShip membership = new MemberShip(map.get(paramsNameOriginal[0]), map.get(paramsNameOriginal[1]),
					map.get(paramsNameOriginal[2]), map.get(paramsNameOriginal[3]), map.get(paramsNameOriginal[4]),
                    map.get(paramsNameOriginal[5]),map.get(paramsNameOriginal[6]), memberShipList);
			
			// remove multiple entries of a same person
			boolean isMultiple = false;
			for(int j=0;j<memberShipList.size();j++){
				if(membership.getFirstName().equals(memberShipList.get(j).getFirstName()) && membership.getLastName().equals(memberShipList.get(j).getLastName())){
					if(membership.getDateOfBirth()=="" || membership.getDateOfBirth().equals(memberShipList.get(j).getDateOfBirth())){
						isMultiple = true;
					}
				}
			}
			if(!isMultiple){
				memberShipList.add(membership);
			}
			map.clear();
		}
		isRepaired = true;
		return isRepaired;
	}
	
	// write all data into new csv
	public void writeCSV() throws IOException{
		ArrayList<MemberShip> memberShipList = ClubMembership.getMembershipList();	
		File csv = new File(filename);
		BufferedWriter bw = new BufferedWriter(new FileWriter(csv));
		for(int i=0;i<memberShipList.size();i++){
			String line = memberShipList.get(i).toString();
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}
	
	// load new csv
	public void loadCSV() throws IOException, ParseException{
		
		memberShipList.clear();
		String[] colName = DataTable.colName;
		
		Map<String, String> map = new HashMap<String, String>();
		ArrayList<String> allLine = new ArrayList<String>();

		// read original CSV
		BufferedReader reader;
		reader = new BufferedReader(new FileReader(filename));
		String line = null;
		while ((line = reader.readLine()) != null) {
			allLine.add(line);
		}
		reader.close();
		
		for (int i = 0; i < allLine.size(); i++) {
			String everyLine = allLine.get(i);
			String item[] = everyLine.split(csvSplitBy);
			for (int j = 0; j < item.length; j++) {
				map.put(colName[j], item[j]);
			}
			for (int j = 0; j < colName.length; j++) {
				if (!map.containsKey(colName[j])) {
					map.put(colName[j], "");
				}
			}
			MemberShip membership = new MemberShip(map, memberShipList);
			memberShipList.add(membership);
			map.clear();
		}
	}
}
