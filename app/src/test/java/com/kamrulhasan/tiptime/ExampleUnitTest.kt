package com.kamrulhasan.tiptime

import android.util.Log
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun round_isChecked() {
        //reset()
        assertTrue(binding.swtRound.isChecked)
    }
    @Test
    fun custom_input(){
        assertTrue( 3==3)
    }
}