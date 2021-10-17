package intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.helloandroid.R;

public class MainIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_intent);
    }
    public void clicked1(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("http://www.google.com"));
        startActivity(i);
    }
    public void clicked2(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse("tel:00401213456"));
        startActivity(i);
    }
    public void clicked3(View view) {
        Intent i = new Intent("MSA.LAUNCH");
        i.setData(Uri.parse("http://www.google.com"));
        startActivity(i);
    }
    public void clicked4(View view) {
        Intent i = new Intent("MSA.LAUNCH");
        i.setData(Uri.parse("tel:00401213456"));
        startActivity(i);
    }
}