package itemp2p.hackmit.org.user;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriversFound extends AppCompatActivity{

    ArrayList<DriverOffer> myDataset= new ArrayList<DriverOffer>();

    DriverOffer.Driver d1 = new DriverOffer.Driver("Ronald", "@mipmap/ic_launcher");
    DriverOffer.Driver d2 = new DriverOffer.Driver("Dante", "@mipmap/ic_launcher");
    DriverOffer.Driver d3 = new DriverOffer.Driver("Andy", "@mipmap/ic_launcher");
    DriverOffer.Driver d4 = new DriverOffer.Driver("Tony", "@mipmap/ic_launcher");
    DriverOffer.Driver d5 = new DriverOffer.Driver("Laura", "@mipmap/ic_launcher");
    DriverOffer.Driver d6 = new DriverOffer.Driver("Sam", "@mipmap/ic_launcher");
    DriverOffer.Driver d7 = new DriverOffer.Driver("George", "@mipmap/ic_launcher");

    DriverOffer do1 = new DriverOffer(d1, 12, 5.43, 0001);
    DriverOffer do2 = new DriverOffer(d2, 31, 14.72, 0002);
    DriverOffer do3 = new DriverOffer(d3, 9, 2.39, 0003);
    DriverOffer do4 = new DriverOffer(d4, 5, 5.01, 0004);
    DriverOffer do5 = new DriverOffer(d5, 67, 9.08, 0005);
    DriverOffer do6 = new DriverOffer(d6, 2, 10.01, 0006);
    DriverOffer do7 = new DriverOffer(d7, 78, 2.02, 0007);

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drivers);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        myDataset.add(do1);
        myDataset.add(do2);
        myDataset.add(do3);
        myDataset.add(do4);
        myDataset.add(do5);
        myDataset.add(do6);
        myDataset.add(do7);

    }
}
