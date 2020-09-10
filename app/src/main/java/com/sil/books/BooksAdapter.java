package com.sil.books;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder>{

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvAuthors;
        TextView tvDate;
        TextView tvPublisher;


        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAuthors = (TextView) itemView.findViewById(R.id.tvAuthors);
            tvDate = (TextView) itemView.findViewById(R.id.tvPublisherDate);
            tvPublisher = (TextView) itemView.findViewById(R.id.tvPublisher);
        }

        public void bind (Book book){
            tvTitle.setText(book.title);
            String authors = "";
            int i = 0;
            for (String author : book.authors){
                authors += author;
                i++;
                if (i<book.authors.length){
                    authors+= ", ";
                }

            }
            tvAuthors.setText(authors);
            tvDate.setText(book.publishedDate);
            tvPublisher.setText(book.publisher);
        }
    }
}
