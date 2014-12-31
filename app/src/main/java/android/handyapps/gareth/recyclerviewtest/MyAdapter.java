package android.handyapps.gareth.recyclerviewtest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<String> timearr = new ArrayList<>();
    ArrayList<String> msgarr = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public View mTextView;

        public ViewHolder(View view) {
            super(view);
            mTextView = view;
        }
    }

    public MyAdapter(ArrayList<String> myDataSetTime,ArrayList<String>myDataSetMsg) {
        timearr = myDataSetTime;
        msgarr = myDataSetMsg;
    }

    @Override
    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.testlayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    // Called by RecyclerView to display the data at the specified position.
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {
        TextView title  = (TextView)viewHolder.mTextView.findViewById(R.id.title);
        TextView msg    = (TextView)viewHolder.mTextView.findViewById(R.id.msg);
        title.setText(timearr.get(i));
        msg.setText(msgarr.get(i));
        //msg.setText("This is a text section showing you an example of what a description would look like in the recycler view");
    }

    @Override
    // Returns the total number of items in the data set hold by the adapter.
    public int getItemCount() {
        return timearr.size();
    }
}
