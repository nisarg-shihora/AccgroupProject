package acc.SearchEngine.Web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawlerEvent {
	static HashSet<String> urls = new HashSet<String>(); 
		
	public static void webCrawler(String urlValue)
	{
		urls.add(urlValue);
		try {			
			 Document dc = Jsoup.connect(urlValue).get();								// Use to fetch and parse the HTML page
			 String urlp = ".*" + urlValue.replaceAll("^(http|https)://", "") + ".*";	// replacing blank with //http or //https
			 System.out.println("\nURL parsing : "+ urlp);

			 Elements urlsonwebpage = dc.select("a[href]");								// searchs urls with a tag of href attributes 
			 
			 String url123;
			 for (Element page : urlsonwebpage) {
				 url123 = page.attr("abs:href");								// obtain an ultimate URL from an attribute that related to URL
				 if(urls.contains(url123)) {
					 System.out.println("\nURL: " + url123 + " =====> url is already visited");			// example : https://www.javatpoint.com/python-tutorial
				 } 
				 else if(!Pattern.matches(urlp, url123)) {
					 System.out.println("\nURL: " + url123 + " =====> is irrevant. Will not be parsed."); // example : https://www.javatpoint.com/python-literals
				 }
				 else {
					 urls.add(page.attr("abs:href"));
					 System.out.println("\nURL: " + url123 + " =====>  web page will be crawled.");		//https://www.facebook.com/sharer.php?u=https://www.javatpoint.com/java-tutorial
				 }
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void convertHtmlToText()
	{
		try {
			String value, url123;
			String path = System.getProperty("user.dir") + "\\textFiles\\";
			Iterator<String> iterator = urls.iterator();
			while(iterator.hasNext())
			{
				url123 = iterator.next();
				Document dc = Jsoup.connect(url123).get();
				value = dc.text();
				String name = dc.title().replaceAll("[^a-zA-Z0-9_-]", "")+".val";
				BufferedWriter buffferwriter = new BufferedWriter( 
		        new FileWriter(path + name, true)); 
		        buffferwriter.write(url123 + " " + value); 
		        buffferwriter.close(); 
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void startCrawl(String urlValue)
	{
		webCrawler(urlValue);
		convertHtmlToText();
	}

}
