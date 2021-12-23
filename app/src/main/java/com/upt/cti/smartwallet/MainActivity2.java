package com.upt.cti.smartwallet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.upt.cti.model.Payment;
import com.upt.cti.ui.PaymentAdapter;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TextView tStatus;
    private Button bPrevious,bNext;
    private FloatingActionButton fabAdd;
    private ListView listPayments;
    private DatabaseReference databaseReference;
    private int currentMonth;
    private List<Payment> payments = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private static final String TAG_MONTH = "";
    private final static String PREFERENCES_SETTINGS = "prefs_settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tStatus = findViewById(R.id.tStatus);
        bPrevious =  findViewById(R.id.bPrevious);
        bNext =  findViewById(R.id.bNext);
        fabAdd = findViewById(R.id.fabAdd);
        listPayments = findViewById(R.id.listPayments);
        final PaymentAdapter adapter = new PaymentAdapter(this, R.layout.item_payment, payments);
        listPayments.setAdapter(adapter);

        listPayments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AppState.get().setCurrentPayment(payments.get(i));
                startActivity(new Intent(getApplicationContext(), AddPaymentActivity.class));
            }
        });

        sharedPreferences =  getSharedPreferences(PREFERENCES_SETTINGS, Context.MODE_PRIVATE);
        currentMonth = sharedPreferences.getInt(TAG_MONTH, -1);
        if (currentMonth == -1)
            currentMonth = Month.monthFromTimestamp(AppState.getCurrentTimeDate());

        // setup firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance("https://smart-wallet-3b5c7-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = database.getReference();

        databaseReference.child("wallet").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Payment newPayment = snapshot.getValue(Payment.class);
                if (newPayment != null) {
                    newPayment.timestamp = snapshot.getKey();
                    if (!payments.contains(newPayment))
                    {
                        payments.add(newPayment);
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void clicked(View view){
        switch(view.getId()){
            case R.id.fabAdd:
                AppState.get().setDatabaseReference(databaseReference);
                AppState.get().setCurrentPayment(null);
                startActivity(new Intent(this, AddPaymentActivity.class));
                break;
            case R.id.bNext:
                currentMonth++;
                if(currentMonth == 12) currentMonth = 0;
                sharedPreferences.edit().putInt(TAG_MONTH, currentMonth).apply();
                recreate();
                break;
            case R.id.bPrevious:
                currentMonth--;
                if(currentMonth == -1) currentMonth = 11;
                sharedPreferences.edit().putInt(TAG_MONTH, currentMonth).apply();
                recreate();
                break;
        }
    }

    public enum Month {
        January, February, March, April, May, June, July, August,
        September, October, November, December;

        public static int monthNameToInt(Month month) {
            return month.ordinal();
        }

        public static Month intToMonthName(int index) {
            return Month.values()[index];
        }

        public static int monthFromTimestamp(String timestamp) {
            // 2016-11-02 14:15:16
            int month = Integer.parseInt(timestamp.substring(5, 7));
            return month - 1;
        }
    }
}