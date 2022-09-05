package com.onmobile.gamelysampleapp

import android.app.Application
import android.widget.Toast
import com.onmobile.gamelysdk.exceptions.GamelySdkInitialisationException
import com.onmobile.gamelysdk.sdkutil.GamelySdkClient
import com.onmobile.gamelysdk.sdkutil.IGamelySdkClient

class GamelySampleApplication : Application() {
    private var gamelySDKClient: IGamelySdkClient? = null
    override fun onCreate() {
        super.onCreate()
        try {
            gamelySDKClient = GamelySdkClient.Builder(this)
                .setUserId("Test") //Mandatory
                .setApiKey("2zOLoNOUnsuuF3y2izLl")//Mandatory
                .setLogEnabled(false) //false by default | Optional
                .build()
        } catch (gamelySdkInitialisationException: GamelySdkInitialisationException) {
            //Catch the exception here
            Toast.makeText(this, gamelySdkInitialisationException.message, Toast.LENGTH_SHORT)
                .show()
            //gamelySdkInitialisationException.printStackTrace()
        }
    }

    fun gamelySDKClient(): IGamelySdkClient? = gamelySDKClient
}