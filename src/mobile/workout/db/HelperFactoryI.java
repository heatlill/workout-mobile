package mobile.workout.db;

import android.content.Context;


public interface HelperFactoryI<T extends WorkoutTrackerDatabaseHelper> {
    public T createHelper( Context context );
}
