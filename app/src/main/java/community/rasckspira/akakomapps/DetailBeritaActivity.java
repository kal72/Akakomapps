package community.rasckspira.akakomapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailBeritaActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView titleBar;
    private TextView judul;
    private TextView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intens = getIntent();
        Bundle a = intens.getExtras();
        String mjudul = a.getString("mJudul");
        String mDetail = a.getString("mDetail");

        judul = (TextView) findViewById(R.id.berita_judul);
        detail = (TextView) findViewById(R.id.berita_detail);
        titleBar = (TextView) findViewById(R.id.toolbar_title);

        titleBar.setText("Berita");
        judul.setText(mjudul.toString());
        detail.setText(mDetail.toString());

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
