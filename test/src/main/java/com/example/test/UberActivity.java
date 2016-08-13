package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uber.sdk.android.core.UberSdk;
import com.uber.sdk.android.core.auth.LoginManager;
import com.uber.sdk.android.rides.RideParameters;
import com.uber.sdk.android.rides.RideRequestActivityBehavior;
import com.uber.sdk.android.rides.RideRequestButton;
import com.uber.sdk.core.auth.Scope;
import com.uber.sdk.rides.client.SessionConfiguration;

import java.util.Arrays;

public class UberActivity extends AppCompatActivity {

    private LoginManager loginManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber);

        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("YOUR_CLIENT_ID") //This is necessary
                .setRedirectUri("YOUR_REDIRECT_URI") //This is necessary if you'll be using implicit grant
                .setEnvironment(SessionConfiguration.Environment.SANDBOX) //Useful for testing your app in the sandbox environment
                .setScopes(Arrays.asList(Scope.PROFILE, Scope.RIDE_WIDGETS)) //Your scopes for authentication here
                .build();
        //This is a convenience method and will set the default config to be used in other components without passing it directly.
        UberSdk.initialize(config);
        RideRequestButton rideRequestButton = (RideRequestButton) findViewById(R.id.uber);
        Activity activity = this; // If you're in a fragment you must get the containing Activity!
        int requestCode = 1234;
        RideRequestActivityBehavior rideRequestBehavior = new RideRequestActivityBehavior(activity, requestCode);
        rideRequestButton.setRequestBehavior(rideRequestBehavior);
// Optional, default behavior is to use current location for pickup
        RideParameters rideParams = new RideParameters.Builder()
                .setProductId("a1111c8c-c720-46c3-8534-2fcdd730040d")
                .setPickupLocation(37.775304, -122.417522, "Uber HQ", "1455 Market Street, San Francisco")
                .setDropoffLocation(37.795079, -122.4397805, "Embarcadero", "One Embarcadero Center, San Francisco")
                .build();
//        rideRequestButton.setRideParameters(rideParams);
        rideRequestBehavior.requestRide(this, rideParams);
//        LoginCallback loginCallback = new LoginCallback() {
//            @Override
//            public void onLoginCancel() {
//                // User canceled login
//            }
//
//            @Override
//            public void onLoginError(@NonNull AuthenticationError error) {
//                // Error occurred during login
//            }
//
//            @Override
//            public void onLoginSuccess(@NonNull AccessToken accessToken) {
//                // Successful login!  The AccessToken will have already been saved.
//            }
//
//            @Override
//            public void onAuthorizationCodeReceived(@NonNull String authorizationCode) {
//
//            }
//        };
//        AccessTokenManager accessTokenManager = new AccessTokenManager(this);
//        loginManager = new LoginManager(accessTokenManager, loginCallback);
//        loginManager.login(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        loginManager.onActivityResult(this, requestCode, resultCode, data);
    }
}
