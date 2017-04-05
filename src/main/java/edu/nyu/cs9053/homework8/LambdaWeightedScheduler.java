package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaWeightedScheduler {

    private final List<LambdaJobs> lambdaJobs;

    public LambdaWeightedScheduler (List<LambdaJobs> lambdaJobs) {
        this.lambdaJobs = lambdaJobs;
        Collections.sort(lambdaJobs);
        AcceptCompatibleJobsWithMaximumCost(lambdaJobs);
    }

    public int getLatestJobCompatibleWithCurrentJob(List<LambdaJobs> lambdaJobs, int currentIndex) {

        for (int index = (lambdaJobs.size() - 1); index >= 0; index--) {
            if (lambdaJobs.get(index).getFinishTime() <= lambdaJobs.get(currentIndex).getStartTime()) {
                return index;
            }
        }

        return -1;
    }

    public List<LambdaJobs> AcceptCompatibleJobsWithMaximumCost (List<LambdaJobs> lambdaJobs) {

        List<LambdaJobs> compatibleJobsWithMaximumCost = new ArrayList<LambdaJobs>();

        double costofJob[] = new double[lambdaJobs.size() + 1];

        int latestJobCompatibleWithCurrentJob[] = new int[lambdaJobs.size()];

        int maximumValueAtIndex = 0;

        for (int index = 0; index < lambdaJobs.size(); index++) {
            latestJobCompatibleWithCurrentJob[index] = getLatestJobCompatibleWithCurrentJob(lambdaJobs, index);
        }

        costofJob[0] = 0d;

        for(int index = 1; index <= lambdaJobs.size(); index++) {

            double costOfCompatibleJobsWithMaximimValueBeforeCurrentJob = costofJob[index - 1];

            double costOfTheCurrentJobIncludingPreviousCompatibleJobs = lambdaJobs.get(index - 1).getCostOfJob();

            if(latestJobCompatibleWithCurrentJob[index-1] != -1){
                costOfTheCurrentJobIncludingPreviousCompatibleJobs += costofJob[latestJobCompatibleWithCurrentJob[index - 1] + 1];
            }

            costofJob[index] = Math.max(costOfTheCurrentJobIncludingPreviousCompatibleJobs, costOfCompatibleJobsWithMaximimValueBeforeCurrentJob);

            if (costOfCompatibleJobsWithMaximimValueBeforeCurrentJob < costOfTheCurrentJobIncludingPreviousCompatibleJobs) {
                maximumValueAtIndex = (index - 1);
            }
        }

        compatibleJobsWithMaximumCost.add(lambdaJobs.get(maximumValueAtIndex));

        while (latestJobCompatibleWithCurrentJob[maximumValueAtIndex] != (-1)) {
            maximumValueAtIndex = latestJobCompatibleWithCurrentJob[maximumValueAtIndex];
            compatibleJobsWithMaximumCost.add(lambdaJobs.get(maximumValueAtIndex));
        }

        return compatibleJobsWithMaximumCost;
    }
}