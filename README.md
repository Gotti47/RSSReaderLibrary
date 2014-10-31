RSSReaderLibrary
================

RSS Reader Library for Android and Java

Implementation of the SAX XML parser. You can use this library in your projects when you need to parse XML.

This libabry can parse 6 tags namely title,description,pubDate,author, alink to the RSS post(postUrl) and imageUrl(if a post contains a link to an image).You can extend it to satisfy your needs by adding new items in RSSFeedItem,Constant and RSSDefaultHandler classes.

If you are cool with the current tags you can just get the ready packaged jars and just add them to your build path.

This library comes in two flavours; the Android specific that uses HttpURLConnection to make Http connections,the Java variant gets an InputStream directly from a URL object.

Usage
================
The Wiki has a simple explanation on how to use this library. Please make use of it.



