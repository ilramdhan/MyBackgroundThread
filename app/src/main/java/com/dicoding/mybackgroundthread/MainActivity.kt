package com.dicoding.mybackgroundthread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = findViewById<Button>(R.id.btn_start)
        val tvStatus = findViewById<TextView>(R.id.tv_status)

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        btnStart.setOnClickListener {
            executor.execute {
                try {
                    //simulate process in background thread
                    for (i in 0..10) {
                        Thread.sleep(500)
                        val percentage = i * 10
                        handler.post {
                            //update ui in main thread
                            if (percentage == 100) {
                                tvStatus.setText(R.string.task_completed)
                            } else {
                                tvStatus.text = String.format(getString(R.string.compressing), percentage)
                            }
                        }
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}