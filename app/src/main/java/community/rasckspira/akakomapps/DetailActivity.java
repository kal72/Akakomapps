package community.rasckspira.akakomapps;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleBar;
    private TextView desJudul;
    private TextView detail;
    private Button goLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intens = getIntent();
        Bundle a = intens.getExtras();
        String mjudul = a.getString("mJudul");
        String mdesJudul = a.getString("mdesJudul");
        String mDetail = a.getString("mDetail");
        final String mLink = a.getString("mLink");

        desJudul = (TextView) findViewById(R.id.detail_judul);
        detail = (TextView) findViewById(R.id.detail_detail);
        titleBar = (TextView) findViewById(R.id.toolbar_title);
        goLink = (Button) findViewById(R.id.btn_link);
        goLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mLink));
                startActivity(intent);
            }
        });


        titleBar.setText(mjudul.toString());
        desJudul.setText(mdesJudul.toString());
        detail.setText(mDetail.toString());




    }
}
