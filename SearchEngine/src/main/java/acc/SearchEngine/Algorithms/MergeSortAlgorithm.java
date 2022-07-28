package acc.SearchEngine.Algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class MergeSortAlgorithm {
	
	public static void getSortedPages(Hashtable<?, Integer> ht,int rep)
	{
		 // sorting list
	       ArrayList<Map.Entry<?, Integer>> listarray= new ArrayList(ht.entrySet());
	       Collections.sort(listarray, new Comparator<Map.Entry<?, Integer>>(){

	         public int compare(Map.Entry<?, Integer> in, Map.Entry<?, Integer> in2) {
	            return in.getValue().compareTo(in2.getValue());
	        }});
	      
	       Collections.reverse(listarray);
	       if(rep!=0) {
		       System.out.println("\n ========================= Web Pages RANKED ========================= \n");
		       
		       int i = 10;
		       int j = 1;
		       System.out.printf( "%-10s %s\n", "Sr.no", "Name and repetition" );
		       System.out.println("====================================================");
				while (listarray.size() > j && i>0){
					System.out.printf("\n%-10d| %s\n", j, listarray.get(j));
					j++;
					i--;
				}
				System.out.println("\n===============================================\n");
	       }
	}

}
