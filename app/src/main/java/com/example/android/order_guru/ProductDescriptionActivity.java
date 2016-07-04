package com.example.android.order_guru;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDescriptionActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecString;
    String[] subCategory = new String[5];
    String [] addProduct = new String [5];
    int i = -1;
    String clear = "";
    int nclick = 0;
    int n = -1;
    int iclick = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);
    }

    public void load_product_image(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecString = cursor.getString(columnIndex);
                cursor.close();

                // Set the Image in ImageView after decoding the String
                ImageView imgView = (ImageView) findViewById(R.id.image_view_product_image);
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecString));

            } else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }


    public void addSubCategory (View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        TextView addSubcategoryInfo = (TextView) findViewById(R.id.text_view_sub_categories_info);
        TextView addSubcategoryInfo2 = (TextView) findViewById(R.id.text_view_sub_categories_info2);
        EditText addSubcategoryName = (EditText) findViewById(R.id.edit_sub_category_name);
        Button addSubcategoryButton = (Button) findViewById(R.id.button_add_subcategory);


        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_button_no:
                if (checked)
                    addSubcategoryInfo.setVisibility(View.GONE);
                    addSubcategoryInfo2.setVisibility(View.GONE);
                    addSubcategoryName.setVisibility(View.GONE);
                    addSubcategoryButton.setVisibility(View.GONE);
                    break;
            case R.id.radio_button_yes:
                if (checked)
                    addSubcategoryInfo.setVisibility(View.VISIBLE);
                    addSubcategoryInfo2.setVisibility(View.VISIBLE);
                    addSubcategoryName.setVisibility(View.VISIBLE);
                    addSubcategoryButton.setVisibility(View.VISIBLE);
                    break;
        }
    }


    public void addSubCategoryButton (View view){

        i += 1;
        nclick += 1;
        EditText subCategoryEditText = (EditText) findViewById(R.id.edit_sub_category_name);
        Button addSubcategoryButton = (Button) findViewById(R.id.button_add_subcategory);


        if (nclick < 6){
            int subCbuttonText = nclick + 1;
            subCategory[i] = subCategoryEditText.getText().toString();
            subCategoryEditText.setText(clear);
            if (subCbuttonText < 6) {
                addSubcategoryButton.setText("add subcategory"+ "" + subCbuttonText + "" + "to Product");
            }
            else {
                addSubcategoryButton.setText("Can not add anymore");
            }

        }
        else{

            Toast.makeText(this, "You cannot add more than five subcategories ", Toast.LENGTH_LONG).show();
            addSubcategoryButton.setEnabled(false);

        }

    }

    public void addProduct (View view){

        n += 1;
        iclick += 1;
        EditText productNameEditText = (EditText) findViewById(R.id.edit_text_product_name);
        Button addProductButton = (Button) findViewById(R.id.button_add_product);


        if (iclick < 6){
            int subCbuttonText = iclick + 1;
            addProduct[n] = productNameEditText.getText().toString();
            productNameEditText.setText(clear);
            if (subCbuttonText < 6) {
                addProductButton.setText("add Product" + "" + subCbuttonText);
            }
            else {
                addProductButton.setText("Can not add anymore");
            }

        }
        else{

            Toast.makeText(this, "You cannot add more than five Products ", Toast.LENGTH_LONG).show();
            addProductButton.setEnabled(false);

        }

    }

    public void previous1(View view) {


        Intent previous1Intent = new Intent(ProductDescriptionActivity.this, CompanyProfileActivity.class);
        startActivity(previous1Intent);


   }




}

