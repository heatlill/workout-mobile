package mobile.workout;

public enum WorktoutTrackerConstants {
    PREFERENCES("PREFERENCES");
    
    private String name;
    WorktoutTrackerConstants( String name ) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
    
}
