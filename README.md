RSSReaderLibrary
================

RSS Reader Library for Android and Java

Implementation of the SAX XML parser. You can use this library in your projects when you need to parse XML.

This libabry can parse 6 tags namely title,description,pubDate,author, alink to the RSS post(postUrl) and imageUrl(if a post contains a link to an image).You can extend it to satisfy your needs by adding new items in RSSFeedItem,Constant and RSSDefaultHandler classes.

If you are cool with the current tags you can just get the ready packaged jars and just add them to your build path.

This library comes in two flavours; the Android specific that uses HttpURLConnection to make Http connections,the Java variant gets an InputStream directly from a URL object.

Usage
================
To use this library,create an instance of RSSFeedReader passing a link to your RSS feed in the constructor.
invoke the instance method RSSFeedReader.getRSSFeedItems() to get an ArrayList of RSSFeedItem.

  `
  RSSFeedReader reader = new RSSFeedReader("http://tomyrssfeed.xml");
  ArrayList<RSSFeedItem> items = reader.getRSSFeedItems();
  `

Use respective getters from RSSFeedItem to retrieve individual values of instance fields.
Use RSSFeedItem.tostring() instance method to get one String of all values.

Use the RSS items in a for loop like this 

  `
  for(RSSFeedItem item : items){
  System.out.println(item.toString());
  }
  `



