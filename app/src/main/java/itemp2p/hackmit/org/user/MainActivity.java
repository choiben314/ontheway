package itemp2p.hackmit.org.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.location.Geocoder;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Geocoder geocoder;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        geocoder = new Geocoder(this);
    }

    public void chooseFromAddress (View view) {
        ChooseLocationActivity.Companion.start(this,0);
    }

    public void chooseToAddress (View view) {
        ChooseLocationActivity.Companion.start(this, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        int diff = requestCode - ChooseLocationActivity.REQUEST_CODE;
        LatLng location;

        if(data != null && (diff == 0 || diff == 1))
            location = data.getParcelableExtra(ChooseLocationActivity.LOCATION_EXTRA);
        else return;


        String address = null;
        try {
            address = geocoder.getFromLocation(location.latitude, location.longitude, 1).get(0).getAddressLine(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (diff <= 0) {
            ((TextView) findViewById(R.id.editTextFrom)).setText(address);
        } else {
            ((TextView) findViewById(R.id.toAddress)).setText(address);
            //calculateDistance();
        }
    }

    public void pressOk (View view) {
        final Intent intent = new Intent(this, DriversFound.class);

        String toAddress = ((EditText) findViewById(R.id.toAddress)).getText().toString();

        Bundle extras = new Bundle();

        extras.putString("EXTRA_TO", "toAddress");

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);


//        WebService.implementation.submitRequest(toAddress, time).enqueue(
//                new Callback<Integer>() {
//                    @Override
//                    public void onResponse(Call<Integer> call, Response<Integer> response) {
//                         sharedPreferences.edit().putInt("requestid", response.body()).commit();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Integer> call, Throwable t) {
//
//                    }
//                }
//        );


//        if (!itemNeeded.isEmpty() && !fromAddress.isEmpty() && !toAddress.isEmpty() && !time.isEmpty()) {
//            intent.putExtras(extras);
//            startActivity(intent);
//        }
        intent.putExtras(extras);

        setContentView(R.layout.loading);

        Glide.with(getApplicationContext()).load(R.mipmap.loading).into((ImageView) findViewById(R.id.loading_gif));
        new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                setContentView(R.layout.activity_main);
                startActivity(intent);
            }
        }.start();
    }
}
