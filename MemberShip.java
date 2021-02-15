import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MemberShip {
	private int membershipNumber;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String gender;
	private String postAddress;
	private String telNumber;
	private int age;
	private String healthCondition = "";
	private String allergyInfo = "";
	private Date startingDate = null;
	private Date endingDate = null;
	private String memberType = "";
	private int feeAmount;

	public MemberShip(String membershipNumber, String firstName, String lastName, String dateOfBirth, String gender,
			String postAddress, String telNumber, ArrayList<MemberShip> memberShipList) throws ParseException {
		
		setMembershipNumber(membershipNumber, memberShipList);
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.postAddress = postAddress;
		this.telNumber = telNumber;
		setDateOfBirth(dateOfBirth);
		setAge();
	}
	
	public MemberShip(Map<String, String> map, ArrayList<MemberShip> memberShipList) throws ParseException {
		String[] colName = DataTable.colName;
		for(int i=0;i<colName.length-3;i++){
			switch (i) {
			case 0:
				setMembershipNumber(map.get(colName[i]), memberShipList);break;
			case 1:
				this.firstName = map.get(colName[i]);break;
			case 2:
				this.lastName = map.get(colName[i]);break;
			case 3:
				setDateOfBirth(map.get(colName[i]));break;
			case 4:
				this.gender = map.get(colName[i]);break;
			case 5:
				this.postAddress = map.get(colName[i]);break;
			case 6:
				this.telNumber = map.get(colName[i]);break;
			case 7:
				setAge();break;
			case 8:
				this.healthCondition = map.get(colName[i]);break;
			case 9:
				this.allergyInfo = map.get(colName[i]);break;
			case 10:
				setMemberInfo(map.get(colName[i]), map.get(colName[i+2]));
			}
		}
	}
	
	private void setMembershipNumber(String membershipNumber, ArrayList<MemberShip> memberShipList){
		if (membershipNumber.equals("")) {
			int numNew;
			do {
				Random rm = new Random();
				numNew = rm.nextInt(999999);
			} while (isExistNumber(memberShipList, numNew));
			this.membershipNumber = numNew;
		} else {
			this.membershipNumber = Integer.parseInt(membershipNumber);
		}
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	private void setDateOfBirth(String dateOfBirth) throws ParseException {
		if (dateOfBirth.equals("")) {
			this.dateOfBirth = null;
		} else {
			String item[] = dateOfBirth.split("/");
			if (item[2].length() == 2) {
				SimpleDateFormat inSdf = new SimpleDateFormat("dd/MM/yy");
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.YEAR, -100);
				inSdf.set2DigitYearStart(calendar.getTime());
				this.dateOfBirth = inSdf.parse(dateOfBirth);
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
				this.dateOfBirth = simpleDateFormat.parse(dateOfBirth);
			}
		}
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public void setAge() throws ParseException {
		int age = 0;
		if (this.dateOfBirth != null) {
			Calendar cal = Calendar.getInstance();

			// 1st September of the current year
			int year = cal.get(Calendar.YEAR);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date current = simpleDateFormat.parse("01/09/" + year);
			cal.setTime(current);
			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH);
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

			// date of birth
			cal.setTime(this.dateOfBirth);
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

			age = yearNow - yearBirth;

			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					if (dayOfMonthNow < dayOfMonthBirth) {
						age--;
					}
				} else {
					age--;
				}
			}
		}
		this.age = age;
	}

	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}

	public void setAllergyInfo(String allergyInfo) {
		this.allergyInfo = allergyInfo;
	}

	public void setMemberInfo(String startingDate, String memberType) throws ParseException {
		this.memberType = memberType;
		if (startingDate.equals("")) {
			this.startingDate = null;
			this.endingDate = null;
		} else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			this.startingDate = simpleDateFormat.parse(startingDate);
			claculateEndDate();
		}
	}

	private void claculateEndDate() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		rightNow.setTime(this.startingDate);
		if (this.memberType.equals("Monthly_Family")) {
			rightNow.add(Calendar.MONTH, 1);
			this.endingDate = rightNow.getTime();
			this.feeAmount = 60;
		}
		else if(this.memberType.equals("Quarterly_Family")){
			rightNow.add(Calendar.MONTH, 3);
			this.endingDate = rightNow.getTime();
			this.feeAmount = (int) (60 * 3 * 0.95);
		}
		else if(this.memberType.equals("Yearly_Family")){
			rightNow.add(Calendar.MONTH, 12);
			this.endingDate = rightNow.getTime();
			this.feeAmount = (int) (60 * 12 * 0.85);
		}
		else if(this.memberType.equals("Monthly_Individual")){
			rightNow.add(Calendar.MONTH, 1);
			this.endingDate = rightNow.getTime();
			this.feeAmount = 36;
		}
		else if(this.memberType.equals("Quarterly_Individual")){
			rightNow.add(Calendar.MONTH, 3);
			this.endingDate = rightNow.getTime();
			this.feeAmount = (int) (36 * 3 * 0.95);
		}
		else if(this.memberType.equals("Yearly_Individual")){
			rightNow.add(Calendar.MONTH, 12);
			this.endingDate = rightNow.getTime();
			this.feeAmount = (int) (36 * 12 * 0.85);
		}
	}

	public int getMembershipNumber() {
		return membershipNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDateOfBirth() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(dateOfBirth==null){
			return "";
		}
		else{
			return simpleDateFormat.format(dateOfBirth);
		}	
	}

	public String getGender() {
		return gender;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public int getAge() {
		return age;
	}

	public String getHealthCondition() {
		return healthCondition;
	}

	public String getAllergyInfo() {
		return allergyInfo;
	}

	public String getStartingDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(startingDate==null){
			return "";
		}
		return simpleDateFormat.format(startingDate);
	}

	public String getEndingDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(endingDate);
	}

	public String getMemberType() {
		return memberType;
	}

	public int getFeeAmount() {
		return feeAmount;
	}

	public boolean isExistNumber(ArrayList<MemberShip> memberShipList, int membershipNumber) {
		boolean isExist = false;
		for (int i = 0; i < memberShipList.size(); i++) {
			if (membershipNumber == memberShipList.get(i).getMembershipNumber()) {
				isExist = true;
			}
		}
		return isExist;
	}

	// write into new csv
	public String toString() {

		String out = this.membershipNumber + "," + this.firstName + "," + this.lastName + ",";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if (this.dateOfBirth == null) {
			out += "" + ",";
		} else {
			out += simpleDateFormat.format(this.dateOfBirth) + ",";
		}

		out += this.gender + "," + this.postAddress + "," + this.telNumber + ",";

		if (this.age == 0) {
			out += "" + ",";
		} else {
			out += this.age + ",";
		}

		out += this.healthCondition + "," + this.allergyInfo + ",";

		if (this.startingDate == null) {
			out += "" + "," + "" + ",";
		} else {
			out += simpleDateFormat.format(this.startingDate) + "," + simpleDateFormat.format(this.endingDate) + ",";
		}

		if (this.feeAmount == 0) {
			out += this.memberType + "," + "";
		} else {
			out += this.memberType + "," + this.feeAmount;
		}
		return out;
	}
	
	public String searchInfo(int searchType){
		switch (searchType) {
		case 0:
			return this.membershipNumber + "";
		case 1:
			return this.firstName;
		case 2:
			return this.lastName;
		case 3:
			return this.telNumber;
		}
		return "";
	}

}
