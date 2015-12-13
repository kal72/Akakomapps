package community.rasckspira.akakomapps;

import android.content.Context;
import android.content.Intent;
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
public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.MyViewHolder> {


    List<Data> mItems;
    Context mContext;

    public BeritaAdapter(Context context, List<Data> mItems) {

        this.mItems = mItems;
        this.mContext = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_berita_view, null);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Data item = mItems.get(i);

        myViewHolder.judul.setText(item.getNama().toString());
        myViewHolder.desJudul.setText(item.getJudul().toString());
        myViewHolder.aDetail = item.getDetail();


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView judul;
        public TextView desJudul;
        public CardView cv;
        public String aDetail;


        public MyViewHolder(View itemView) {
            super(itemView);

            //robotobold = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Bold.ttf");
            //robotoregular = Typeface.createFromAsset(itemView.getContext().getAssets(), "Roboto-Regular.ttf");


            this.judul = (TextView) itemView.findViewById(R.id.nama_berita);
            this.desJudul = (TextView) itemView.findViewById(R.id.des_berita);
            this.cv = (CardView) itemView.findViewById(R.id.cv_berita);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Context Mcontext = itemView.getContext();
            Intent intent = new Intent(Mcontext, DetailBeritaActivity.class);
            intent.putExtra("mJudul", judul.getText());
            intent.putExtra("mDetail", aDetail);
            Mcontext.startActivity(intent);

        }
    }
}
