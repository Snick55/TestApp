package com.example.testapp.presentation.shop

import android.app.AlertDialog
import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testapp.R
import com.example.testapp.databinding.ShopFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShopFragment : Fragment() {

    private lateinit var binding: ShopFragmentBinding
    private val viewModel by viewModels<ShopViewModel>()
    private var points = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShopFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ShopAdapter(onOpenItemClicked = {
            updateWallpaper(it.image)
        },
            onCloseItemClicked = {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                DialogManager.showAlert(dialogBuilder){
                    if (it.price > points) return@showAlert
                    viewModel.updatePoints(-it.price)
                    updateWallpaper(it.image)
                    viewModel.changeItem(it.image)
                    viewModel.getWallpapers()

                }
            })
        viewModel.wallpapers.observe(viewLifecycleOwner){
            adapter.setList(it)
        }

        viewModel.points.observe(viewLifecycleOwner){
            points = it
        }


        setupLayoutManager(binding, adapter)
    }


    private fun setupLayoutManager(binding: ShopFragmentBinding, adapter: ShopAdapter) {
        binding.root.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.root.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val width = binding.root.width
                val itemWidth = resources.getDimensionPixelSize(R.dimen.item_width)
                val columns = width / itemWidth
                binding.shopRecyclerview.adapter = adapter
                binding.shopRecyclerview.layoutManager =
                    GridLayoutManager(requireContext(), columns)
            }
        })
    }

    private fun updateWallpaper(imageId: Int) = lifecycleScope.launch(Dispatchers.Default){
        val bitmap = BitmapFactory.decodeResource(resources,imageId)
        val wallpaperManager = WallpaperManager.getInstance(requireContext())
        wallpaperManager.setBitmap(bitmap)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPoints()
        viewModel.getWallpapers()
    }
}