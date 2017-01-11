package com.egineering.recyclerViewPOC.viewmodels;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import com.egineering.recyclerViewPOC.R;
import com.egineering.recyclerViewPOC.helpers.DateHelper;

import timber.log.Timber;

public class TimeEntryItemViewModel extends BaseObservable {

    private Context context;
    private TimeEntryViewModel timeEntry;

    public TimeEntryItemViewModel(Context context, TimeEntryViewModel timeEntry) {
        this.context = context;
        this.timeEntry = timeEntry;
    }

    public String getDescription() {
        return (timeEntry.taskDescription);
    }

    public String getDuration() {
        return timeEntry.duration + context.getResources().getString(R.string.hours);
    }

    public String getEntryDate() { return timeEntry.entryDate; }

    public String getTaskName() { return timeEntry.taskName; }

    public String getDayOfWeek() {
        return DateHelper.getDayOfWeek(timeEntry.entryDate);
    }

    public View.OnClickListener getOnClickTimeEntry() {
        return v -> Timber.d("no-op");
    }


}
