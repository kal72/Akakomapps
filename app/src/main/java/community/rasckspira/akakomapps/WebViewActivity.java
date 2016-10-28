package community.rasckspira.akakomapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WebViewActivity extends AppCompatActivity {
    public static final String KEY_HEADER = "header";
    public static final String KEY_URL = "url";
    private WebView web;
    private Toolbar toolbar;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        toolbar = (Toolbar) findViewById(R.id.tolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(getIntent().getExtras().getString(KEY_HEADER));
        title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(getIntent().getExtras().getString(KEY_HEADER));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        web = (WebView) findViewById(R.id.web_view);
        web.setWebViewClient(new LoadJurusanBrowser());

        loadUrl();
    }

    private class LoadJurusanBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void loadUrl(){
        String url = getIntent().getExtras().getString(KEY_URL);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        web.loadUrl(url);
    }
}
