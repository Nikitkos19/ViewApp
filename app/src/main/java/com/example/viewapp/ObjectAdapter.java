package com.example.viewapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewHolder> {

    private List<ObjectItem> objectList;
    private Context context;
    private final List<ObjectItem> allItems;
    private final List<ObjectItem> displayItems;
    public void filterByCategory(String category) {
        displayItems.clear();
        if (category == null || category.equals("Все") || category.isEmpty()) {
            displayItems.addAll(allItems);
        } else {
            for (ObjectItem item : allItems) {
                if (category.equals(item.category)) {
                    displayItems.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public ObjectAdapter(Context context, List<ObjectItem> objectList) {
        this.context = context;
        this.objectList = objectList;
        this.allItems = new ArrayList<>(objectList);
        this.displayItems = new ArrayList<>(objectList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, categoryView;
        Button downloadButton;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            categoryView = itemView.findViewById(R.id.categoryView);
            downloadButton = itemView.findViewById(R.id.buttonDownload);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        ObjectItem item = objectList.get(position);
                        showExtendedDialog(item);
                    }
                }
            });
        }

        private void showExtendedDialog(ObjectItem item) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_extended, null);

            ImageView imageView = dialogView.findViewById(R.id.dialogImageView);
            TextView extendedTextView = dialogView.findViewById(R.id.extendedTextView);

            imageView.setImageResource(item.imageResId);
            extendedTextView.setText(item.extendedText);

            builder.setView(dialogView);
            builder.setPositiveButton("Закрыть", null);

            final AlertDialog dialog = builder.create();
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    if (dialog.getWindow() != null) {
                        dialog.getWindow().setWindowAnimations(R.style.DialogFadeAnimation);
                    }
                }
            });

            dialog.show();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_object, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ObjectItem item = displayItems.get(position);
        holder.imageView.setImageResource(item.imageResId);
        holder.nameTextView.setText(item.name);
        holder.categoryView.setText(item.category);
        holder.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Приложение " + item.name + " скачивается", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return displayItems.size();
    }
}