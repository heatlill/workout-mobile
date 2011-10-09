package mobile.workout.data;

import java.util.Collections;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable( tableName="exercise" )
public class Exercise implements DataI {
    @DatabaseField( columnName="id", id=true, canBeNull=false )
    public long timeId;
    @DatabaseField( columnName="unit_id", canBeNull=false )
    public long unitId;
    @DatabaseField( canBeNull=false )
    public String name;
    @DatabaseField( canBeNull=true )    
    public String description;
    public List < SetGroup > setGroups;
    public static final List < Exercise > EMPTY_EXERCISES = Collections
            .emptyList();

    
    public Exercise() {
        this( new Builder( "New Exercise" ) );
    }
    private Exercise(Builder builder) {
        super();
        if ( builder == null || builder.name == null ) {
            throw new IllegalArgumentException( "Invalid builder argument." );
        }
        timeId = System.currentTimeMillis();
        this.name = builder.name;
        this.description = builder.description;
        this.setGroups = builder.setGroups == null ? SetGroup.EMPTY_LIST
                : builder.setGroups;
    }

    public String toString() {
        return name;
    }
    
    public static final class Builder {
        private String name;
        private String description;
        private List < SetGroup > setGroups;

        public Builder(String name) {
            super();
            this.name = name;
        }

        public Builder setDescription( String description ) {
            this.description = description;
            return this;
        }

        public Builder setSetsOfReps( List < SetGroup > setsOfReps ) {
            this.setGroups = setsOfReps;
            return this;
        }

        public Exercise Build() {
            return new Exercise( this );
        }
    }
}
