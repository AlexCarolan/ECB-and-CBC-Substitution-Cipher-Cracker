
public class startcracker {
	
	public static void main (String args[]) {
		
		String cipherMode = "";
		String c1Name = "";
		String p1Name = "";
		String c2Name = "";
		String outputName = "";
		int c1IV = '0';
		int c2IV = '0';
		
		//Get input settings
		try {
			cipherMode = args[0];
			c1Name = args[1];
			p1Name = args[2];
			c2Name = args[3];
			outputName = args[4];
		} catch (Exception e) {
			System.out.println("Not enough or invalid input arguments");
			System.exit(0);
		}
		
		try{
			c1IV = Integer.parseInt(args[5]);
			c2IV = Integer.parseInt(args[6]);
		} catch (Exception e) {
			
			if (!cipherMode.equals("ECB")) {
				System.out.println("System Requires IV values or mode was invalid");
				System.exit(0);
			}
		}
		
		//Start using the correct mode
		if (cipherMode.equals("ECB")) {
			
			ecbcracker cracker = new ecbcracker();
			cracker.start(c1Name, p1Name, c2Name, outputName);
			
		} else if (cipherMode.equals("CBC")) {
			
			cbccracker cracker = new cbccracker();
			cracker.start(c1Name, p1Name, c2Name, outputName, c1IV, c2IV);
			
		} else {
			System.out.println("Invalid mode try ECB or CBC");
		}
		
	}
	

}


