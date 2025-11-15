package com.example.viewapp;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.ViewHolder> {

    private List<ObjectItem> objectList;
    private Context context;

    public ObjectAdapter(Context context, List<ObjectItem> objectList) {
        this.context = context;
        this.objectList = objectList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView, shortTextView;
        Button downloadButton; // добавляем кнопку

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            downloadButton = itemView.findViewById(R.id.buttonDownload); // ищем кнопку

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
            builder.setPositiveButton("Close", null);
            builder.show();
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_object, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ObjectItem item = objectList.get(position);
        holder.imageView.setImageResource(item.imageResId);
        holder.nameTextView.setText(item.name);

        // Устанавливаем обработчик для кнопки
        holder.downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Приложение " + item.name + " скачивается", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return objectList.size();
    }
}