package gottibujiku.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import gottibujiku.rssparser.Constant;

/**
 * This activity gives a detailed information about
 * a specific rss item that was clicked from the list
 * of rss items.
 * It gives the title,author,date of publication,link to the
 * attached image if there is one,link to the post itself
 * and the content of the post
 *
 * @author  Newton Bujiku
 * @since  July,2014
 */

public class DetailedActivity extends Activity {

    private TextView title,author,pubDate,postUrl,postImageUrl,description;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        //get the intent that started this activity
        intent = getIntent();

        //reference all the views
      title  =(TextView)findViewById(R.id.title_detailed);
       author =(TextView)findViewById(R.id.author_detailed);
       pubDate =(TextView)findViewById(R.id.pubDate_detailed);
       postUrl =(TextView)findViewById(R.id.post_url_detailed);
        postImageUrl=(TextView)findViewById(R.id.post_image_url_detailed);
       description =(TextView)findViewById(R.id.post_content_detailed);

        //get the information to display from the intent
        title.setText(intent.getStringExtra(Constant.TITLE));
        author.setText(intent.getStringExtra(Constant.AUTHOR));
        pubDate.setText(intent.getStringExtra(Constant.PUBLICATION_DATE));
        postImageUrl.setText(intent.getStringExtra(Constant.URL));
        postUrl.setText(intent.getStringExtra(Constant.POST_URL));
        description.setText(Html.fromHtml(intent.getStringExtra(Constant.DESCRIPTION)));




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detailed, menu);
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
}
