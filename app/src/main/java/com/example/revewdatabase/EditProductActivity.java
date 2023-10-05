package com.example.revewdatabase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.revewdatabase.databinding.ActivityEditProductBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.util.ArrayList;

public class EditProductActivity extends AppCompatActivity {

    ActivityEditProductBinding binding;
    ArrayList<Item> items=new ArrayList<>();
    static String newiamgeUri="";
    DataBase dataBase=new DataBase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        items=dataBase.getDataInDataBaseInProduct();

        String index=getIntent().getStringExtra("index");
        String name=getIntent().getStringExtra("nameProduct");

        if (items.size()>0){
            for (Item item:items){
                if (item.getName().equals(name)){
                    binding.nameEditEd.setText(item.getName());
                    binding.categuryEditEd.setText(item.getCategory());
                    binding.priceEditEd.setText(item.getPrice());
                    Glide.with(this).load(item.getImage()).into(binding.iamgeEditProduct);

                    binding.iamgeEditProduct.setOnClickListener(v -> {
                        ImagePicker.with(this)
                                .crop()	    			//Crop image(Optional), Check Customization for more option
                                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                                .start();
                    });

                    binding.edit.setOnClickListener(v -> {
                        String newName=binding.nameEditEd.getText().toString();
                        String newcategory=binding.categuryEditEd.getText().toString();
                        String newprice=binding.priceEditEd.getText().toString();

                        Item item1=new Item(item.getId(),newName,newcategory,newprice,newiamgeUri);
                        dataBase.editQuestion(item.getId(),item1);
                        Toast.makeText(this, "Done Edit Item", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent();
                        setResult(100,intent);
                        finish();
                    });
                }
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        newiamgeUri="";
        Uri uri=data.getData();
        newiamgeUri=uri.toString();
        if (newiamgeUri.length()!=0) {
            binding.iamgeEditProduct.setImageURI(uri);
        }
    }
}