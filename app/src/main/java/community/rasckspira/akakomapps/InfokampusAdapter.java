package community.rasckspira.akakomapps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kristiawan on 12/12/15.
 */
public class InfokampusAdapter extends RecyclerView.Adapter<InfokampusAdapter.MyViewHolder> {


    List<Data> mItems;
    Context mContext;

    public InfokampusAdapter(Context context, List<Data> mItems) {

        this.mItems = mItems;
        this.mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kampus_view, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Data item = mItems.get(i);


        myViewHolder.judul.setText(item.getNama().toString());
        myViewHolder.desJudul.setText(item.getLink().toString());


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView judul;
        public TextView desJudul;
        public CardView cv;


        public MyViewHolder(View itemView) {
            super(itemView);

            //robotobold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Bold.ttf");
            //robotoregular = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf");


            //this.img = (ImageView)itemView.findViewById(R.id.img);
            this.judul = (TextView) itemView.findViewById(R.id.nama_infokampus);
            this.desJudul = (TextView) itemView.findViewById(R.id.des_kampus);
            this.cv = (CardView) itemView.findViewById(R.id.cvkampus);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Context Mcontext = itemView.getContext();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(desJudul.getText().toString()));
            Mcontext.startActivity(intent);

        }
    }
}