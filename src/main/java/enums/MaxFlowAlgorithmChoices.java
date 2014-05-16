package enums;

/**
 * Created by Navodar on 4/15/14.
 */
public enum MaxFlowAlgorithmChoices {
    DINIC(""),
    PREFLOWPUSH("");

    private String label;

    private MaxFlowAlgorithmChoices(String label) {
        this.label = label;
    }

}
