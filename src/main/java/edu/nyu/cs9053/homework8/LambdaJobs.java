package edu.nyu.cs9053.homework8;

public class LambdaJobs implements Comparable<LambdaJobs> {

    private final double startTime, finishTime;

    private final double costOfJob;

    protected LambdaJobs(double startTime, double finishTime) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.costOfJob = 1d;
    }

    protected LambdaJobs(double startTime, double finishTime, double costOfJob) {
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.costOfJob = costOfJob;
    }

    @Override
    public int compareTo(LambdaJobs object) {
        return Double.compare(this.finishTime, object.finishTime);
    }

    public double getStartTime() {
        return this.startTime;
    }

    public double getFinishTime() {
        return this.finishTime;
    }

    public double getCostOfJob() {
        return this.costOfJob;
    }
}