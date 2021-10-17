package lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.helloandroid.R;

public class ActivityC extends AppCompatActivity {

    private static final String TAG = "lifecycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        setTitle("C");
        Log.d(TAG, "onCreate A");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ActivityC.TAG, "onResume C");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ActivityC.TAG, "onStart C");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ActivityC.TAG, "onPause C");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(ActivityC.TAG, "onStop C");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(ActivityC.TAG,"onRestart C");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ActivityC.TAG, "onDestroy C");
    }

    public void clickedC(View view) {
        switch (view.getId()) {
            case R.id.buttonA:
                startActivity(new Intent(this, ActivityA.class));
                break;
            case R.id.buttonB:
                startActivity(new Intent(this, ActivityB.class));
                break;
            case R.id.buttonC:
                startActivity(new Intent(this, ActivityC.class));
                break;
        }
    }
}