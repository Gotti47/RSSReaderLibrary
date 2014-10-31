package gottibujiku.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import gottibujiku.gottibujiku.adapters.CustomAdapter;
import gottibujiku.rssparser.Constant;
import gottibujiku.rssparser.RSSFeedItem;
import gottibujiku.rssparser.RSSFeedReader;

/**
 *  This class serves as the main screen that shows up when the application
 * is launched and after few seconds of being blank
 * it will display a list of rss items
 * showing the title,author,date of publication,and link
 *to the specific rss post
 *
 * @author  Newton Bujiku
 * @since  July,2014
 */
public class MyActivity extends Activity {

    private ListView listView;
    private ArrayList<RSSFeedItem> feedItems;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        listView = (ListView) findViewById(R.id.list_view);
        feedItems = new ArrayList<RSSFeedItem>();

        //Instantiate the RSSFeedReader Object and pass the url to the rss feed
        final RSSFeedReader rssFeedReader =
                new RSSFeedReader("http://feeds.bbci.co.uk/news/rss.xml?edition=int");

        // instantiate the adapter
        //and set it to a ListView which won't display anything
        //since there is no data yet to be displayed
        adapter = new CustomAdapter(getApplicationContext(), R.layout.list_item, feedItems);
        listView.setAdapter(adapter);

        Thread thread = new Thread(new Runnable() {

            //Create an instance of the worker thread that will
            //run in the background
            //Android forbids doing network operations in the UI thread
            @Override
            public void run() {
                //the RSSFeedReader.getRSSFeedItems() returns an ArrayList
                //of RSS Feed items after parsing.
                //This method is the one that does network operations
                feedItems = rssFeedReader.getRSSFeedItems();
                Log.d("Thread ", "Is Running");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Here we pass the returned items to the adapter and
                        //tell is to notify our ListView that there is
                        //data to be displayed
                        adapter.addAll(feedItems);
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });
        thread.start();//starts the thread


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //get the item that was clicked from the ListView
                RSSFeedItem feedItem = (RSSFeedItem) parent.getItemAtPosition(position);
                //Create an explicit intent to start the DetailedViewActivity
                Intent intent = new Intent(getApplicationContext(), DetailedActivity.class);
                //Add the information we want to display to the intent
                intent.putExtra(Constant.TITLE, feedItem.getTitle());
                intent.putExtra(Constant.AUTHOR, feedItem.getAuthor());
                intent.putExtra(Constant.PUBLICATION_DATE, feedItem.getPubDate());
                intent.putExtra(Constant.DESCRIPTION, feedItem.getDescription());
                intent.putExtra(Constant.POST_URL, feedItem.getPostUrl());
                intent.putExtra(Constant.URL, feedItem.getImageUrl());
                //start the DetailedViewActivity
                startActivity(intent);


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }
}

