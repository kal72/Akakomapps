package community.rasckspira.akakomapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailBeritaActivity extends AppCompatActivity {

    public static final String KEY_JUDUL = "namaJurusan";
    public static final String KEY_TANGGAL = "tanggal";
    public static final String KEY_URL_FOTO = "url_photo";
    public static final String KEY_DESKRIPSI = "deskripsi";

    private Toolbar toolbar;
    private TextView titleBar;
    private TextView judul;
    private TextView detail, tanggal;
    private ImageView fotoDetailBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_berita);

        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        initView();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initView(){
        Intent intens = getIntent();
        Bundle a = intens.getExtras();
        String mjudul = a.getString(KEY_JUDUL);
        String mDetail = a.getString(KEY_DESKRIPSI);
        String mTanggal = a.getString(KEY_TANGGAL);
        String urlFoto = a.getString(KEY_URL_FOTO);

        judul = (TextView) findViewById(R.id.berita_judul);
        detail = (TextView) findViewById(R.id.berita_detail);
        titleBar = (TextView) findViewById(R.id.toolbar_title);
        fotoDetailBerita = (ImageView) findViewById(R.id.foto_detail);
        tanggal = (TextView) findViewById(R.id.tgl_detail_berita);

        titleBar.setText(getIntent().getExtras().getString("title"));
        judul.setText(mjudul.toString());
        detail.setText(mDetail.toString());
        tanggal.setText(mTanggal.toString());
        if (!urlFoto.equals(""))
            Glide.with(this).load(urlFoto).asBitmap().placeholder(R.drawable.placeholder).into(fotoDetailBerita);
        else
            fotoDetailBerita.setVisibility(View.GONE);

    }
}
