package com.egineering.recyclerViewPOC.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.egineering.recyclerViewPOC.R;
import com.egineering.recyclerViewPOC.adapter.TimeEntryAdapter;
import com.egineering.recyclerViewPOC.viewmodels.TimeEntryViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class TimeEntriesFragment extends Fragment {

	@BindView(R.id.recycler_time_entries)
	RecyclerView mListPosts;

	@BindView(R.id.toolbar)
	Toolbar mToolbar;

	@BindView(R.id.add_time_entry)
	FloatingActionButton addTimeEntry;

	private TimeEntryAdapter timeEntryAdapter;
	private boolean executePendingTransactions;

	public static TimeEntriesFragment newInstance() {
		TimeEntriesFragment timeEntriesFragment = new TimeEntriesFragment();
		Bundle args = new Bundle();
		timeEntriesFragment.setArguments(args);
		return timeEntriesFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		timeEntryAdapter = new TimeEntryAdapter(getActivity(), executePendingTransactions);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View fragmentView = inflater.inflate(R.layout.fragment_time_entries, container, false);
		ButterKnife.bind(this, fragmentView);

		mToolbar.setTitle(getString(R.string.app_name));
		((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

		addTimeEntry.setOnClickListener(v -> {
			executePendingTransactions = !executePendingTransactions;
			addTimeEntry.setImageResource(executePendingTransactions ? R.drawable.ic_check_black_24dp : R.drawable.ic_close_black_24dp);
			Timber.d("new adapter: " + executePendingTransactions);
			timeEntryAdapter = new TimeEntryAdapter(getActivity(), executePendingTransactions);
			mListPosts.setAdapter(timeEntryAdapter);
			mListPosts.invalidate();
			//RV will crash if you don't give it a frame to reset
			new Handler().post(() -> getTimeEntries());
		});
		return fragmentView;
	}

	@Override
	public void onResume() {
		super.onResume();
		setupRecyclerView();
		//load up RV with test data
		getTimeEntries();
	}

	private void setupRecyclerView() {
		mListPosts.setLayoutManager(new LinearLayoutManager(getActivity()));
		mListPosts.setHasFixedSize(true);
		mListPosts.setAdapter(timeEntryAdapter);
	}

	// Retrieve time entries for user.
	public void getTimeEntries() {

		for (int i  = 0; i < 15; i++) {
			TimeEntryViewModel timeEntry = new TimeEntryViewModel();
			timeEntry.duration = String.valueOf(0.5 + i);
			timeEntry.entryDate = "12/" + i + "/2016";
			timeEntry.taskName = "task name";
			timeEntry.taskDescription = "task desc";

			timeEntryAdapter.addItem(timeEntry);
		}

	}

}
