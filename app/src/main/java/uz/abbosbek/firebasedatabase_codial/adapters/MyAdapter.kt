package uz.abbosbek.firebasedatabase_codial.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.abbosbek.firebasedatabase_codial.databinding.ItemRvBinding
import uz.abbosbek.firebasedatabase_codial.models.MyImage

class MyAdapter(var list: ArrayList<MyImage> = ArrayList()) : RecyclerView.Adapter<MyAdapter.Vh>() {

    inner class Vh(val itemRvBinding: ItemRvBinding) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(myContact: MyImage) {
            itemRvBinding.itemText.text = myContact.name
            Picasso.get().load(myContact.image).into(itemRvBinding.itemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}