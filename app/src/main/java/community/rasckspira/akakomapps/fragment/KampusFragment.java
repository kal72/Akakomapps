package community.rasckspira.akakomapps.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import community.rasckspira.akakomapps.helper.Config;
import community.rasckspira.akakomapps.model.Data;
import community.rasckspira.akakomapps.adapter.InfokampusAdapter;
import community.rasckspira.akakomapps.R;

import static android.support.design.widget.Snackbar.LENGTH_INDEFINITE;

/**
 * A simple {@link Fragment} subclass.
 */
public class KampusFragment extends Fragment {


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    InfokampusAdapter mAdapter;
    private List<Data> feedItemList = new ArrayList<Data>();
    private String urls;
    public LinearLayout ll, noInternet;

    @Nullable

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_kampus, container, false);

        initView(v);
        getDataJson(v);

        return v;
    }

    private void initView(View v){
        ll = (LinearLayout) v.findViewById(R.id.ll);
        ll.setVisibility(View.VISIBLE);
        noInternet = (LinearLayout) v.findViewById(R.id.ll_nointernet);
        feedItemList = new ArrayList<Data>();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_kampus);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        urls = Config.URL_INFO;
        noInternet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataJson(v);
            }
        });
    }

    public void getDataJson(final View view) {
        ll.setVisibility(View.VISIBLE);
        noInternet.setVisibility(View.GONE);
        final JsonArrayRequest request = new JsonArrayRequest(urls,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println(response.toString());
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Data item = new Data();
                                item.setJudul(jsonObject.getString("jInfo"));
                                item.setWaktu(jsonObject.getString("wInfo"));
                                item.setDetail(jsonObject.getString("isInfo"));
                                item.setFoto(jsonObject.getString("gamInfo"));
                                feedItemList.add(item);

                            }
                            ll.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            ll.setVisibility(View.GONE);
                            noInternet.setVisibility(View.VISIBLE);
                        }
                        mAdapter = new InfokampusAdapter(getActivity(), feedItemList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        Log.i("TAG", "onErrorResponse: "+error.toString());
                        /*final Snackbar snackbar = Snackbar.make(view.getRootView(), "Koneksi bermasalah...", LENGTH_INDEFINITE);
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
                        snackbar.show();*/
                        ll.setVisibility(View.GONE);
                        noInternet.setVisibility(View.VISIBLE);
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(10 * 100, 1, 1.0f));
        AppController.getInstance().addToRequestQueue(request);
    }

}
