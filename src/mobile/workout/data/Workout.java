package mobile.workout.data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "workout")
public class Workout implements DataI {
    @DatabaseField(columnName = "id", id = true, canBeNull = false)
    public Long id;
    @DatabaseField(columnName = "workout_date", canBeNull = false)
    public Long datePerformed;
    public List < Exercise > exercisesPerformed;

    private Workout(Long id) {
        super();
        if ( id == null ) {
            throw new IllegalArgumentException( "Invalid id argument." );
        }
        this.id = id;
        this.datePerformed = new Date( id ).getTime();
        this.exercisesPerformed = new ArrayList < Exercise >();
    }

    public Workout() {
        this( System.currentTimeMillis() );
    }

}
