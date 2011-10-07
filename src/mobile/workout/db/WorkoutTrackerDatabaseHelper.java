package mobile.workout.db;

import java.sql.SQLException;

import mobile.workout.data.Exercise;
import mobile.workout.data.SetGroup;
import mobile.workout.data.Workout;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class WorkoutTrackerDatabaseHelper extends SQLiteOpenHelper {
    public ConnectionSource connectionSource;

    private static final String TAG = WorkoutTrackerDatabaseHelper.class.getName();
    
    private Dao<Exercise, Long> exerciseDao;
    private Dao<SetGroup, Long> setGroupDao;
    private Dao<Workout, Long> workoutDao;
    
    public WorkoutTrackerDatabaseHelper(Context context, String databaseName,
            CursorFactory factory, int databaseVersion) {
        super( context, databaseName, factory, databaseVersion );
    }

    @Override
    public void onCreate( SQLiteDatabase database ) {
        onCreate( database, connectionSource );
    }
    
    public void onCreate( SQLiteDatabase database, ConnectionSource connectionSource ) {
        try {
            Log.d( TAG, "Creating Tables..." );
            TableUtils.createTable( connectionSource, Exercise.class );
            TableUtils.createTable( connectionSource, Workout.class );
            TableUtils.createTable( connectionSource, SetGroup.class );
        } catch (SQLException e) {
            throw new IllegalStateException( "Error caught while creating tables.", e );
        }

    }
    
    public Dao<Exercise, Long> getExerciseDao() throws Exception {
        if ( exerciseDao == null ) {
            exerciseDao = DaoManager.createDao( connectionSource, Exercise.class );
        }
        return exerciseDao;
    }
    public Dao<SetGroup, Long> getSetGroupDao() throws Exception {
        if ( setGroupDao == null ) {
            setGroupDao = DaoManager.createDao( connectionSource, SetGroup.class );
        }
        return setGroupDao;
    }
    public Dao<Workout, Long> getWorkoutDao() throws Exception {
        if ( workoutDao == null ) {
            workoutDao = DaoManager.createDao(connectionSource, Workout.class );
        }
        return workoutDao;
    }

    @Override
    public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {
        onUpgrade( database, connectionSource, oldVersion, newVersion );
        
    }
    public void onUpgrade( SQLiteDatabase database, ConnectionSource connectionSource,
            int oldVersion, int newVersion ) {
        try {
            TableUtils.dropTable(connectionSource, Exercise.class, true);
            TableUtils.dropTable(connectionSource, SetGroup.class, true);
            TableUtils.dropTable(connectionSource, Workout.class, true);
        } catch ( SQLException e ) {
            throw new IllegalStateException( "Error caught while dropping tables.", e );
        }
        onCreate(database, connectionSource);
    }
}
