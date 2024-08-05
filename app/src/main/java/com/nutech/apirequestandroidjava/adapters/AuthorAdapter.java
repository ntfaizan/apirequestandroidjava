package com.nutech.apirequestandroidjava.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nutech.apirequestandroidjava.R;
import com.nutech.apirequestandroidjava.models.Author;

import java.util.List;

public class AuthorAdapter  extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> {

    private List<Author> authorList;

    public AuthorAdapter(List<Author> authorList) {
        this.authorList = authorList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_authors, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Author author = authorList.get(position);
        holder.bind(author);
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public TextView txtId;
        public TextView txtCreatedAt;
        public TextView txtUpdatedAt;
        private ImageButton btnDelete;
        private ImageButton btnEdit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtId = itemView.findViewById(R.id.txtId);
            txtCreatedAt = itemView.findViewById(R.id.txtCreatedAt);
            txtUpdatedAt = itemView.findViewById(R.id.txtUpdatedAt);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnEdit = itemView.findViewById(R.id.btnEdit);
        }

        public void bind(Author author) {
            txtName.setText(author.getName());
            txtId.setText(String.valueOf(author.getId()));
            txtCreatedAt.setText(String.valueOf(author.getCreatedAt()));
            txtUpdatedAt.setText(author.getUpdatedAt());

            btnDelete.setOnClickListener(v -> {
            });
            btnEdit.setOnClickListener(v -> {
            });
        }

    }
}
