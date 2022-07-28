
package acc.SearchEngine.Algorithms;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import acc.SearchEngine.SearchEngineApplication;

public class BoyerMooreSearchWords {

	/* Using the Boyer Moore algorithm, search for words in a text file. returns the number of times the words have been repeated */
	public static int getSearchedWords(File fileLoc, String word)
	{
		int cnt=0;
		String value="";
		try
		{
			BufferedReader obj = new BufferedReader(new FileReader(fileLoc));
			String l = null;
			
			while ((l = obj.readLine()) != null){
				value= value+l;
			}
			obj.close();
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
		}
		// Finding position of word ::
		String txt = value;
		int set = 0;
		
		for (int loc = 0; loc <= txt.length(); loc += set + word.length()) 
		{
			set = SearchEngineApplication.searchBM(word, txt.substring(loc)); 
			if ((set + loc) < txt.length()) {
				cnt++;
				System.out.println("\n"+word+ " at position " + (set + loc));  //printing position of word
			}
		}
		if(cnt!=0)	{		
			System.out.println(" =========================================================== ");
			System.out.println("\n Located in "+fileLoc.getName()); // Founded from which text file..		
			System.out.println(" =========================================================== ");
		}
		return cnt;
	}
	
	// executing editdistance in similar strings
	public static void findData(File source,int number,Matcher m,String pattern) throws FileNotFoundException,ArrayIndexOutOfBoundsException{
		EditDistance.searchWords(source, number, m, pattern);
	}
	
	public static void lookForalwords(String pattern)
	{
		String strline = " ";
		String patern1 = "[a-zA-Z0-9]+";	  
		 
		// Object creation of pattern
		Pattern r3 = Pattern.compile(patern1);
		// Object creation of matcher
		Matcher m3 = r3.matcher(strline);
		int num=0;
		
		File directory = new File(System.getProperty("user.dir")+"\\textFiles\\");
		File[] farray = directory.listFiles();
		for(int i=0 ; i<farray.length ; i++)
		{
			try
			{
				findData(farray[i],num,m3,pattern);
				num++;
			} 
			catch(FileNotFoundException e) {
				System.out.println("Exception:"+e.getMessage());
			}
		}
		
		Integer valdistanceAllowed = 1;  	// allowed editDistance 
		
		boolean foundmatch = false;  		//if word is found having edit distance and valuedistanceallowed are equal then true

		System.out.println("Did you mean? ");
		int val=0;
		for(Map.Entry m: SearchEngineApplication.values.entrySet()){
			if(valdistanceAllowed == m.getValue()) {
				val++;
				System.out.print("("+val+") "+m.getKey()+"\n");		
				foundmatch = true;
			}
		}	        
		if(!foundmatch) System.out.println("Can't find match for given word");
	}
}
