package cl.getapps.kounters.feature.counters.ui

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cl.getapps.kounters.R
import cl.getapps.kounters.base.areActiveNetwork
import cl.getapps.kounters.base.data.Result
import cl.getapps.kounters.feature.counters.domain.model.Counters
import cl.getapps.kounters.feature.counters.presentation.CountersViewModel
import cl.getapps.kounters.feature.counters.presentation.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet.*
import org.koin.android.ext.android.inject


class BottomSheetFragment() : BottomSheetDialogFragment() {

    private lateinit var listener: Listener

    constructor(listener: Listener) : this() {
        this.listener = listener
    }

    interface Listener {
        fun onResult(result: Result<*>)
        fun onNetworkUnavailable()
    }

    private val connectivityManager: ConnectivityManager by inject()
    private val viewModelFactory: ViewModelFactory by inject()
    private val viewModel by viewModels<CountersViewModel> { viewModelFactory }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save.setOnClickListener {
            if (connectivityManager.areActiveNetwork()) {
                with(viewModel) {
                    items.observe(this@BottomSheetFragment, Observer(::onResult))
                    item.observe(this@BottomSheetFragment, Observer(::onResult))
                    save(counter_name.text.toString())
                }
            } else listener.onNetworkUnavailable()
        }
    }

    private fun onResult(result: Result<*>) {
        listener.onResult(result)
    }

    companion object {

        fun newInstance(listener: Listener): BottomSheetFragment {
            return BottomSheetFragment(listener)
        }
    }
}
