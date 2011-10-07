package mobile.workout.data;

import java.util.Collections;
import java.util.List;

public class SetGroup implements DataI {
    public long timeId;
    public Integer sequenceIndex;
    public Integer repititionCount;
    public static final List < SetGroup > EMPTY_LIST = Collections.emptyList();
    private SetGroup( Builder builder ) {
        super();
        if ( builder == null ) {
            throw new IllegalArgumentException( "Invalid builder argument." );
        }
        timeId = System.currentTimeMillis();
        this.repititionCount = builder.repititionCount;
        this.sequenceIndex = builder.sequenceIndex;
    }
    
    public static final class Builder {
        private Integer sequenceIndex;
        private Integer repititionCount;
        public Builder( Integer sequenceIndex, Integer repititionCount ) {
            super();
            this.sequenceIndex = sequenceIndex;
            this.repititionCount = repititionCount;
        }
        public SetGroup build() {
            return new SetGroup( this );
        }
    }
}
