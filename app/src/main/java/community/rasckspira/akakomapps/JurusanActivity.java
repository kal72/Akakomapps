package community.rasckspira.akakomapps;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import community.rasckspira.akakomapps.adapter.RecyclerAdapter;
import community.rasckspira.akakomapps.helper.AppController;
import community.rasckspira.akakomapps.helper.Config;
import community.rasckspira.akakomapps.model.Data;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;

public class JurusanActivity extends AppCompatActivity {
    private LinearLayout activity;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;
    private List<Data> feedItemList = new ArrayList<Data>();
    private String urlJurusan;
    public LinearLayout ll;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurusan);
        initView();
        getDataJson();
    }

    private void initView(){
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

        mLayoutManager = new LinearLayoutManager(this);
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.setVisibility(View.VISIBLE);

        activity = (LinearLayout) findViewById(R.id.activity_jurusan);
        feedItemList = new ArrayList<Data>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        urlJurusan = Config.URL_JURUSAN;
    }

    public void getDataJson() {
        final JsonArrayRequest request = new JsonArrayRequest(urlJurusan,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println(response.toString());
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Data item = new Data();
                                item.setNama(jsonObject.getString("namJurusan"));
                                item.setDetail(jsonObject.getString("detJurusan"));
                                item.setLink(jsonObject.getString("link"));
                                feedItemList.add(item);

                            }
                            ll.setVisibility(View.GONE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mAdapter = new RecyclerAdapter(JurusanActivity.this, feedItemList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        final Snackbar snackbar = Snackbar.make(activity, "Koneksi bermasalah...", LENGTH_INDEFINITE);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                                getDataJson();
                            }
                        });
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.WHITE);
                        snackbar.show();
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 100, 1, 1.0f));
        AppController.getInstance().addToRequestQueue(request);
    }
}
