package community.rasckspira.akakomapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JurusanDetailActivity extends AppCompatActivity {
    public static final String KEY_NAMA = "nama";
    public static final String KEY_DESKRIPSI = "deskripsi";
    public static final String KEY_LINK = "link";
    private TextView judul, deskripsi;
    private Button btnMore;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurusan_detail);
        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getIntent().getExtras().getString(KEY_NAMA));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        initView();
    }

    private void initView(){
        judul = (TextView) findViewById(R.id.judul_jurusan);
        deskripsi = (TextView) findViewById(R.id.deskripsi_jurusan);
        btnMore = (Button) findViewById(R.id.btn_more_jurusan);

        judul.setText(getIntent().getExtras().getString(KEY_NAMA));
        deskripsi.setText(getIntent().getExtras().getString(KEY_DESKRIPSI));

        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(JurusanDetailActivity.this, WebViewActivity.class);
                i.putExtra(WebViewActivity.KEY_HEADER, judul.getText());
                i.putExtra(WebViewActivity.KEY_URL, getIntent().getExtras().getString(KEY_LINK));
                startActivity(i);
            }
        });
    }
}
