package org.hisp.dhis.android.sdk.models.dashboard;

import java.util.List;

/**
 * Created by arazabishov on 9/30/15.
 */
public class DashboardContentService implements IDashboardItemContentService {
    private final IDashboardItemContentStore mDashboardItemContentStore;

    public DashboardContentService(IDashboardItemContentStore mDashboardItemContentStore) {
        this.mDashboardItemContentStore = mDashboardItemContentStore;
    }

    @Override
    public List<DashboardItemContent> query() {
        return mDashboardItemContentStore.query();
    }

    @Override
    public List<DashboardItemContent> query(List<String> types) {
        return mDashboardItemContentStore.query(types);
    }
}
