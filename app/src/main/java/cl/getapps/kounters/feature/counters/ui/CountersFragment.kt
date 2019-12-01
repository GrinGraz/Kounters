package cl.getapps.kounters.feature.counters.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.getapps.kounters.R
import cl.getapps.kounters.feature.counters.presentation.CountersViewModel

class CountersFragment : Fragment() {

    companion object {
        fun newInstance() = CountersFragment()
    }

    private lateinit var viewModel: CountersViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountersViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
