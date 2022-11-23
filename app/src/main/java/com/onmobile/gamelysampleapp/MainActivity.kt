package com.onmobile.gamelysampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.onmobile.gamelysdk.sdkutil.IGamelySdkClient
import com.onmobile.gamelysdk.sdkutil.IResponseListener


class MainActivity : AppCompatActivity() {
    private var gamelySDKClient: IGamelySdkClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatButton>(R.id.get_template).setOnClickListener {
            gamelySDKClient = (application as GamelySampleApplication).gamelySDKClient()
            gamelySDKClient?.getTemplate(
                this@MainActivity,
                %RULE_NAME%,
                object : IResponseListener {
                    override fun onSuccess() {}
                    override fun onFailure(message: String) {}
                })
        }

        findViewById<AppCompatButton>(R.id.get_reward).setOnClickListener {
            gamelySDKClient = (application as GamelySampleApplication).gamelySDKClient()
            gamelySDKClient?.getReward(
                this@MainActivity,
                object : IResponseListener {
                    override fun onSuccess() {}
                    override fun onFailure(message: String) {}
                })
        }
    }


}