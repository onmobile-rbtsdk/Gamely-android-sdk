package com.onmobile.gamelysampleapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onmobile.gamelysdk.sdkutil.IGamelySdkClient
import com.onmobile.gamelysdk.sdkutil.IResponseListener
import com.onmobile.gamelysdk.sdkutil.Template


class MainActivity : AppCompatActivity(), TemplateAdapter.ITemplateSelectedListener {
    private val templateList =
        arrayListOf(MyTemplate("SPIN_WHEEL", "Spin Wheel", R.drawable.spin_wheel),
            MyTemplate("OPINION_POLL", "Opinion Poll", R.drawable.opinion_poll),
            MyTemplate("GET_REWARD", "Get Reward", null))
    private val questionsAdapter by lazy { TemplateAdapter(templateList, this) }

    private var gamelySDKClient: IGamelySdkClient? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.recycler).apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = questionsAdapter
        }

    }

    override fun onTemplateSelected(template: MyTemplate) {
        gamelySDKClient = (application as GamelySampleApplication).gamelySDKClient()
        if (template.id == "GET_REWARD") {
            gamelySDKClient?.getReward(this@MainActivity, object : IResponseListener {
                override fun onSuccess() {}

                override fun onFailure(message: String) {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }

            })
            return
        }

        val templateType = when (template.id) {
            "SPIN_WHEEL" -> Template.SPIN_WHEEL
            "OPINION_POLL" -> Template.OPINION_POLL
            else -> null
        } ?: return

        gamelySDKClient?.getTemplate(this@MainActivity, templateType, object : IResponseListener {
            override fun onSuccess() {}

            override fun onFailure(message: String) {
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}