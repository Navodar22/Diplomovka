package enums;

/**
 * Created by Navodar on 4/15/14.
 */
public enum MainToolbarButtonChoices {
    EDGE("Edge"),
    VERTEX("Vertex"),
    SELECT_MORE("Select");

    private String label;

    private MainToolbarButtonChoices(String label) {
        this.label = label;
    }
}
