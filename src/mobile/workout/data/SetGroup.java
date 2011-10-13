package mobile.workout.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
@DatabaseTable( tableName="set_group" )
public class SetGroup implements DataI {
    @DatabaseField( columnName="id", id=true, canBeNull=false )
    public long id;
    @DatabaseField( columnName="sequence_index", canBeNull=false )
    public Integer sequenceIndex;
    @DatabaseField( columnName="repitition_count", canBeNull=false )
    public Integer repititionCount;
    private SetGroup( Long id ) {
        super();
        if ( id == null ) {
            throw new IllegalArgumentException( "Invalid id argument." );
        }
        this.id = id;
        this.sequenceIndex = 0;
        this.repititionCount = 0;
    }
    
    public SetGroup() {
       this( System.currentTimeMillis() );
    }
    
}
