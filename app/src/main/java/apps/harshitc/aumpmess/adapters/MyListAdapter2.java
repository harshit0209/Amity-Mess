package apps.harshitc.aumpmess.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.util.ArrayList;
import java.util.List;

import apps.harshitc.aumpmess.R;
import apps.harshitc.aumpmess.models.MyListData2;
import apps.harshitc.aumpmess.utils.Tools;
import apps.harshitc.aumpmess.utils.Tools2;


public class MyListAdapter2 extends RecyclerView.Adapter<MyListAdapter2.ExampleViewHolder>{

    private ArrayList<MyListData2> myListData;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView title,date,day;
        public AppCompatImageButton appCompatImageButton;
        NachoTextView titleN;
        public RelativeLayout relativeLayout;
        public CardView cardView;
        public ExampleViewHolder(View itemView) {
            super(itemView);

            this.date=(TextView)itemView.findViewById(R.id.date);
            this.day=(TextView)itemView.findViewById(R.id.day);
            this.title=(TextView)itemView.findViewById(R.id.title);
            this.cardView=(CardView)itemView.findViewById(R.id.card_view);
            this.titleN=(NachoTextView)itemView.findViewById(R.id.et_tag);
            this.appCompatImageButton=(AppCompatImageButton) itemView.findViewById(R.id.reminderButton);
        }
    }

    public MyListAdapter2(ArrayList<MyListData2> exampleList) {
        myListData = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //Here is where view is gives to list
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        final MyListData2 currentItem = myListData.get(position);

        holder.date.setText(currentItem.getDate());
        holder.day.setText(currentItem.getDay());
        Tools2 tools2=new Tools2();
        List<String> items = tools2.getChipAdapterDelimeter(currentItem.getTitle());

        holder.titleN.setText(items);
        holder.titleN.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);

        // holder.title.setText(currentItem.getTitle());

        holder.cardView.setCardBackgroundColor(currentItem.getColor());
        holder.appCompatImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools2 tools2=new Tools2();
                tools2.setReminder(v.getContext(),"Aniversary",currentItem.getDate());
                Toast.makeText(v.getContext(), " Pressed " + currentItem.getDate(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return myListData.size();
    }
}