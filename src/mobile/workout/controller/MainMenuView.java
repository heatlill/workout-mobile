package mobile.workout.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import mobile.workout.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainMenuView extends ListActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        final Map < String, Intent> items = new LinkedHashMap < String, Intent>( 3 );
        items.put( "Log Workout", new Intent( this, CurrentWorkoutView.class ) );
        items.put( "Workout History", null );
        items.put( "Account Options", null );
        //setContentView( R.id.main_menu_view );
        setListAdapter( new ArrayAdapter < String>( this,
                android.R.layout.simple_list_item_1, items.keySet().toArray(
                        new String[ 0 ] ) ) );
        ListView view = getListView();
        view.setOnItemClickListener( new OnItemClickListener() {

            @Override
            public void onItemClick( AdapterView < ? > arg0, View arg1,
                    int arg2, long arg3 ) {
                ArrayList<Intent> itemList = new ArrayList<Intent>( items.values() );
                Intent intent = itemList.get( arg2 );
                startActivityForResult(intent , 0 );
            }

        } );
        // TODO Auto-generated method stub
        super.onCreate( savedInstanceState );
    }

}
