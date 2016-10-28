package community.rasckspira.akakomapps.adapter;

/**
 * Created by kristiawan on 09/12/15.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import community.rasckspira.akakomapps.model.Data;
import community.rasckspira.akakomapps.DetailActivity;
import community.rasckspira.akakomapps.R;

/**
 * Created by dedevalen on 14/09/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    List<Data> mItems;
    Context mContext;

    public RecyclerAdapter(Context context, List<Data> mItems) {

        this.mItems = mItems;
        this.mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Data item = mItems.get(i);


        myViewHolder.judul.setText(item.getNama().toString());
        myViewHolder.aDetail = item.getDetail();
        myViewHolder.aLink = item.getLink();



    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView judul;
        public TextView desJudul;
        public String aDetail;
        public String aLink;
        public CardView cv;


        public MyViewHolder(View itemView) {
            super(itemView);

            //robotobold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Bold.ttf");
            //robotoregular = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf");


            //this.img = (ImageView)itemView.findViewById(R.id.img);
            this.judul = (TextView) itemView.findViewById(R.id.person_name);
            this.desJudul = (TextView) itemView.findViewById(R.id.judul_des);
            this.cv = (CardView) itemView.findViewById(R.id.cv);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Context Mcontext = itemView.getContext();
            Intent intent = new Intent(Mcontext, DetailActivity.class);
            intent.putExtra("mJudul", judul.getText());
            intent.putExtra("mdesJudul", desJudul.getText());
            intent.putExtra("mDetail", aDetail);
            intent.putExtra("mLink", aLink);
            Mcontext.startActivity(intent);

        }
    }
}