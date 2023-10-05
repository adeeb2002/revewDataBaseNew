package com.example.revewdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.revewdatabase.databinding.ActivityViewBinding;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity implements Click{

    ArrayList<Item> items=new ArrayList<>();
    ActivityViewBinding binding;
    AdapterItem adapterItem;
    DataBase dataBase=new DataBase(this);

    ActivityResultLauncher<Intent> launcher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==100) {
                        if (result.getData() != null) {
                            adapterItem = new AdapterItem(items, ViewActivity.this);
                            binding.recycleItem.setAdapter(adapterItem);
                        }
                    }
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        items=dataBase.getDataInDataBaseInProduct();
        try {
             adapterItem=new AdapterItem(items, this);
            binding.recycleItem.setAdapter(adapterItem);
            binding.recycleItem.setLayoutManager(new LinearLayoutManager(this));
        }catch (Exception e){
            Toast.makeText(this, "Error Get Product", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onEdit(int index, String name) {
        Intent intent=new Intent(ViewActivity.this, EditProductActivity.class);
        intent.putExtra("index",index);
        intent.putExtra("nameProduct",name);
        launcher.launch(intent);
    }

    @Override
    public void onDelete(int index, int name) {
        dataBase.deleteQuestion(name);
        items=dataBase.getDataInDataBaseInProduct();
        adapterItem=new AdapterItem(items,this);
        binding.recycleItem.setAdapter(adapterItem);
        adapterItem.notifyItemChanged(index);
    }
}