package mobile.workout.db;

import java.sql.SQLException;

import mobile.workout.data.Exercise;
import mobile.workout.data.SetGroup;
import mobile.workout.data.Workout;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class WorkoutTrackerDatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME = "WORKOUT_TRACKER.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TAG = WorkoutTrackerDatabaseHelper.class
            .getName();

    private Dao < Exercise, Long > exerciseDao;
    private Dao < SetGroup, Long > setGroupDao;
    private Dao < Workout, Long > workoutDao;

    public WorkoutTrackerDatabaseHelper(Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate( SQLiteDatabase database,
            ConnectionSource connectionSource ) {
        try {
            Log.d( TAG, "Creating Tables..." );
            TableUtils.createTable( connectionSource, Exercise.class );
            TableUtils.createTable( connectionSource, Workout.class );
            TableUtils.createTable( connectionSource, SetGroup.class );
        } catch ( SQLException e ) {
            throw new IllegalStateException(
                    "Error caught while creating tables.", e );
        }
    }

    public Dao < Exercise, Long > getExerciseDao() throws SQLException {
        if ( exerciseDao == null ) {
            exerciseDao = getDao( Exercise.class );
        }
        return exerciseDao;
    }

    public Dao < SetGroup, Long > getSetGroupDao() throws SQLException {
        if ( setGroupDao == null ) {
            setGroupDao = getDao( SetGroup.class );
        }
        return setGroupDao;
    }

    public Dao < Workout, Long > getWorkoutDao() throws SQLException {
        if ( workoutDao == null ) {
            workoutDao = getDao( Workout.class );
        }
        return workoutDao;
    }

    @Override
    public void onUpgrade( SQLiteDatabase database,
            ConnectionSource connectionSource, int oldVersion, int newVersion ) {
        try {
            TableUtils.dropTable( connectionSource, Exercise.class, true );
            TableUtils.dropTable( connectionSource, SetGroup.class, true );
            TableUtils.dropTable( connectionSource, Workout.class, true );
        } catch ( SQLException e ) {
            throw new IllegalStateException(
                    "Error caught while dropping tables.", e );
        }
        onCreate( database, connectionSource );
    }
}
