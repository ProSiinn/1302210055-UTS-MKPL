package lib;

public class TaxFunction {

	


	private static final int TAX_RATE = 5;
    private static final int TAX_EXEMPTION_SINGLE = 54000000;
    private static final int TAX_EXEMPTION_MARRIED = 4500000;
    private static final int TAX_EXEMPTION_PER_CHILD = 1500000;
    private static final int MAX_CHILDREN_EXEMPTION = 3;
    private static final int MONTHS_IN_YEAR = 12;

	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible, boolean isMarried, int numberOfChildren) {
        int tax = 0;

        if (numberOfMonthsWorked > MONTHS_IN_YEAR) {
            System.err.println("More than 12 months working per year");
            numberOfMonthsWorked = MONTHS_IN_YEAR;
        }

        int taxExemption = calculateTaxExemption(isMarried, numberOfChildren);

        int totalIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked;
        int taxableIncome = totalIncome - deductible - taxExemption;

        tax = (int) Math.round((TAX_RATE / 100.0) * taxableIncome);

        return Math.max(0, tax);
    }

    private static int calculateTaxExemption(boolean isMarried, int numberOfChildren) {
        int taxExemption = isMarried ? TAX_EXEMPTION_MARRIED : TAX_EXEMPTION_SINGLE;
        taxExemption += Math.min(numberOfChildren, MAX_CHILDREN_EXEMPTION) * TAX_EXEMPTION_PER_CHILD;
        return taxExemption;
    }
	
}
