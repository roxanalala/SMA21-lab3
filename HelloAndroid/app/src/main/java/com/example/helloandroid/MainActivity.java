package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import lifecycle.ActivityA;
import lifecycle.ActivityB;
import lifecycle.ActivityC;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

     private AlertDialog.Builder dialogBuilder;
     private AlertDialog dialog;
     TextView tName,tPop;
     EditText eName;
     Button btn1, btn2, bClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.colors,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    @SuppressLint("SetTextI18n")
    public void onClick(View view) {

        tName = (TextView) findViewById(R.id.tName);
        eName = (EditText) findViewById(R.id.eName);
        tName.setText("Hello, " + eName.getText()+"!");

        createPopUp();


    }
    public void onClickShare(View view){
        String name = ((EditText) findViewById(R.id.eName)).getText().toString();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, name );
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }
    public void onClickSearch(View view){
        String text = ((EditText) findViewById(R.id.eName)).getText().toString();
        String url = "https://www.google.com/search?q="+text;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    @SuppressLint("SetTextI18n")
    public void createPopUp(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popUp = getLayoutInflater().inflate(R.layout.popup,null);

        tPop = (TextView) popUp.findViewById(R.id.tPop);
        tPop.setText("Hello, "+ eName.getText() +"!");

        btn1= (Button) popUp.findViewById(R.id.btn1);
        btn2 = (Button) popUp.findViewById(R.id.btn2);

        dialogBuilder.setView(popUp);
        dialog = dialogBuilder.create();
        dialog.show();



    }
    public void displayToast(View v){
        Toast.makeText(this,"Ai ales butonul 1!",Toast.LENGTH_SHORT).show();
    }
    public void displayToast2(View v){
        Toast.makeText(this,"Ai ales butonul 2!",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        bClick = (Button) findViewById(R.id.bClick);

        //Toast.makeText(this,text,Toast.LENGTH_SHORT ).show();
        switch (text) {
            case "Red":
                bClick.setTextColor(Color.RED);

                break;
            case "Blue":
                bClick.setTextColor(Color.BLUE);

                break;
            case "Pink":
                bClick.setTextColor(Color.MAGENTA);

                break;
            case "Green":
                bClick.setTextColor(Color.GREEN);

                break;
            default:
                bClick.setTextColor(Color.BLACK);
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}