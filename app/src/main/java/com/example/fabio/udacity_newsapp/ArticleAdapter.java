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
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_list_item, parent, false);
        }

        final Article currentArticle = getItem(position);

        TextView itemTitle = listItemView.findViewById(R.id.item_title);
        itemTitle.setText(currentArticle.getTitle());

        TextView dateTextView = listItemView.findViewById(R.id.item_date);
        String formattedDate = formatDate(currentArticle.getDate());
        dateTextView.setText(formattedDate);

        TextView sectionView = listItemView.findViewById(R.id.item_sectionAndAuthor);
        sectionView.setText(currentArticle.getSection() + " - By " + currentArticle.getAuthNames());

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

        return listItemView;
    }

    /**
     * Return the formatted date string from the article date string .
     */
    private String formatDate(String dateString) {
        String[] dateArray = dateString.split("T")[0].split("-");
        String[] timeArray = dateString.split("T")[1].split("Z")[0].split(":");

        return timeArray[0] +":" +timeArray[1] + " - " + dateArray[2] + "/" + dateArray[1] +"/" + dateArray[0];
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

        }
    }

}
