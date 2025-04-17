package lib;

public class TaxFunction {

    private static final int BASE_PTKP = 54000000;
    private static final int MARRIAGE_PTKP = 4500000;
    private static final int CHILD_PTKP = 1500000;
    private static final int MAX_CHILDREN = 3;
    private static final double TAX_RATE = 0.05;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan dikurangi penghasilan tidak kena pajak.
     */
    public static int calculateTax(
            int monthlySalary,
            int otherMonthlyIncome,
            int numberOfMonthWorking,
            int deductible,
            boolean isMarried,
            int numberOfChildren
    ) {
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        if (numberOfChildren > MAX_CHILDREN) {
            numberOfChildren = MAX_CHILDREN;
        }

        int annualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int ptkp = calculatePTKP(isMarried, numberOfChildren);
        int taxableIncome = annualIncome - deductible - ptkp;

        int tax = (int) Math.round(TAX_RATE * taxableIncome);
        return Math.max(tax, 0);
    }

    private static int calculatePTKP(boolean isMarried, int numberOfChildren) {
        int ptkp = BASE_PTKP;
        if (isMarried) {
            ptkp += MARRIAGE_PTKP;
        }
        ptkp += Math.min(numberOfChildren, MAX_CHILDREN) * CHILD_PTKP;
        return ptkp;
    }
}
