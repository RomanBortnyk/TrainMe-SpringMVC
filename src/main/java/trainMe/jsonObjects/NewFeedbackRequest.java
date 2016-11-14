package trainMe.jsonObjects;

/**
 * Created by romab on 11/14/16.
 */
public class NewFeedbackRequest {

    private int destinationUserId;
    private String newFeedbackText;

    public int getDestinationUserId() {
        return destinationUserId;
    }

    public void setDestinationUserId(int destinationUserId) {
        this.destinationUserId = destinationUserId;
    }

    public String getNewFeedbackText() {
        return newFeedbackText;
    }

    public void setNewFeedbackText(String newFeedbackText) {
        this.newFeedbackText = newFeedbackText;
    }
}
