package de.hdmstuttgart.parkspot.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import de.hdmstuttgart.parkspot.R

/**
 * This file is part of Parkspot.      
 *
 * Parkspot is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation version 3 of the License.
 * Parkspot is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Parkspot. 
 * If not, see http://www.gnu.org/licenses/.
 *
 * Copyright (c) 2019, Hochschule der Medien
 * Author: Monika Grabke
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },2000
        )
    }
}
