package mobile.workout.db;


public interface HelperFactoryI<T extends WorkoutTrackerDatabaseHelper> {
    public T createHelper();
}
