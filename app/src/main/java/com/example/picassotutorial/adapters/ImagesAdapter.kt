package com.example.picassotutorial.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.picassotutorial.R
import com.example.picassotutorial.ui.PictureActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImagesAdapter(context: Context?) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    private val mActivityContext = context
    private val mPicasso = Picasso.with(context)
    private val mInflate = LayoutInflater.from(context)


    private val mUtlStrings = arrayOf(
        "https://upload.wikimedia.org/wikipedia/commons/c/c4/NGC253_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/6/68/NGC2276_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/e/e5/NGC2403_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/9/94/NGC2683_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/3/3a/NGC3169_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/a/a8/NGC3344_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/b/b2/NGC4038_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/5/54/NGC4395_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/5/5b/NGC4414_Spiral_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/c/c0/NGC4490_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/7/78/NGC4565_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/8/84/NGC4676_Mice_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/5/53/NGC4910_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/1/19/NGC536_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/9/92/NGC5364_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/a/a0/NGC5426_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/a/a5/NGC5859_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/1/12/NGC5529_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/2/29/NGC5850_Galaxy_from_the_Mount_Lemmon_SkyCenter_Schulman_Telescope_courtesy_Adam_Block.jpg"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(mInflate.inflate(R.layout.view_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        mPicasso.cancelRequest(holder.imageView)
        holder.imageView.setImageBitmap(null)
        mPicasso
            .load(mUtlStrings[position])
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_error)
            .resizeDimen(
                R.dimen.image_size,
                R.dimen.image_size
            )
            .into(holder.imageView, object : Callback {
                override fun onSuccess() {
                    holder.imageView.visibility = View.VISIBLE
                    holder.progressBar.visibility = View.GONE
                }

                override fun onError() {
                    holder.imageView.visibility = View.INVISIBLE
                    holder.progressBar.visibility = View.VISIBLE
                }

            })
        holder.itemView.setOnClickListener {
            mActivityContext?.startActivity(
                Intent(mActivityContext, PictureActivity::class.java)
                    .putExtra(IMAGE_URL_KEY, mUtlStrings[position])
            )
        }
    }

    override fun getItemCount(): Int {
        return mUtlStrings.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView = itemView.findViewById(R.id.view_image)
        var progressBar: ProgressBar = itemView.findViewById(R.id.view_progress)
    }

    companion object {
        private const val IMAGE_URL_KEY = "IMAGE_URL"
    }
}