package acc.SearchEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import acc.SearchEngine.Algorithms.BoyerMooreAlgorithm;
import acc.SearchEngine.Algorithms.BoyerMooreSearchWords;
import acc.SearchEngine.Algorithms.MergeSortAlgorithm;
import acc.SearchEngine.Web.WebCrawlerEvent;


public class SearchEngineApplication {

	public static ArrayList<String> values = new ArrayList<String>();
	public static Hashtable<String, Integer> hashvalues = new Hashtable<String, Integer>();

	public SearchEngineApplication() {
		System.out.println(" ========================= Search Engine Started ========================== ");
	}

	// Boyer moore algorithm is used for searching.
	public static int searchBM(String pattern, String text) {
		BoyerMooreAlgorithm bmoore = new BoyerMooreAlgorithm(pattern);
		int value = bmoore.search(text);
		return value;
	}

	public static void searchEngine() {
		System.out.println("\n ========================= Crawler started ========================= ");
		String url = "https://www.javatpoint.com/python-tutorial";
		WebCrawlerEvent.startCrawl(url);
		System.out.println("\n ========================= Crawler Stopped ========================= ");
		
		/** to save word repetition in all text documents where the key is the file name and the value is repetition */
		Hashtable<String, Integer> repeatwords = new Hashtable<String, Integer>();
		Scanner scan = new Scanner(System.in);
		String in = "y";
		do {
			System.out.println("\n Enter the word to be searched: ");
			String parse = scan.nextLine();
			long valuefn = 0;
			int r = 0;
			int page = 0;
			try {
				File directory = new File(System.getProperty("user.dir") + "\\textFiles\\");

				File[] arraylist = directory.listFiles();
				for (int i = 0; i < arraylist.length; i++) {
					// Search word from user input.
					r = BoyerMooreSearchWords.getSearchedWords(arraylist[i], parse);
					repeatwords.put(arraylist[i].getName(), r);
					if (r != 0)
						page++;
					valuefn++;
				}

				if (page == 0) {
					System.out.println("Not Found!!");
					System.out.println("Searching web =========================== ");
					BoyerMooreSearchWords.lookForalwords(parse);
				} 
				else {
					//Merge sort is used to rank web pages. 
					//MergeSort.pageSort by default uses merge sort
					MergeSortAlgorithm.getSortedPages(repeatwords,page);
				}	
				System.out.println("\n\n Do you want to continue search(y/n)??");
				in = scan.nextLine();
			} catch(Exception e) {
				e.printStackTrace();
			}
		} 
		while(in.equals("y"));
		System.out.println("\n =========================================================== \n");
		System.out.println(" Browser Stopped !!! ");
		System.out.println("\n ============================================================ \n");
			
	}

	// search engine function is runned by main method
	public static void main(String[] args) {
			SearchEngineApplication.searchEngine();		
	}
}
