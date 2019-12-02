package cl.getapps.kounters.feature.counters.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import cl.getapps.kounters.R
import cl.getapps.kounters.base.data.BaseAdapter
import cl.getapps.kounters.base.data.BaseViewHolder
import cl.getapps.kounters.feature.counters.domain.model.Counter
import kotlinx.android.synthetic.main.item_counter.view.*

class CountersRecyclerViewAdapter(private val itemEventListener: ItemEventListener) : BaseAdapter<Counter>() {

    interface ItemEventListener{
        fun onIncrementClick(item: Counter, position: Int)
        fun onDecrementClick(item: Counter, position: Int)
        fun onRemove(item: Counter, position: Int)
        fun onSave(item: Counter, position: Int)
    }

    override fun provideComparator(): Comparator<Counter> = compareBy(Counter::id)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Counter> {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_counter, parent, false)

        return CounterViewHolder(itemView).also { holder ->
            with(itemView){
                holder.resolveItem()?.let { counter ->
                    btn_increment.setOnClickListener { itemEventListener.onIncrementClick(counter, holder.adapterPosition) }
                    btn_decrement.setOnClickListener { itemEventListener.onDecrementClick(counter, holder.adapterPosition) }
                }
            }
        }
    }
}
