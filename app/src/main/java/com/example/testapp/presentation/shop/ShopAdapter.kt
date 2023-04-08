package com.example.testapp.presentation.shop

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.databinding.CloseWallpaperItemBinding
import com.example.testapp.databinding.OpenWallpaperItemBinding

class ShopAdapter(
    val onOpenItemClicked: (ShopRecyclerItem.OpenWallpaper) -> Unit,
    val onCloseItemClicked: (ShopRecyclerItem.CloseWallpaper) -> Unit
) : RecyclerView.Adapter<ShopAdapter.ShopViewHolder>(),View.OnClickListener {


    private var shopItems: List<ShopRecyclerItem> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    fun setList(items: List<ShopRecyclerItem>){
        shopItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
      return when(viewType){

            OPEN -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = OpenWallpaperItemBinding.inflate(inflater, parent, false)
                binding.root.setOnClickListener(this)
                ShopViewHolder.Open(binding)
            }
            CLOSE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = CloseWallpaperItemBinding.inflate(inflater, parent, false)
                binding.root.setOnClickListener(this)
                ShopViewHolder.Close(binding)
            }
          else -> throw java.lang.IllegalStateException()
      }
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bind(shopItems[position])
    }

    override fun getItemCount(): Int = shopItems.size


    override fun getItemViewType(position: Int): Int {
        return when (shopItems[position]) {
            is ShopRecyclerItem.OpenWallpaper -> OPEN
            is ShopRecyclerItem.CloseWallpaper -> CLOSE
            else -> throw java.lang.IllegalStateException()
        }
    }

    override fun onClick(view: View) {
        val item = view.tag as ShopRecyclerItem
        if (item is ShopRecyclerItem.OpenWallpaper)
            onOpenItemClicked(item)
        else
            onCloseItemClicked(item as ShopRecyclerItem.CloseWallpaper)
    }


    abstract class ShopViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
       open fun load(imageId: Int,imageView: ImageView) {
            Glide.with(view.context)
                .load(imageId)
                .centerCrop()
                .into(imageView)
        }
        open fun bind(shopItem: ShopRecyclerItem) = Unit

        class Open(private val binding: OpenWallpaperItemBinding) : ShopViewHolder(binding.root) {
            override fun bind(shopItem: ShopRecyclerItem) {
                binding.root.tag = shopItem
                load((shopItem as ShopRecyclerItem.OpenWallpaper).image,binding.openImage)
            }
        }

        class Close(private val binding: CloseWallpaperItemBinding) : ShopViewHolder(binding.root) {
            override fun bind(shopItem: ShopRecyclerItem) {
                val closeShopItem = shopItem as ShopRecyclerItem.CloseWallpaper
                binding.root.tag = shopItem
                binding.priceTv.text = binding.root.context.getString(R.string.price,closeShopItem.price.toString())
                load(closeShopItem.image,binding.closeImage)
            }
        }
    }

    private companion object {
        const val OPEN = 0
        const val CLOSE = 1



    }


}