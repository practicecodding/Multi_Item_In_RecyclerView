package com.hamidul.multipleitemsinrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<HashMap<String ,String >> arrayList = new ArrayList<>();
    HashMap<String,String> hashMap;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        hashMap = new HashMap<>();
        hashMap.put("type","book");
        hashMap.put("name","This is book 1");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","image");
        hashMap.put("name","This is image 1");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","text");
        hashMap.put("text","This is text 1");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","book");
        hashMap.put("name","This is book 2");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","text");
        hashMap.put("text","This is text 2");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("type","image");
        hashMap.put("name","This is image 2");
        arrayList.add(hashMap);

        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    public class MyAdapter extends RecyclerView.Adapter{
        int BOOK = 0;
        int IMAGE = 1;
        int TEXT = 2;
        public class bookViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            ImageView imageView;
            public bookViewHolder(@NonNull View itemView) {
                super(itemView);

                textView = itemView.findViewById(R.id.textView);
                imageView = itemView.findViewById(R.id.imageView);

            }
        }

        public class imageViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView textView;
            public imageViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);

            }
        }

        public class textViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            public textViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();

            if (viewType==BOOK){
                View myView = layoutInflater.inflate(R.layout.item_book,parent,false);
                return new bookViewHolder(myView);
            }
            else if (viewType==IMAGE){
                View myView = layoutInflater.inflate(R.layout.item_image,parent,false);
                return new imageViewHolder(myView);
            }
            else {
                View myView = layoutInflater.inflate(R.layout.item_text,parent,false);
                return new textViewHolder(myView);
            }


        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            if (getItemViewType(position)==BOOK){

                bookViewHolder myHolder = (bookViewHolder) holder;

                hashMap = arrayList.get(position);

                String type = hashMap.get("type");
                String name = hashMap.get("name");

                myHolder.textView.setText(name);

                myHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toastMessage(type);
                    }
                });

            }
            else if (getItemViewType(position) == IMAGE){

                imageViewHolder myHolder = (imageViewHolder) holder;

                hashMap = arrayList.get(position);

                String type = hashMap.get("type");
                String name = hashMap.get("name");

                myHolder.textView.setText(name);

                myHolder.imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toastMessage(type);
                    }
                });

            }
            else {

                textViewHolder myHolder = (textViewHolder) holder;

                hashMap = arrayList.get(position);

                String type = hashMap.get("type");
                String text = hashMap.get("text");

                myHolder.textView.setText(text);

                myHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toastMessage(type);
                    }
                });

            }

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        @Override
        public int getItemViewType(int position) {
            hashMap = arrayList.get(position);
            String type = hashMap.get("type");

            if (type.contains("book")) return BOOK;
            else if (type.contains("image"))return IMAGE;
            else return TEXT;

        }
    }

    private void toastMessage (String text){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG);
        toast.show();
    }


}