package com.egineering.recyclerViewPOC.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.egineering.recyclerViewPOC.R;
import com.egineering.recyclerViewPOC.databinding.ItemTimeEntryBinding;
import com.egineering.recyclerViewPOC.viewmodels.TimeEntryItemViewModel;
import com.egineering.recyclerViewPOC.viewmodels.TimeEntryViewModel;

import java.util.ArrayList;
import java.util.List;

public class TimeEntryAdapter extends RecyclerView.Adapter<TimeEntryAdapter.BindingHolder> {

	private List<TimeEntryViewModel> timeEntries;
	private Context context;
	private boolean executePendingTransactions;

	public TimeEntryAdapter(Context context, Boolean executePendingTransactions) {
		this.context = context;
		timeEntries = new ArrayList<>();
		this.executePendingTransactions = executePendingTransactions;
	}

	@Override
	public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		ItemTimeEntryBinding timeEntryBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_time_entry, parent, false);
		return new BindingHolder(timeEntryBinding);
	}

	@Override
	public void onBindViewHolder(BindingHolder holder, int position) {
		ItemTimeEntryBinding timeEntryBinding = holder.binding;
		timeEntryBinding.setViewModel(new TimeEntryItemViewModel(context, timeEntries.get(position)));
		if (executePendingTransactions) {
			timeEntryBinding.executePendingBindings();
		}
	}

	@Override
	public int getItemCount() {
		return timeEntries.size();
	}

	public void setItems(List<TimeEntryViewModel> timeEntries) {
		this.timeEntries = timeEntries;
		notifyDataSetChanged();
	}

	public void addItem(TimeEntryViewModel timeEntry) {
		if (!timeEntries.contains(timeEntry)) {
			timeEntries.add(timeEntry);
			notifyItemInserted(timeEntries.size() - 1);
		} else {
			timeEntries.set(timeEntries.indexOf(timeEntry), timeEntry);
			notifyItemChanged(timeEntries.indexOf(timeEntry));
		}
	}

	public static class BindingHolder extends RecyclerView.ViewHolder {

		private ItemTimeEntryBinding binding;

		public BindingHolder(ItemTimeEntryBinding binding) {
			super(binding.cardView);
			this.binding = binding;
		}
	}

}