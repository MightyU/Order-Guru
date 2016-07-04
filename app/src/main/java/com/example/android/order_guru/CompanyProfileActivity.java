package com.example.android.order_guru;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class CompanyProfileActivity extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    String noCompanylogo;
    String[] companyProfile = new String[3];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);
    }

    public void load_company_logo (View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                // Set the Image in ImageView after decoding the String
                ImageView imgView = (ImageView) findViewById(R.id.image_view_company_logo);
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

            }

            else {
                noCompanylogo = "no image";
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        }

        catch (Exception e) {
            noCompanylogo = "no image";
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

    public void next (View view) {

        EditText companyNameEditText = (EditText) findViewById(R.id.edit_company_name);
        EditText emailAddressEditText = (EditText) findViewById(R.id.edit_email_address);
        EditText phoneNumberEditText = (EditText) findViewById(R.id.edit_phone_number);
        //ImageView imgView = (ImageView) findViewById(R.id.image_view_company_logo);


        companyProfile[0]= companyNameEditText.getText().toString();
        companyProfile[1]= emailAddressEditText.getText().toString();
        companyProfile[2]= phoneNumberEditText.getText().toString();

        if (companyNameEditText.getText().toString().matches("")) {

            Toast.makeText(this, "fill in company name", Toast.LENGTH_LONG).show();
            companyNameEditText.setHintTextColor(Color.RED);

        }
        else if (emailAddressEditText.getText().toString().matches ("")){

            Toast.makeText(this, "fill in company's email address", Toast.LENGTH_LONG).show();
            emailAddressEditText.setHintTextColor(Color.RED);
        }
        else if (phoneNumberEditText.getText().toString().matches ("")){

            Toast.makeText(this, "fill in company's phone number", Toast.LENGTH_LONG).show();
            emailAddressEditText.setHintTextColor(Color.RED);
        }
        else {

                /*Bitmap companyLogoBitmap = imgView.getDrawingCache();
                ByteArrayOutputStream cmpLogo = new ByteArrayOutputStream();
                companyLogoBitmap.compress(Bitmap.CompressFormat.PNG,100,cmpLogo);
                byte[] companyLogoByteArray = cmpLogo.toByteArray();*/
                Intent nextIntent = new Intent(CompanyProfileActivity.this, ProductDescriptionActivity.class);
                nextIntent.putExtra("companyProfile",companyProfile);
                //nextIntent.putExtra("companyLogo",companyLogoByteArray);
                startActivity(nextIntent);
            }

        }

    }

