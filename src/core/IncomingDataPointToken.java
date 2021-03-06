package net.opentsdb.core;

import com.google.common.base.Optional;

/**
 * Created by yaleiw on 5/23/16.
 */
public class IncomingDataPointToken {
    private String token;
    private IncomingDataPoint[] metrics;

    public IncomingDataPointToken() {}

    public IncomingDataPointToken(String token, IncomingDataPoint[] metrics) {
        this.token = token;
        this.metrics = metrics;
    }

    public void initialize(TSDB tsdb) {
        Prefix sysAOrgId = new Prefix();
        Optional<Prefix> prefix = tsdb.getTokenOrgMap().getPrefixForToken(this.token);
        if (prefix.isPresent()) {
            sysAOrgId = prefix.get();
        }
        for (IncomingDataPoint metricPoint : this.metrics) {
            metricPoint.setMetric(String.format("%s.%s", sysAOrgId.toString(), metricPoint.getMetric()));
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public IncomingDataPoint[] getMetrics() {
        return metrics;
    }

    public void setMetrics(IncomingDataPoint[] metrics) {
        this.metrics = metrics;
    }
}
