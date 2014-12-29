package android.handyapps.gareth.recyclerviewtest;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Gareth on 2014-12-29.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private String[] mDataSet;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public View mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = view;
        }
    }

    public MyAdapter(String[] myDataSet) {
        mDataSet = myDataSet;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.testlayout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        TextView title = (TextView)viewHolder.mTextView.findViewById(R.id.title);
        TextView msg = (TextView)viewHolder.mTextView.findViewById(R.id.msg);
        title.setText(mDataSet[i]);
        msg.setText("This is a text section showing you an example of what a description would look like in the recycler view");
    }

    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
