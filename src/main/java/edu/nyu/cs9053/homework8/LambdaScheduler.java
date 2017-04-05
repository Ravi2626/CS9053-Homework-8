package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaScheduler {

    private final List<LambdaJobs> lambdaJobs;

    public LambdaScheduler(List<LambdaJobs> lambdaJobs) {
        this.lambdaJobs = lambdaJobs;
        Collections.sort(lambdaJobs);
        AcceptMaximumPossibleCompatibleJobs(lambdaJobs);
    }

    public List<LambdaJobs> AcceptMaximumPossibleCompatibleJobs (List<LambdaJobs> lambdaJobs) {

        List<LambdaJobs> maximumPossibleCompatibleJobs = new ArrayList<LambdaJobs>();

        int previousIndex = 0;

        maximumPossibleCompatibleJobs.add(lambdaJobs.get(0));

        for (int currentIndex = 1; currentIndex < lambdaJobs.size(); currentIndex++) {
            if (lambdaJobs.get(currentIndex).getStartTime() >= lambdaJobs.get(previousIndex).getFinishTime()) {
                maximumPossibleCompatibleJobs.add(lambdaJobs.get(currentIndex));
                currentIndex++;
                previousIndex = currentIndex;
            }
        }

        return maximumPossibleCompatibleJobs;
    }
}