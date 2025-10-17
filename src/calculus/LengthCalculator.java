package calculus;

import model.Title;

public class LengthCalculator {
    private int totalLength = 0;

    public int getTotalLength() {
        return totalLength;
    }

    public void includeTitleToQueue(Title title) {
        totalLength += title.getDurationInMinutes();
    }
}
