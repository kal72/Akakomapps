package community.rasckspira.akakomapps.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import community.rasckspira.akakomapps.Data;
import community.rasckspira.akakomapps.R;
import community.rasckspira.akakomapps.RecyclerAdapter;


public class Content2Fragment extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerAdapter mAdapter;
    private List<Data> feedItemList = new ArrayList<Data>();

    @Nullable

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_content2, container, false);

        feedItemList = new ArrayList<Data>();
        Data item = new Data();

        item.setJudul("Info terkini");
        feedItemList.add(item);
        item.setJudul("Info terkini");
        feedItemList.add(item);
        item.setJudul("Info terkini");
        feedItemList.add(item);
        item.setJudul("Info terkini");
        feedItemList.add(item);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecyclerAdapter(getActivity(), feedItemList);
        mRecyclerView.setAdapter(mAdapter);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


}
