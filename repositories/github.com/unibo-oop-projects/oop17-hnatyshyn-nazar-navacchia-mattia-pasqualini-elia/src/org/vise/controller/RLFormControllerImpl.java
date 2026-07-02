package org.vise.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.vise.model.user.CurrentUser;
import org.vise.model.user.CurrentUserImpl;
import org.vise.network.NetworkUtility;
import org.vise.network.RLFormNetwork;
import org.vise.view.UI;
import org.vise.view.UIRLForm;

import javafx.util.Pair;

/**
 * 
 * Implementation of {@link RLFormController}.
 *
 */
public class RLFormControllerImpl implements RLFormController, UpdatableUI {

    private Pair<Boolean, Pair<String, CurrentUser>> lastOperation;
    private final Set<UI> uis = new HashSet<>();
    private final Set<UIRLForm> ui = new HashSet<>();

    /**
     * 
     */
    @Override
    public final void login(final String email, final String pwd) throws JSONException, IOException {
            this.lastOperation = RLFormNetwork.makeLogin(email, pwd);

        if (this.lastOperation.getKey()) {
            NetworkUtility.save(new CurrentUserImpl.UserBuilder()
                                                .id(this.lastOperation.getValue().getValue().getUserID())
                                                .username(this.lastOperation.getValue().getValue().getUsername())
                                                .email(email)
                                                .password(pwd)
                                                .build());
            ui.forEach(u -> u.goToPlayerAfterLogin());
        } else {
            this.showNotification(this.lastOperation.getValue().getKey());
        }
    }

    /**
     * 
     */
    @Override
    public final void registration(final String nomeUtente, final String email, final String pwd) throws JSONException, IOException {
        this.lastOperation = RLFormNetwork.makeRegistration(nomeUtente, email, pwd);
        this.showNotification(this.lastOperation.getValue().getKey());
    }

    @Override
    public final void automaticLogin() throws JSONException, IOException, ParseException {
        if (NetworkUtility.isFilePresent()) {
                this.login(NetworkUtility.readJSON().getEmail(), NetworkUtility.readJSON().getPassword());
        }
    }

    /**
     * 
     */
    @Override
    public final void bindUI(final UI ui) {
        this.uis.add(ui);
    }

    /**
     * 
     */
    @Override
    public final void bindRLForm(final UIRLForm ui) {
        this.ui.add(ui);
    }


    /**
     * 
     */
    @Override
    public final void showNotification(final String textNotification) {
        this.uis.forEach(u -> u.showNotification(textNotification)); 
    }

    /**
     * 
     */
    @Override
    public final boolean isNetworkingAvailable() {
        return NetworkUtility.isNetworkingAvailable();
    }

}
