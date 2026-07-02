package com.biaren.sportclubmanager.corebundle.views.interfaces;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;

/**
 * Intefaces for list view
 * @author nbrunetti
 *
 * @param <T> type of data to view
 */
public interface ListView<T> {
    /**
     * Attach controller to panel to controls it
     * @param controller {@link ListViewController} to controls panel
     */
    void attachViewObserver(final ListViewController<T> controller);
}
