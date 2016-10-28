package community.rasckspira.akakomapps.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import community.rasckspira.akakomapps.helper.AppController;
import community.rasckspira.akakomapps.adapter.BeritaAdapter;
import community.rasckspira.akakomapps.helper.Config;
import community.rasckspira.akakomapps.model.Data;
import community.rasckspira.akakomapps.R;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private BeritaAdapter mAdapter;
    private List<Data> feedItemList = new ArrayList<Data>();
    private String urls;
    public LinearLayout ll;

    @Nullable

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_berita, container, false);

        initView(v);
        getDataJson(v);
        return v;
    }

    private void initView(View v){
        ll = (LinearLayout) v.findViewById(R.id.ll);
        ll.setVisibility(View.VISIBLE);

        feedItemList = new ArrayList<Data>();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_berita);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        urls = Config.URL_BERITA;
    }

    public void getDataJson(final View view) {
        final JsonArrayRequest request = new JsonArrayRequest(urls,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println(response.toString());
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Data item = new Data();
                                item.setJudul(jsonObject.getString("jBerita"));
                                item.setWaktu(jsonObject.getString("wBerita"));
                                item.setFoto(jsonObject.getString("gamBerita"));
                                item.setDetail(jsonObject.getString("isBerita"));
//                                item.setJudul(deskripsi.substring(0, 100));
//                                item.setDetail(deskripsi);
                                feedItemList.add(item);

                            }
                            ll.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mAdapter = new BeritaAdapter(getActivity(), feedItemList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        final Snackbar snackbar = Snackbar.make(view.getRootView(), "Koneksi bermasalah...", LENGTH_INDEFINITE);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                snackbar.dismiss();
                                getDataJson(view);
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
