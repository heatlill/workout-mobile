package mobile.workout.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

public class WorkoutTrackerDatabaseHelperFactory implements
        HelperFactoryI < WorkoutTrackerDatabaseHelper > {
    private static WorkoutTrackerDatabaseHelperFactory INSTANCE;
    private static WorkoutTrackerDatabaseHelper HELPER;
    private Context context;
    private String databaseName;
    private CursorFactory factory;
    private int databaseVersion;

    /*
     * Private constructor enforces creation with factory method.
     */
    protected WorkoutTrackerDatabaseHelperFactory() {
        super();
    }

    /**
     * Instatiate and initialize the singleton instance.
     */
    public static WorkoutTrackerDatabaseHelperFactory getFactory( Context context,
            String databaseName, CursorFactory factory, int databaseVersion ) {
       if ( INSTANCE == null ) {
           INSTANCE = new WorkoutTrackerDatabaseHelperFactory();
           INSTANCE.context = context;
           INSTANCE.databaseName = databaseName;
           INSTANCE.factory = factory;
           INSTANCE.databaseVersion = databaseVersion;
       }
       return INSTANCE;
    }

    @Override
    public WorkoutTrackerDatabaseHelper createHelper() {
        if ( HELPER == null ) {
            HELPER = new WorkoutTrackerDatabaseHelper( context, databaseName,
                    factory, databaseVersion );
        }
        return HELPER;
    }

}
