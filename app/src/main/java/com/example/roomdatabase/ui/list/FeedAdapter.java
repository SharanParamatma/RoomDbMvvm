package com.example.roomdatabase.ui.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.ui.add_text.room.DatabaseClient;
import com.example.roomdatabase.ui.add_text.room.Recipe;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.HeroViewHolder> {
    private Context mCtx;
    private List<Recipe> feedModelList;
    FeedAdapter(Context mCtx, List<Recipe> heroList) {
        this.mCtx = mCtx;
        this.feedModelList = heroList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.layout_feed, parent, false);
        return new HeroViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final HeroViewHolder holder, final int i) {

        try {
                holder.tv_d.setText(Html.fromHtml(String.valueOf(feedModelList.get(i).getId())));
                holder.tv_m.setText(Html.fromHtml(feedModelList.get(i).getMessage()));



        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return feedModelList.size();

    }

    class HeroViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_m,tv_d,tv_option;
        private LinearLayout linearLayout;

        HeroViewHolder(View view) {
            super(view);

            tv_m=view.findViewById(R.id.tv_messageNotify);
            tv_d=view.findViewById(R.id.tv_date);
            linearLayout=view.findViewById(R.id.lin1);
            tv_option=view.findViewById(R.id.tv_option);
           tv_option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int pos = getAdapterPosition();
                    PopupMenu popup = new PopupMenu(mCtx,tv_option);
                    popup.inflate(R.menu.option);
                    popup.show();
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.remove:
                                    try {
                                        Recipe hm = feedModelList.get(pos);
                                        DatabaseClient.getInstance(mCtx).getAppDatabase().recipeDao().delete(hm.getId());
                                        feedModelList.remove(pos);
                                        notifyItemRemoved(pos);
                                        notifyItemRangeChanged(pos, feedModelList.size());
                                        notifyDataSetChanged();
                                    } catch (IndexOutOfBoundsException e) {
                                        e.printStackTrace();
                                    }
                                    return true;
                                case R.id.navigation_update:
                                    AlertDialog.Builder alert = new AlertDialog.Builder(mCtx);
                                    final Recipe hm = feedModelList.get(pos);
                                    final EditText edittext = new EditText(mCtx);

//                                    final EditText edittext = new EditText(mCtx);
//                                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                                            LinearLayout.LayoutParams.MATCH_PARENT,
//                                            LinearLayout.LayoutParams.WRAP_CONTENT);
//                                    edittext.setPadding(10,5,5,10);
//                                    edittext.setLayoutParams(lp);

                                    alert.setMessage(" ");
                                    alert.setTitle("Enter Your Message");
                                    edittext.setText(hm.getMessage());

                                    alert.setView(edittext);


                                    alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {

                                            String YouEditTextValue = edittext.getText().toString();
                                            DatabaseClient.getInstance(mCtx).getAppDatabase().recipeDao().update(YouEditTextValue,hm.getId());
                                            hm.setMessage(YouEditTextValue);
                                            notifyItemRangeChanged(pos, feedModelList.size());
                                            notifyDataSetChanged();
                                        }
                                    });

                                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            // what ever you want to do with No option.
                                        }
                                    });

                                    alert.show();
                                    return true;

                                default:
                                    return false;
                            }
                        }
                    });
                }
            });
        }
    }
}
