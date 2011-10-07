package mobile.workout.data;

import java.util.List;

public class Workout implements DataI {
    public long timeId;
    public final Long datePerformed;
    public final List < Exercise > exercisesPerformed;
    public final Integer userName;

    private Workout(final Builder builder) {
        super();
        if ( builder == null || builder.datePerformed == null
                || builder.userName == null ) {
            throw new IllegalArgumentException( "Invalid builder argument." );
        }
        timeId = System.currentTimeMillis();
        datePerformed = builder.datePerformed;
        userName = builder.userName;
        exercisesPerformed = builder.exercisesPerformed == null ? Exercise.EMPTY_EXERCISES
                : builder.exercisesPerformed;
    }

    public static final class Builder {
        public Builder(Long datePerformed, Integer userName) {
            super();
            this.datePerformed = datePerformed;
            this.userName = userName;
        }

        private Long datePerformed;
        private Integer userName;
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
