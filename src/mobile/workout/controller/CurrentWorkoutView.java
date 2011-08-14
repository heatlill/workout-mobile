package mobile.workout.controller;

import java.util.ArrayList;

import mobile.workout.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class CurrentWorkoutView extends Activity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        setContentView( R.layout.current_workout_view );

        Button addExerciseButton = ( Button ) findViewById( R.id.add_exercise_button );
        addExerciseButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                addExercise();
            }
        } );

        super.onCreate( savedInstanceState );
    }

    private void addExercise() {
        LinearLayout l = ( LinearLayout ) findViewById( R.id.current_workout_view );
        LayoutInflater inflater = ( LayoutInflater ) getSystemService( Context.LAYOUT_INFLATER_SERVICE );

        Spinner exerciseSpinner = new Spinner( this );
        TextView ex1 = new TextView( this );
        ex1.setText( "Item1" );
        ArrayList < View > exerciseItems = new ArrayList < View >( 1 );
        exerciseItems.add( ex1 );
        exerciseSpinner.addTouchables( exerciseItems );

        Spinner repSpinner = new Spinner( this );
        TextView rep1 = new TextView( this );
        rep1.setText( Integer.valueOf( 1 ).toString() );
        ArrayList < View > repItems = new ArrayList < View >();
        repItems.add( rep1 );
        repSpinner.addTouchables( repItems );

        View newView = inflater.inflate(
                mobile.workout.R.layout.exercise_set_rep_view, null );
        l.addView( newView );
    }
}
