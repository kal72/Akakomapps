package community.rasckspira.akakomapps.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import community.rasckspira.akakomapps.AppController;
import community.rasckspira.akakomapps.Data;
import community.rasckspira.akakomapps.R;
import community.rasckspira.akakomapps.RecyclerAdapter;


public class Content3Fragment extends Fragment {


    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerAdapter mAdapter;
    private List<Data> feedItemList = new ArrayList<Data>();
    private String urlJurusan = "http://service.rackspira.community/rest/rjson/jurusan.json";

    @Nullable

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content3, container, false);
        feedItemList = new ArrayList<Data>();
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);


        final JsonArrayRequest request = new JsonArrayRequest(urlJurusan,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        System.out.println(response.toString());
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                Data item = new Data();
                                item.setNama(jsonObject.getString("nama"));
                                item.setJudul(jsonObject.getString("judul"));
                                item.setDetail(jsonObject.getString("detail"));
                                item.setLink(jsonObject.getString("link"));
                                feedItemList.add(item);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mAdapter = new RecyclerAdapter(getActivity(), feedItemList);
                        mRecyclerView.setAdapter(mAdapter);
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                    }
                }
        );

        AppController.getInstance().addToRequestQueue(request);


        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}
