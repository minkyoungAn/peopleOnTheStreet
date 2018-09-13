package com.seoul.buskinggo.buskerconfigure;

import com.seoul.buskinggo.BasePresenter;
import com.seoul.buskinggo.BaseView;

public interface BuskerConfigureContract {
    interface View extends BaseView<Presenter> {
        // 기능

        void setLoadingIndicator(boolean active);

        void showMissingTask();

        void hideTitle();

        void showTitle(String title);

        void hideDescription();

        void showDescription(String description);

        void showCompletionStatus(boolean complete);

        void showEditTask(String taskId);

        void showTaskDeleted();

        void showTaskMarkedComplete();

        void showTaskMarkedActive();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void editTask();

        void deleteTask();

        void completeTask();

        void activateTask();
    }
}
