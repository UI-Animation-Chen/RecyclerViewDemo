package com.czf.recyclerviewdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dnwalter.formlayoutmanager.adapter.BaseFormAdapter;
import com.dnwalter.formlayoutmanager.layoutmanager.FormLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);

    List<String> list = new ArrayList<>(100);
    for (int i = 0; i < 10000; i++) {
      list.add("list data: " + i);
    }

    recyclerView.setAdapter(new MyAdapter(this, list));
    recyclerView.setLayoutManager(new FormLayoutManager(100, recyclerView));
  }

  public static class MyAdapter extends BaseFormAdapter<String> {

    public MyAdapter(Context context, List<String> data) {
      super(context, data);
    }

    @Override
    public int getItemViewType(int position) {
      return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder createViewHolder(@NonNull View view, int viewType) {
      return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      TextView tv = (TextView)holder.itemView;
      float density = tv.getResources().getDisplayMetrics().density;
      tv.setHeight(Math.round(50 * density));
      tv.setPadding(Math.round(10 * density), 0, Math.round(10 * density), 0);
      tv.setGravity(Gravity.CENTER);
      ((TextView)holder.itemView).setText(this.mList.get(position));
    }

    @Override
    public int getRowCount() {
      return 100;
    }

    @Override
    public int getColumnCount() {
      return 100;
    }

    @Override
    protected View createView(ViewGroup viewGroup, int viewType) {
      TextView tv = new TextView(viewGroup.getContext());
      return tv;
    }
  }

  public static class MyViewHolder extends RecyclerView.ViewHolder {

    public MyViewHolder(View itemView) {
      super(itemView);
    }

  }

}
