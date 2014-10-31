package gottibujiku.rssparser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


/**
 * This class initiates and handles the whole process
 * of parsing an XML until the required items are returned
 * To use it create an its instance passing a url to
 * the RSS Feed as a parameter to the constructor
 *
 * @author Newton Bujiku
 * @since July,2014
 */
public class RSSFeedReader {

    private String link;//a url to the

    public  RSSFeedReader (String link){

        this.link = link;

    }

    public ArrayList<RSSFeedItem> getRSSFeedItems(){


        SAXParserFactory saxParserFactory=null;
        SAXParser saxParser=null;

        //create a url object from the string url
        URL url = null;
        RSSDefaultHandler handler = null;

        try {
            //create an instance of the SAXParserFactory
            saxParserFactory= SAXParserFactory.newInstance();
            url  = new URL(link);

            //create a parser from the SAXParserFactory instance

             saxParser = saxParserFactory.newSAXParser();

            //instantiate the handler whose methods
            //will be invoked by the parser
            //during parsing
             handler = new RSSDefaultHandler();

            
            //Start parsing.
            
            saxParser.parse(url.openStream(),handler);

        } catch (MalformedURLException  e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }

        //return all the items after parsing is done
       return handler.getFeedItems();

    }

}
