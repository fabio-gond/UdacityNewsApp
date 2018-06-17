package com.example.fabio.udacity_newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {
    /**
     * Constructs a new {@link ArticleAdapter}.
     *
     * @param context of the app
     * @param articles is the list of articles, which is the data source of the adapter
     */
    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }

    /**
     * Returns a list item view that displays information about the article at the given position
     * in the list of articles.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        // Find the article at the given position in the list of articles
        final Article currentArticle = getItem(position);


        // Find the TextView with view ID location
        TextView itemTitle = (TextView) listItemView.findViewById(R.id.item_title);
        // Display the location of the current article in that TextView
        itemTitle.setText(currentArticle.getTitle());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.item_date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(currentArticle.getDate());
        // Display the date of the current article in that TextView
        dateView.setText(formattedDate);

        TextView sectionView = listItemView.findViewById(R.id.item_section);
        sectionView.setText(currentArticle.getSection());

        ImageView imageView = listItemView.findViewById(R.id.item_image);
        RetrieveImageTask task = new RetrieveImageTask();
        task.execute(new MyTaskParams(currentArticle.getImgUrl() , imageView));

        listItemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(currentArticle.getUrl()));
                getContext().startActivity(i);
            }
        });

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(String date) {
        return date;
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private static class MyTaskParams {
        String imgUrl;
        ImageView imageView;

        public MyTaskParams(String imgUrl, ImageView imageView) {
            this.imgUrl = imgUrl;
            this.imageView = imageView;
        }
    }

    private class RetrieveImageTask extends AsyncTask<MyTaskParams, Void, Boolean> {

        private Exception exception;

        protected Boolean doInBackground(MyTaskParams... params) {
            try {
                ImageView imageView = params[0].imageView;
                imageView.setImageDrawable(Api.LoadImageFromWebOperations(params[0].imgUrl));
                return true;
            } catch (Exception e) {
                this.exception = e;
                return false;
            }
        }

        protected void onPostExecute() {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }

}
