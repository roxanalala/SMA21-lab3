package intents;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.helloandroid.R;

public class IntentFilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_filter);

        TextView txtView = (TextView) findViewById(R.id.textView);
        Uri url = getIntent().getData();
        txtView.setText(url.toString());
    }
}