package com.example.revewdatabase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.revewdatabase.databinding.ActivityMainBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    static String iamgeUri="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DataBase dataBase=new DataBase(this);


        binding.add.setOnClickListener(v -> {
            String name=binding.nameEd.getText().toString();
            String category=binding.categuryEd.getText().toString();
            String price=binding.priceEd.getText().toString();
            int id=Utils.getIdItem();

            Item item=new Item(id,name,category,price,iamgeUri);

            dataBase.insertProductInDataBase(item);
            Toast.makeText(this, "Done Add Item", Toast.LENGTH_SHORT).show();
        });

        binding.view.setOnClickListener(v -> {
            startActivity(new Intent(this, ViewActivity.class));
        });

        binding.iamgeProduct.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        iamgeUri="";
        Uri uri=data.getData();
        iamgeUri=uri.toString();
        if (iamgeUri.length()!=0) {
            binding.iamgeProduct.setImageURI(uri);
        }
    }
}