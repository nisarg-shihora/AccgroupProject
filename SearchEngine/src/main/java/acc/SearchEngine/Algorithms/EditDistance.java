package acc.SearchEngine.Algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;

import acc.SearchEngine.SearchEngineApplication;

public class EditDistance {

	/**
	 * Finds the edit distance between the words word1 and word2
	 */
	public static int findEditDistance(String input1, String input2)
	{
		int l1 = input1.length();
		int l2 = input2.length();
	 
		// l1+1, l2+1, because finally return ed[len1][len2]
		int[][] ed = new int[l1 + 1][l2 + 1];
 
		for (int i = 0; i <= l1; i++) {
			ed[i][0] = i;
		}
	 
		for (int j = 0; j <= l2; j++) {
			ed[0][j] = j;
		}
	 
		//loop over to validate last char value
		for (int l = 0; l < l1; l++) {
			char a = input1.charAt(l);
			for (int m = 0; m < l2; m++) {
				char b = input2.charAt(m);
	 
				//if 
				if (a == b) {
					//update ed value for +1 length
					ed[l + 1][m + 1] = ed[l][m];
				} else {
					int update = ed[l][m] + 1;
					int enter = ed[l][m+ 1] + 1;
					int remove = ed[l + 1][m] + 1;
	 
					int value = update > enter ? enter : update;
					value = remove > value ? value : remove;
					ed[l + 1][m + 1] = value;
				}
			}
		}
		return ed[l1][l2];		
	}
	
	/*searching input word in file*/
	public static void searchWords(File srcFile,int number,Matcher m,String pattern)
	{
		try
    	{
			BufferedReader rd = new BufferedReader(new FileReader(srcFile));
			String line = null;
			
			while ((line = rd.readLine()) != null){
				m.reset(line);
				while (m.find()) {
					SearchEngineApplication.values.add(m.group());
				}
			}
			rd.close();
			for(int n= 0; n<SearchEngineApplication.values.size(); n++){
				SearchEngineApplication.values.put(SearchEngineApplication.values.get(n), findEditDistance(pattern.toLowerCase(),SearchEngineApplication.values.get(n).toLowerCase()));
			}
    	}     
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
	}
}
