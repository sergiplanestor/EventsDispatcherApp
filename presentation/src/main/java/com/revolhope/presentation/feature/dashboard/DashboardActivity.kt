package com.revolhope.presentation.feature.dashboard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.revolhope.presentation.R
import com.revolhope.presentation.common.observe
import com.revolhope.presentation.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityDashboardBinding.inflate(layoutInflater)
        initObservers()
        initViews()
    }

    private fun initObservers() {
        observe(viewModel.errorLiveData) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        observe(viewModel.maxCapacityLiveData) {
            Toast.makeText(this, getString(R.string.max_capacity_updated), Toast.LENGTH_LONG).show()
        }
        observe(viewModel.dispatchEventLiveData) {

        }
        observe(viewModel.fetchEventsLiveData) {

        }
    }

    private fun initViews() {

    }

}
