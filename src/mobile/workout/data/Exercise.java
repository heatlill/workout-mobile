package mobile.workout.data;

import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "exercise")
public class Exercise implements DataI {
    @DatabaseField(columnName = "id", id = true, canBeNull = false)
    public Long id;
    @DatabaseField(columnName = "unit_id", canBeNull = true)
    public Long unitId;
    @DatabaseField(canBeNull = false)
    public String name;
    @DatabaseField(canBeNull = true)
    public String description;
    public List < SetGroup > setGroups;

    public Exercise() {
        this( System.currentTimeMillis() );
    }

    private Exercise(Long id) {
        super();
        if ( id == null ) {
            throw new IllegalArgumentException( "Invalid id argument." );
        }
        this.id = id;
        this.name = "New Exercise";
        this.description = "New Exercise";
        this.setGroups = new ArrayList< SetGroup >();
    }

    public String toString() {
        return name;
    }

}
