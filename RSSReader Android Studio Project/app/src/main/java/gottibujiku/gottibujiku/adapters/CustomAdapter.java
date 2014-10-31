package gottibujiku.gottibujiku.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gottibujiku.rssparser.RSSFeedItem;
import gottibujiku.rssreader.R;

/**
 * A custom list view adapter to pupulate the data
 * for each feed item
 *
 * @author Newton Bujiku
 * @since  July,2014
 */
public class CustomAdapter extends ArrayAdapter<RSSFeedItem> {

    private Context context;// a context object
    private int resId;//resource id of a single feed item layout
    private ArrayList<RSSFeedItem> objects;

    public CustomAdapter(Context context, int resource, ArrayList<RSSFeedItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resId = resource;
        this.objects = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        View row = convertView;

        if (row == null) {//if row if null that means we don't have this
            //item already so we can't recycle
            //let's create an view for it

            //get a layout inflater from context
            LayoutInflater inflater = (LayoutInflater)LayoutInflater.from(context);
            //inflate the view
            row = inflater.inflate(resId, parent,false);

            holder = new ViewHolder();

            //make references to the views
            holder.title = (TextView) row.findViewById(R.id.title);
            holder.author = (TextView) row.findViewById(R.id.author);
            holder.pubDate = (TextView) row.findViewById(R.id.pubDate);
            holder.postUrl = (TextView) row.findViewById(R.id.post_url);

            row.setTag(holder);//set the tag

        } else {
            //if row is not null let's get the tag
            holder = (ViewHolder) row.getTag();


        }

        //get an object at this position
        RSSFeedItem item = (RSSFeedItem) objects.get(position);

        //set the content to the text views
        holder.title.setText(item.getTitle());
        holder.author.setText(item.getAuthor());
        holder.pubDate.setText(item.getPubDate());
        holder.postUrl.setText(item.getPostUrl());


        //return a view of a single item
        return row;


    }

     class ViewHolder {


         TextView title;
         TextView author;
         TextView pubDate;
         TextView postUrl;
    }

}
