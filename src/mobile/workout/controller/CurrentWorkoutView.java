package mobile.workout.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mobile.workout.R;
import mobile.workout.data.Exercise;
import mobile.workout.data.Workout;
import mobile.workout.db.WorkoutTrackerDatabaseHelper;
import mobile.workout.db.WorkoutTrackerDatabaseHelperFactory;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.j256.ormlite.dao.Dao;

public class CurrentWorkoutView extends Activity {
    private static String TAG = CurrentWorkoutView.class.getName();
    private long date = 0;

    private ArrayAdapter < Exercise > exerciseListAdapter;

    private WorkoutTrackerDatabaseHelper helper;

    protected Workout currentWorkout;
    protected WorkoutTrackerDatabaseHelperFactory factory;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        setContentView( R.layout.current_workout_view );
        initialize();

        Button addExerciseButton = ( Button ) findViewById( R.id.add_exercise_button );
        Button dateButton = ( Button ) findViewById( R.id.workout_date_control );
        DateFormat dateFormat = DateFormat.getDateInstance( DateFormat.MEDIUM );
        ListView currentWorkoutView = ( ListView ) findViewById( R.id.current_workout_list_view );

        addExerciseButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                addExercise();
            }
        } );

        currentWorkoutView.setAdapter( exerciseListAdapter );

        if ( date == 0 ) {
            date = new Date().getTime();
        }
        dateButton.setText( dateFormat.format( new Date( date ) ).toString() );
        dateButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View v ) {
                showDatePopup();
            }
        } );

        super.onCreate( savedInstanceState );
    }

    /*
     * TODO - Rename this method to openExersizeSetEditView() and
     * open new activity.  Move the save code to the ExersizeSetEditView's
     * save button.
     */
    private void addExercise() {
        List < Exercise > exercises = new ArrayList < Exercise >(
                currentWorkout.exercisesPerformed );
        Exercise exercise = new Exercise();
        exercise.name = "Exercise" + ( exercises.size() + 1 );
        exercise.unitId = Long.valueOf( 1000 );
        try {
            helper.getExerciseDao().createOrUpdate( exercise );
        } catch ( SQLException e ) {
            throw new IllegalStateException( e );
        }
        
        exerciseListAdapter.add( exercise );
        exerciseListAdapter.notifyDataSetChanged();
    }

    private void showDatePopup() {

        LayoutInflater inflater = ( LayoutInflater ) this
                .getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View dateSetterView = ( View ) inflater.inflate(
                R.layout.date_setter_view, null );
        View v = this.findViewById( R.id.current_workout_view );

        Button cancelButton = ( Button ) dateSetterView
                .findViewById( R.id.cancel_date_button );
        Button saveButton = ( Button ) dateSetterView
                .findViewById( R.id.save_date_button );
        final PopupWindow dateSetterWindow = new PopupWindow( dateSetterView,
                450, 600, true );

        dateSetterWindow.showAtLocation( v, Gravity.CENTER, 0, 0 );
        cancelButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                dateSetterWindow.dismiss();
            }
        } );
        saveButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick( View v ) {
                try {
                    helper.getWorkoutDao().update( currentWorkout );
                } catch ( SQLException e ) {
                    throw new IllegalStateException( e );
                }
            }
        } );
    }

    /**
     * Gets the current workout that is in use.
     */
    protected Workout getCurrentWorkout( Long workoutId ) {
        try {
            Dao < Workout, Long > workoutDao = helper.getWorkoutDao();
            if ( workoutId != null ) {
                currentWorkout = workoutDao.queryForId( workoutId );
            }
            /**
             * If the current workout isn't initialized yet, create a new on.
             */
            if ( currentWorkout == null ) {
                currentWorkout = new Workout();
                workoutDao.create( currentWorkout );

            }
        } catch ( SQLException e ) {
            throw new IllegalStateException( e );
        }
        return currentWorkout;
    }

    protected void initialize() {
        factory = WorkoutTrackerDatabaseHelperFactory.getFactory();
        helper = factory.createHelper( getApplicationContext() );

        currentWorkout = getCurrentWorkout( null );
        List < Exercise > exercises = currentWorkout.exercisesPerformed;
        exerciseListAdapter = new ArrayAdapter < Exercise >( this,
                android.R.layout.simple_expandable_list_item_1, exercises );

    }
}
