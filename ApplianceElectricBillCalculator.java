import java.util.*;

public class ApplianceElectricBillCalculator 
{
	private static final double WATTS_IN_KWH = 1000.0;
	private static final int DAYS_IN_A_WEEK = 7;
	private static final int DAYS_IN_A_MONTH = 30;
	
	public static void main(String[] args) throws Exception
	{
		startProgram();
	}
	
	private static void startProgram() throws Exception
	{		
		Scanner scan = new Scanner(System.in);
		
		try
		{
			System.out.println("------------------------------- Appliance Electric Bill Calculator -------------------------------\n\n");
			getUserInput(scan);		
		}
		catch(InputMismatchException ex)
		{
		    System.out.println("Invalid input. Please enter a number.");
		    scan.nextLine();  // Clear the scanner
		    getUserInput(scan);
		}
		finally
		{
			scan.close();
		}
	}

	
	private static void getUserInput(Scanner scan) throws Exception
	{
	    double pricePerKwH;
	    int wattageRating;
	    int hoursPerDay;

	    while(true)
	    {
	        try
	        {
	            System.out.print("Enter price per KwH: ");
	            pricePerKwH = scan.nextDouble();
	            scan.nextLine();
	            
	            System.out.print("Enter the wattage rating of the appliance (in watts): ");
	            wattageRating = scan.nextInt();
	            scan.nextLine();
	            
	            System.out.print("Enter hours of usage per day: ");
	            hoursPerDay = scan.nextInt();
	            scan.nextLine();

	            break;  // Exit the loop when valid input is received
	        }
	        catch(Exception ex)
	        {
	            System.out.println("Invalid input. Please enter a number.");
	            scan.nextLine();  // Clear the scanner
	        }
	    }
	    displayCalculations(pricePerKwH, wattageRating, hoursPerDay);		
	}

	
	private static void displayCalculations(double pricePerKwh, int wattageRating, int hoursPerDay)
	{
		System.out.println("\nResults: \n");
		System.out.printf("Per day: \tPhp %.2f \n", calculatePerDay(pricePerKwh, wattageRating, hoursPerDay));
		System.out.printf("Per week: \tPhp %.2f \n", calculatePerWeek(pricePerKwh, wattageRating, hoursPerDay));
		System.out.printf("Per month: \tPhp %.2f \n", calculatePerMonth(pricePerKwh, wattageRating, hoursPerDay));
	}
	
	private static double calculatePerDay(double pricePerKwh, int wattageRating, int hoursPerDay)
	{
		double Kwh = wattageRating / WATTS_IN_KWH;
		double KwhPerDay = Kwh * hoursPerDay;
		double dailyBill = KwhPerDay * pricePerKwh;	
		
		return dailyBill;
	}
	
	private static double calculateForDays(double pricePerKwh, int wattageRating, int hoursPerDay, int days) {
	    double dailyBill = calculatePerDay(pricePerKwh, wattageRating, hoursPerDay);
	    return dailyBill * days;
	}

	
	private static double calculatePerWeek(double pricePerKwh, int wattageRating, int hoursPerDay)
	{	
		return calculateForDays(pricePerKwh, wattageRating, hoursPerDay, DAYS_IN_A_WEEK);
	}
	
	private static double calculatePerMonth(double pricePerKwh, int wattageRating, int hoursPerDay)
	{
		return calculateForDays(pricePerKwh, wattageRating, hoursPerDay, DAYS_IN_A_MONTH);
	}
}
