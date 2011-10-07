package mobile.workout.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import mobile.workout.R;
import mobile.workout.data.Exercise;
import mobile.workout.data.Workout;
import mobile.workout.db.HelperFactoryI;
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

public class CurrentWorkoutView extends Activity {
    private static String tag = CurrentWorkoutView.class.getName();
    private long date = 0;
    public Workout currentWorkout;
    public WorkoutTrackerDatabaseHelper helper;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        setContentView( R.layout.current_workout_view );
        Button addExerciseButton = ( Button ) findViewById( R.id.add_exercise_button );
        Button dateButton = ( Button ) findViewById( R.id.workout_date_control );
        DateFormat dateFormat = DateFormat.getDateInstance( DateFormat.MEDIUM );
        ListView currentWorkoutView = ( ListView ) findViewById( R.id.current_workout_list_view );
        currentWorkout = getCurrentWorkout();
        final ArrayList < Exercise > exercises = new ArrayList < Exercise >( 5 );
        final ArrayAdapter < Exercise > exerciseListAdapter = new ArrayAdapter < Exercise >(
                this, android.R.layout.simple_expandable_list_item_1, exercises );
        addExerciseButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                addExercise( exercises, exerciseListAdapter );
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

    private void addExercise( final ArrayList < Exercise > exercises,
            final ArrayAdapter < Exercise > exerciseListAdapter ) {
        exercises.add( new Exercise.Builder( "Exercise"
                + ( exercises.size() + 1 ) ).Build() );
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

            }
        } );
    }

    /**
     * Gets the current workout that is in use.
     */
    /*
     * TODO: Take an ID arg, if null, return the latest one in the database, if
     * none available return new instance.
     */
    protected Workout getCurrentWorkout() {
        return new Workout.Builder( new Date().getTime(), 0 ).build();
    }

    protected Workout getHelper() {
        if ( helper == null ) {
            /*
             * TODO: Get databasename, oldversion, newversion, from Android manifest.
             */
            WorkoutTrackerDatabaseHelperFactory factory = WorkoutTrackerDatabaseHelperFactory
                    .getFactory( this.getApplicationContext(), "", null, 0, 0 );
            helper = factory.createHelper();
        }
    }
}
