import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.lang.StringBuilder;
import java.io.PrintWriter;
import java.io.IOException;

public class ecbcracker {
	
	public void start (String cipherTextName, String plainTextName, String SecondCipherTextName, String outputFileName) {	
		//Read all file data for both texts
		
		String plainText = "";
		String cipherText = "";
		String secondCipherText = "";
		
		try{
			plainText = new String (Files.readAllBytes(Paths.get(plainTextName)));
			cipherText = new String (Files.readAllBytes(Paths.get(cipherTextName)));
			secondCipherText = new String (Files.readAllBytes(Paths.get(SecondCipherTextName)));
			
		} catch (Exception e) {
			System.out.println("Unable to find or read file");
			System.out.println(e);
			System.exit(0);
		}
		
		//Create hash maps to hold key letter pairings
		HashMap<Character, Character> letterMap = new HashMap<>();
		HashMap<Character, Character> reverseLetterMap = new HashMap<>();
		
		//get array of normal alphabet positions
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		
		//Itterate through the plain and cipher text
		for (int i=0; i < plainText.length(); i++){
			char pChar = plainText.charAt(i);        
			char cChar = cipherText.charAt(i);
			
			//Map each letter of the cipher text to the plain text
			letterMap.put(pChar, cChar);
			reverseLetterMap.put(cChar, pChar);
		}
		
		//Print out key letter pairings
		System.out.println("Mode: ECB, Key:");
		for (int i=0; i<alphabet.length; i++){	
			System.out.println(alphabet[i] + "->" + letterMap.get(alphabet[i]));
		}
		
		//Create a string builder to assemble the plain text
		StringBuilder plainTextBuilder = new StringBuilder(); 
		
		//Build the string by decoding the cipher text
		for (int i=0; i < secondCipherText.length(); i++){
			char cChar = secondCipherText.charAt(i);        
			plainTextBuilder.append(reverseLetterMap.get(cChar));
			
		}
		
		//Output result to specified file
		try {
			PrintWriter writer = new PrintWriter(outputFileName, "UTF-8");
			writer.println(plainTextBuilder.toString());
			writer.close();
		} catch (IOException e) {
			System.out.println("Unable to output file");
			System.out.println(e);
		}
			
	}
	
			
		

}

