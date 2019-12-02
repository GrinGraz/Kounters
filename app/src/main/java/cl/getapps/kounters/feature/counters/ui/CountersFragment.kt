package cl.getapps.kounters.feature.counters.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cl.getapps.kounters.R
import cl.getapps.kounters.feature.counters.domain.model.Counter
import cl.getapps.kounters.feature.counters.presentation.CountersRecyclerViewAdapter
import cl.getapps.kounters.feature.counters.presentation.CountersViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class CountersFragment : Fragment() {

    companion object {
        fun newInstance() = CountersFragment()
    }

    private lateinit var viewModel: CountersViewModel

    private var recyclerViewAdapter = CountersRecyclerViewAdapter(ItemEventListener())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountersViewModel::class.java)
    }

    inner class ItemEventListener :
        CountersRecyclerViewAdapter.ItemEventListener {
        override fun onIncrementClick(item: Counter, position: Int) {

        }

        override fun onDecrementClick(item: Counter, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onRemove(item: Counter, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onSave(item: Counter, position: Int) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}
