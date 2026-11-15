package com.example.androidlab1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 实验三 5：使用 RecyclerView + Adapter + ViewHolder 实现动物列表。
 * 列表项为卡片（CardView），包含图片与名称；点击显示名称，增加记录后列表立即刷新。
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private static final String[] ANIMAL_NAMES = {
            "Lion", "Tiger", "Monkey", "Dog", "Cat", "Elephant"
    };

    private static final int[] ANIMAL_IMAGES = {
            R.drawable.animal_lion,
            R.drawable.animal_tiger,
            R.drawable.animal_monkey,
            R.drawable.animal_dog,
            R.drawable.animal_cat,
            R.drawable.animal_elephant
    };

    private final List<Animal> animals = new ArrayList<>();
    private AnimalAdapter adapter;
    private RecyclerView recyclerView;
    private int nextId = 0;
    private int addedCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        for (int i = 0; i < ANIMAL_NAMES.length; i++) {
            animals.add(new Animal(nextId++, ANIMAL_NAMES[i], ANIMAL_IMAGES[i]));
        }

        recyclerView = findViewById(R.id.recycler_animals);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnimalAdapter(
                animals,
                getString(R.string.recycler_item_sub),
                animal -> Toast.makeText(this,
                        getString(R.string.recycler_clicked, animal.name),
                        Toast.LENGTH_SHORT).show());
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_add_record).setOnClickListener(v -> addRecord());
    }

    private void addRecord() {
        int index = addedCount % ANIMAL_NAMES.length;
        addedCount++;
        animals.add(new Animal(nextId++, ANIMAL_NAMES[index], ANIMAL_IMAGES[index]));
        adapter.notifyItemInserted(animals.size() - 1);
        recyclerView.scrollToPosition(animals.size() - 1);
    }

    static class Animal {
        final int id;
        final String name;
        final int imageRes;

        Animal(int id, String name, int imageRes) {
            this.id = id;
            this.name = name;
            this.imageRes = imageRes;
        }
    }

    interface OnAnimalClickListener {
        void onAnimalClick(Animal animal);
    }

    static class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalHolder> {

        private final List<Animal> data;
        private final String subFormat;
        private final OnAnimalClickListener listener;

        AnimalAdapter(List<Animal> data, String subFormat, OnAnimalClickListener listener) {
            this.data = data;
            this.subFormat = subFormat;
            this.listener = listener;
            setHasStableIds(true);
        }

        @Override
        public long getItemId(int position) {
            return data.get(position).id;
        }

        @NonNull
        @Override
        public AnimalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_animal_card, parent, false);
            return new AnimalHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AnimalHolder holder, int position) {
            Animal animal = data.get(position);
            holder.name.setText(animal.name);
            holder.sub.setText(String.format(subFormat, position + 1));
            holder.image.setImageResource(animal.imageRes);
            holder.itemView.setOnClickListener(v -> listener.onAnimalClick(animal));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        static class AnimalHolder extends RecyclerView.ViewHolder {
            final ImageView image;
            final TextView name;
            final TextView sub;

            AnimalHolder(@NonNull View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.iv_card_image);
                name = itemView.findViewById(R.id.tv_card_name);
                sub = itemView.findViewById(R.id.tv_card_sub);
            }
        }
    }
}
