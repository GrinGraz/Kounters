package cl.getapps.kounters.feature.counters.ui

import android.net.ConnectivityManager
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import cl.getapps.kounters.R
import cl.getapps.kounters.base.areActiveNetwork
import cl.getapps.kounters.base.data.Result
import cl.getapps.kounters.base.showWithDelay
import cl.getapps.kounters.feature.counters.domain.model.Counter
import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.presentation.CountersRecyclerViewAdapter
import cl.getapps.kounters.feature.counters.presentation.CountersViewModel
import cl.getapps.kounters.feature.counters.presentation.ViewModelFactory
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.android.ext.android.inject

class CountersFragment : Fragment() {

    companion object {
        fun newInstance() = CountersFragment()
    }

    private val viewModelFactory: ViewModelFactory by inject()
    private val viewModel by viewModels<CountersViewModel> { viewModelFactory }
    private val connectivityManager: ConnectivityManager by inject()
    private var recyclerViewAdapter = CountersRecyclerViewAdapter(ItemEventListener())
    private lateinit var snackBar: Snackbar
    private lateinit var bottomSheetFragment: BottomSheetFragment
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecycler()

        snackBar = Snackbar.make(coordinator, "", Snackbar.LENGTH_INDEFINITE)

        with(viewModel) {
            items.observe(this@CountersFragment, Observer(::renderResult))
            item.observe(this@CountersFragment, Observer(::renderResult))
            if (connectivityManager.areActiveNetwork()) fetchCounters()
            else showSnackBar(
                message = getString(R.string.no_connection_message),
                isError = true,
                action = { fetchCounters() })
        }

        fab.setOnClickListener {
            bottomSheetFragment = BottomSheetFragment.newInstance(BottomSheetListener())
            fragmentManager?.let { it1 ->
                bottomSheetFragment.show(
                    it1,
                    getString(R.string.save_counter_fragment_tag)
                )
            }
        }
    }

    private fun setupRecycler() {
        linearLayoutManager = LinearLayoutManager(requireContext())
        with(counter_recylerview) {
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
            addScrollListener(this)
            setHasFixedSize(true)
        }
    }

    private fun addScrollListener(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab.visibility == View.VISIBLE) {
                    fab.hide()
                    title.visibility = View.GONE
                } else if (dy < 0 && fab.visibility != View.VISIBLE) {
                    fab.show()
                    title.visibility = View.VISIBLE
                }
            }
        })
    }

    fun renderResult(result: Result<*>) {
        when (result) {
            is Result.Success -> {
                when (result.data) {
                    is Counter -> {
                        recyclerViewAdapter.addItemAt(result.data, 0, notifyChange = true)
                        showWithDelay(500) {
                            snackBar.dismiss()
                        }
                    }
                    is List<*> -> {
                        recyclerViewAdapter.swapItems(result.data as Counters)
                        showWithDelay(500) {
                            snackBar.dismiss()
                        }
                    }

                }

            }
            is Result.Error   -> {
                showWithDelay(500) {
                    showSnackBar(
                        message = getString(R.string.server_issues_message),
                        isError = true,
                        action = { fetchCounters() }
                    )
                }
            }
            is Result.Loading -> {
                showWithDelay(500) {
                    showSnackBar(message = getString(R.string.loading_counters_message))
                }
            }
        }
    }

    private fun showSnackBar(
        message: String,
        isError: Boolean = false,
        action: CountersViewModel.() -> Unit = {}
    ) {
        if (::bottomSheetFragment.isInitialized && bottomSheetFragment.isAdded) bottomSheetFragment.dismiss()
        snackBar.anchorView = fab
        snackBar.setText(message).setDuration(BaseTransientBottomBar.LENGTH_INDEFINITE).run {
            if (isError) setAction(getString(R.string.label_retry)) {
                action(viewModel)
            }.show() else {
                setAction(null, null)
                show()
            }
        }
    }

    inner class ItemEventListener :
        CountersRecyclerViewAdapter.ItemEventListener {
        override fun onIncrementClick(item: Counter, position: Int) {
            viewModel.increment(item.id)
        }

        override fun onDecrementClick(item: Counter, position: Int) {
            if (item.count > 0) viewModel.decrement(item.id)
        }

        override fun onRemove(item: Counter, position: Int) {
            viewModel.remove(item.id)
            recyclerViewAdapter.removeItemAt(position, true)
        }

        override fun onSave() {
            showWithDelay(1000) {
                val linearSmoothScroller = object : LinearSmoothScroller(context) {
                    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                        return 150f / displayMetrics.densityDpi
                    }
                }

                linearSmoothScroller.targetPosition = 0
                linearLayoutManager.startSmoothScroll(linearSmoothScroller)
            }
        }
    }

    inner class BottomSheetListener : BottomSheetFragment.Listener {
        override fun onNetworkUnavailable(title: String) {
            showSnackBar(
                message = getString(R.string.no_connection_message),
                isError = true,
                action = { save(title) })
        }

        override fun onResult(result: Result<*>) {
            renderResult(result)
        }
    }
}
