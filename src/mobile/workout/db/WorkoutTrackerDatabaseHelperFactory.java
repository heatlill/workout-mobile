package mobile.workout.db;

import android.content.Context;

public class WorkoutTrackerDatabaseHelperFactory implements
        HelperFactoryI < WorkoutTrackerDatabaseHelper > {
    private static WorkoutTrackerDatabaseHelperFactory INSTANCE;
    private static WorkoutTrackerDatabaseHelper HELPER;

    /*
     * Private constructor enforces creation with factory method.
     */
    protected WorkoutTrackerDatabaseHelperFactory() {
        super();
    }

    /**
     * Instatiate and initialize the singleton instance.
     */
    public static WorkoutTrackerDatabaseHelperFactory getFactory() {
       if ( INSTANCE == null ) {
           INSTANCE = new WorkoutTrackerDatabaseHelperFactory();
       }
       return INSTANCE;
    }

    @Override
    public WorkoutTrackerDatabaseHelper createHelper( Context context ) {
        if ( HELPER == null ) {
            HELPER = new WorkoutTrackerDatabaseHelper( context );
        }
        return HELPER;
    }

}
