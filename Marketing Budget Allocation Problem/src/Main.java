import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<InvestmentInfo> in = new ArrayList<InvestmentInfo>();
		int nChannels;
		int totalBudget;
		
		totalBudget = sc.nextInt();
		nChannels = sc.nextInt();
		sc.next();
		
		String inputLine = null;
		for(int i = 0; i < nChannels; i++) {
			inputLine = sc.nextLine();
			String[] nameAndROI = inputLine.split(" ");
			String name = nameAndROI[0];
			Double ROI = Double.parseDouble(nameAndROI[1])/100.0;
			in.add(new InvestmentInfo(name, ROI, null, null));
		}
		
		for(int i = 0; i < nChannels; i++) {
			inputLine = sc.nextLine();
			String[] landU = inputLine.split(" ");
			Double lower = (landU[0].equals("x"))? -1.0 :Double.parseDouble(landU[0])*1000.0;
			Double upper = (landU[1].equals("x"))? -1.0 :Double.parseDouble(landU[1])/100.0;
			in.get(i).setLowerBound(lower);
			in.get(i).setUpperBound(upper);
		}
		
		
		InvestmentInfoArray ia = new InvestmentInfoArray(in, totalBudget*1000);
		MBA mba = new MBA(ia);
		
		System.out.println(mba.GA());
		
	}

}
