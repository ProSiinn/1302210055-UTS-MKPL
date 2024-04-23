package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private LocalDate dateJoined;
	private boolean isForeigner;
	private Gender gender; 
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	

	
	public void setMonthlySalary(Grade grade) {
        int baseSalary = grade.getSalary();
        monthlySalary = isForeigner ? (int) (baseSalary * 1.5) : baseSalary;
    }
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	
    public int getAnnualIncomeTax() {
        int monthsWorked = calculateMonthsWorked();
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorked, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }

    
    private int calculateMonthsWorked() {
        LocalDate now = LocalDate.now();
        return dateJoined.until(now).getMonths() + 1;
    }

	public enum Grade {
        GRADE_1(3000000),
        GRADE_2(5000000),
        GRADE_3(7000000);

        private final int salary;

        Grade(int salary) {
            this.salary = salary;
        }
		public int getSalary() {
            return salary;
        }
	}

	public enum Gender {
        MALE,
        FEMALE
    }
}
