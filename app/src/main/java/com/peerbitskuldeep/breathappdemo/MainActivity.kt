package com.peerbitskuldeep.breathappdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import com.github.florent37.viewanimator.ViewAnimator
import com.peerbitskuldeep.breathappdemo.sharedpref.Prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPref: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPref = Prefs(this)

        tvBreathTaken.text = "${sharedPref.sessions} min today"
        tvLastBreath.text = "${sharedPref.breaths} breaths"
        tvTodayMinutes.text = "${sharedPref.getDate()}"

        btnStart.setOnClickListener {
            startAnimation()
        }

    }

    private fun startAnimation() {
        ViewAnimator
            .animate(imageView)
            .alpha(0f,1f)
            .onStart {
                breath.text = "Inahle... Exhale"
            }
            .decelerate()
            .duration(1000)
            .thenAnimate(imageView)
            .scale(0.02f, 1.5f, 0.02f)
            .rotation(360f)
            .repeatCount(5)
            .accelerate()
            .duration(5000)
            .onStop {
                breath.text = "GREAT!!!"
                imageView.scaleX = 1.0f
                imageView.scaleY = 1.0f

                sharedPref.sessions = sharedPref.sessions+1
                sharedPref.breaths = sharedPref.breaths+1
                sharedPref.setDate(System.currentTimeMillis())

                var handler = Handler()

                var countDownTimer = object : Runnable{
                    override fun run() {
                        startActivity(Intent(applicationContext, MainActivity::class.java))
                        finish()
                    }
                }

                handler.postDelayed(countDownTimer, 100)

                //refresh the activity
              /* object : CountDownTimer(2000,100)
               {
                   override fun onTick(p0: Long) {

                   }

                   override fun onFinish() {
                      startActivity(Intent(applicationContext, MainActivity::class.java))
                       finish()
                   }

               }*/

            }
//            .start() //for countdown timer
            .start() //for animation
    }

    private fun breathAnimation()
    {
        ViewAnimator.animate(breath)
            .scale(0f,1f)
            .duration(1500)
            .onStart {
                breath.text = "Breath!!!"
            }
            .start()
    }

}