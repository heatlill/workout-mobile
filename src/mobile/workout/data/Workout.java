package mobile.workout.data;

import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable( tableName="workout" )
public class Workout implements DataI {
    @DatabaseField( columnName="id", id=true, canBeNull=false )
    public long timeId;
    @DatabaseField( columnName="workout_date", canBeNull=false )
    public final Long datePerformed;
    public final List < Exercise > exercisesPerformed;

    private Workout(final Builder builder) {
        super();
        if ( builder == null || builder.datePerformed == null ) {
            throw new IllegalArgumentException( "Invalid builder argument." );
        }
        timeId = System.currentTimeMillis();
        datePerformed = builder.datePerformed;
        exercisesPerformed = builder.exercisesPerformed == null ? Exercise.EMPTY_EXERCISES
                : builder.exercisesPerformed;
    }
    
    public Workout() {
        this( new Builder( System.currentTimeMillis() ) );
    }

    public static final class Builder {
        public Builder( Long datePerformed ) {
            super();
            this.datePerformed = datePerformed;
        }

        private Long datePerformed;
        private List < Exercise > exercisesPerformed;

        public Builder setExerciesPerformed( List < Exercise > exercise ) {
            this.exercisesPerformed = exercise;
            return this;
        }

        public Workout build() {
            return new Workout( this );
        }
    }
}
