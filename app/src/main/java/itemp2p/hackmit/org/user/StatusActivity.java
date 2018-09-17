package itemp2p.hackmit.org.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        int time_remaining = (int) intent.getIntExtra("TIME", -1);
        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String timeString = "Time Remaining:";
                //df.format(time_remaining);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_layout);
        ((TextView) findViewById(R.id.time_remaining)).setText(timeString);
        ((TextView) findViewById(R.id.minutes)).setText(time_remaining + " minutes");
    }

    public void cancelButton() {

        WebService.implementation.submitCancel(getSharedPreferences("prefs", Context.MODE_PRIVATE).getInt("requestid", -1)).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });

    }
}
