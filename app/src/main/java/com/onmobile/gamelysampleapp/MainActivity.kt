package com.onmobile.gamelysampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.onmobile.gamelysdk.sdkutil.ResultBundle
import com.onmobile.gamelysdk.sdkutil.enums.GamelyEvent
import com.onmobile.gamelysdk.sdkutil.enums.ResultStatus
import com.onmobile.gamelysdk.sdkutil.listeners.IEventListener
import com.onmobile.gamelysdk.sdkutil.listeners.IResponseListener
import com.onmobile.gamelysdk.view.activities.GamelySdkHomeActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //callback listener for response
        val iResponseListener = object : IResponseListener {
            override fun onResponse(
                resultStatus: ResultStatus,
                resultBundle: ResultBundle?,
                activity: AppCompatActivity?
            ) {
                //Incase of Win/Loose, response will have result bundle and activity
                //resultBundle?.bundle?.getString("Text") // Result text
                //resultBundle?.bundle?.getLong("NextPlayTimeStamp") // ExpiryTimeStamp
                //val bottomSheetDialog = BottomSheetDialog(activity) // use this activity to open bottomsheet
                (activity as GamelySdkHomeActivity).triviaCompleted()// use this to close sdk
            }
        }

        //callback listener for media play/pause events
        val iEventListener = object : IEventListener {
            override fun onReceiveEvent(gamelyEvent: GamelyEvent) {}

        }

        findViewById<AppCompatButton>(R.id.get_reward).setOnClickListener {
            val gamelySDKClient = (application as GamelySampleApplication).gamelySDKClient()
            gamelySDKClient?.getReward(iResponseListener, iEventListener)
        }
    }

}