package com.grupomedios.circulo_pro.android.card.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.grupomedios.circulo_pro.android.BuildConfig;
import com.grupomedios.circulo_pro.android.DesclubApplication;
import com.grupomedios.circulo_pro.android.R;
import com.grupomedios.circulo_pro.android.home.activity.DesclubMainActivity;
import com.grupomedios.circulo_pro.android.warranty.activity.WarrantyActivity;
import com.grupomedios.circulo_pro.api.facade.CorporateMembershipFacade;
import com.grupomedios.circulo_pro.api.representations.CorporateMembershipRepresentation;
import com.grupomedios.circulo_pro.util.security.UserHelper;

import javax.inject.Inject;

/**
 * Main {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {

    private final String TAG = "CardFragment";

    @Inject
    UserHelper userHelper;

    @Inject
    CorporateMembershipFacade corporateMembershipFacade;

    public CardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((DesclubApplication) getActivity().getApplication()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_card, container, false);

        if (userHelper.isLoggedIn()) {
            mainView = inflater.inflate(R.layout.fragment_card_user, container, false);
        }

        return mainView;

    }

    @Override
    public void onStart() {
        super.onStart();

        TextView version = (TextView) getView().findViewById(R.id.card_version);
        version.setText("v." + BuildConfig.VERSION_NAME);

        if (userHelper.isLoggedIn()) {
            TextView name = (TextView) getView().findViewById(R.id.card_name);
            TextView email = (TextView) getView().findViewById(R.id.card_email);
            TextView membershipNumber = (TextView) getView().findViewById(R.id.card_card_numberl);
            TextView validThru = (TextView) getView().findViewById(R.id.card_validThru);

            CorporateMembershipRepresentation currentUser = userHelper.getCurrentUser();

            name.setText(currentUser.getName());
            email.setText(currentUser.getEmail());
            membershipNumber.setText(userHelper.getCardNumber());
            validThru.setText(userHelper.getValidThru());

            getView().findViewById(R.id.card_btn_request_warranty).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), WarrantyActivity.class));
                }
            });

            Button exit = (Button) getView().findViewById(R.id.card_exit_button);
            exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    MaterialDialog confirmDialog = new MaterialDialog.Builder(getActivity())
                            .title(R.string.exit_question)
                            .content(R.string.exit_text)
                            .positiveText(R.string.yes)
                            .negativeText(R.string.no)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    super.onPositive(dialog);
                                    userHelper.logout();

                                    Intent intent = new Intent(getActivity(), DesclubMainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            })
                            .show();


                }
            });

        } else {

            Button accessButton = (Button) getView().findViewById(R.id.card_access_button);
            accessButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DesclubMainActivity mainActivity = (DesclubMainActivity) getActivity();
                    userHelper.showLoginDialog(getActivity(), mainActivity.requestQueue, corporateMembershipFacade);
                }
            });
        }
    }
}