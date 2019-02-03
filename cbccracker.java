import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.lang.StringBuilder;
import java.io.PrintWriter;
import java.io.IOException;

public class cbccracker {
	
	public void start (String cipherTextName, String plainTextName, String SecondCipherTextName, String outputFileName, int IV_One, int IV_Two) {	
		//Read all file data for both texts
		
		String plainText = "";
		String cipherText = "";
		String secondCipherText = "";
		
		// IF 26 or larger - 26
		// to find letter subrtact previous and map
		
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
		
		char prevChar = (char)(IV_One + 97);
		
		//Itterate through the plain and cipher text
		for (int i=0; i < plainText.length(); i++){
			char pChar = plainText.charAt(i);        
			char cChar = cipherText.charAt(i);
			
			//Compute addition mod(26) of plain char and IV/prev char
			int modChar = pChar + (prevChar - 97);
			if (modChar > 122){
				modChar = modChar - 26;
			}
			
			prevChar = cChar;
			
			//Map each letter of the cipher text to the plain text
			letterMap.put((char)modChar, cChar);
			reverseLetterMap.put(cChar, (char)modChar);
		}
		
		
		//Print out key letter pairings
		System.out.println("Mode: CBC, Key:");
		for (int i=0; i<alphabet.length; i++){	
			System.out.println(alphabet[i] + "->" + letterMap.get(alphabet[i]));
		}
		
		
		//Create a string builder to assemble the plain text
		StringBuilder plainTextBuilder = new StringBuilder(); 
		
		char prevCharDec = (char)(IV_Two + 97);
		
		//Build the string by decoding the cipher text
		for (int i=0; i < secondCipherText.length(); i++){
			
			char cChar = secondCipherText.charAt(i);
			char modChar = reverseLetterMap.get(cChar);
			int pChar = (modChar - (prevCharDec - 97));
			
			//If less than 'a' wrap back from 'z'
			if (pChar < 97) {
				pChar = ((pChar - 96) + 122);
			}
			       
			prevCharDec = cChar;      
			       
			plainTextBuilder.append((char)pChar);
			
		}
		
		System.out.println(plainTextBuilder.toString());
		
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


