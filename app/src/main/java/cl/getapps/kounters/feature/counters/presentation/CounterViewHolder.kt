package cl.getapps.kounters.feature.counters.presentation

import android.view.View
import cl.getapps.kounters.base.data.BaseViewHolder
import cl.getapps.kounters.feature.counters.domain.model.Counter
import kotlinx.android.synthetic.main.item_counter.view.*

class CounterViewHolder(itemView: View) : BaseViewHolder<Counter>(itemView) {
    override fun bindView(item: Counter) {
        with(itemView) {
            counter_title.text = item.count
            counter_amount.text = item.title
        }
    }
}
