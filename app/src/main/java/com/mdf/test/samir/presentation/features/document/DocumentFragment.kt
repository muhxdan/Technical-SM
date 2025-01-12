package com.mdf.test.samir.presentation.features.document

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mdf.test.samir.R
import com.mdf.test.samir.databinding.FragmentDocumentBinding
import com.mdf.test.samir.utils.Constants.BASE_URL

class DocumentFragment : Fragment() {

    private var _binding: FragmentDocumentBinding? = null
    private val binding get() = _binding!!

    private val args: DocumentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDocumentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageUrl = args.url

        Glide.with(this).load(BASE_URL + imageUrl).listener(object : RequestListener<Drawable> {
            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>?,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                binding.ivPlaceholder.visibility = View.GONE
                return false
            }

            override fun onLoadFailed(
                e: GlideException?, model: Any?, target: Target<Drawable>, isFirstResource: Boolean
            ): Boolean {
                binding.ivPlaceholder.visibility = View.VISIBLE
                binding.ivPlaceholder.setImageDrawable(context?.let {
                    getDrawable(
                        it, R.drawable.ic_placeholder_error
                    )
                })
                return false
            }
        }).into(binding.ivLargeImage)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
